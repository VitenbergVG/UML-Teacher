import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseTaskInfoModel } from 'src/app/models/course-task-info.model';
import { CourseModel } from 'src/app/models/course.model';
import { NotificationType } from 'src/app/models/notification-type.enum';
import { TaskType } from 'src/app/models/task-type.enum';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { NotificationService } from 'src/app/services/notification.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

@Component({
  selector: 'study-process',
  templateUrl: './study-process.component.html',
  styleUrls: ['./study-process.component.less']
})
export class StudyProcessComponent implements OnInit {

  public course: CourseModel;
  public tasks: { [taskNumber: number]: CourseTaskInfoModel };
  public currentTaskNumber: number;

  public formAnswer: FormGroup;
  // public isTaskConfirmed: boolean = false;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private taskManagementService: TaskManagementService,
    private educationalProcessService: EducationalProcessService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.route.params
      .subscribe(params => {
        let courseId = params['courseId'];
        this.currentTaskNumber = params['taskNumber'];
        this._initializeCourse(courseId);
        this._initializeTasks(courseId);
      });
  }

  private _initializeCourse(courseId: number) {
    this.educationalProcessService.getCourseById(courseId)
      .subscribe(course => {
        this.course = course;
      });
  }

  private _initializeTasks(courseId: number) {
    if (!this.educationalProcessService.tasksSubject.getValue()) {
      this.taskManagementService.getTasksFromCourse(courseId)
        .subscribe(tasks => {
          this.tasks = tasks;
          this.educationalProcessService.tasksSubject.next(tasks);
          this._initializeAnswerForm(courseId);
        });
    } else {
      this.tasks = this.educationalProcessService.tasksSubject.getValue();
      this._initializeAnswerForm(courseId);
    }
  }

  private _initializeAnswerForm(courseId: number) {
    let currentTask = this.tasks[this.currentTaskNumber];
    switch (currentTask.type) {
      case TaskType.TEXT:
        this.taskManagementService.getAnswerForTask(courseId, this.currentTaskNumber)
          .subscribe(
            answer => {
              this.formAnswer = new FormGroup({
                answer: new FormControl(answer.answer, Validators.required)
              });
            },
            error => {
              this.formAnswer = new FormGroup({
                answer: new FormControl('', Validators.required)
              });
            });
        break;
      case TaskType.TEST:
        this.taskManagementService.getAnswerForTask(courseId, this.currentTaskNumber)
          .subscribe(testAnswers => {
            let answers: string[] = testAnswers.answer.split(';');
            answers.forEach(answer => {
              const regEx: RegExp = /\$ID\$(.*)\$ANSWER\$(.*)/g
              let groups;
              while ((groups = regEx.exec(answer)) !== null) {
                if (groups.index === regEx.lastIndex) {
                  regEx.lastIndex++;
                }
                let testTaskIdx = groups[1];
                let testTaskAnswer = groups[2];
                currentTask.testTasks[testTaskIdx].enteredAnswer = testTaskAnswer;
              }
            });
          });
        break;
      case TaskType.GRAPHIC:
        break
    }
  }

  navigateToNextTask() {
    let taskNumbers: string[] = Object.keys(this.tasks);
    if (taskNumbers.indexOf(this.currentTaskNumber.toString()) === taskNumbers.length - 1) {
      this._initializeCourse(this.course.id);
      if (this.course.complete === 100) {
        this.notificationService.showNotification('Congratulations!',
          'Course was successfully completed.', NotificationType.SUCCESS);
      }
      this.router.navigate(['home']);
      return;
    }
    let nextTaskNumber = taskNumbers[taskNumbers.indexOf(this.currentTaskNumber.toString()) + 1];
    this.router.navigate(['study/course', this.course.id, 'task', nextTaskNumber]);
  }

  navigateToPrevTask() {
    let taskNumbers: string[] = Object.keys(this.tasks);
    if (taskNumbers.indexOf(this.currentTaskNumber.toString()) === 0) {
      return;
    }
    let prevTaskNumber = taskNumbers[taskNumbers.indexOf(this.currentTaskNumber.toString()) - 1];
    this.router.navigate(['study/course', this.course.id, 'task', prevTaskNumber]);
  }

  confirmTextTask() {
    let enteredAnswer: string = this.formAnswer.value.answer;
    let correctAnswer: string = this.tasks[this.currentTaskNumber].answer;
    if (!enteredAnswer || enteredAnswer === '') {
      this.notificationService.showNotification('Empty Answer!',
        'Check the correctly filling of answer.', NotificationType.WARNING);
      return;
    }
    if (enteredAnswer === correctAnswer) {
      this.notificationService.showNotification('Answer is correct!', '', NotificationType.SUCCESS);
      this.navigateToNextTask();
    } else {
      this.notificationService.showNotification('Answer is not correct!', 'Please, try again.', NotificationType.ERROR);
    }
    this.taskManagementService.addAnswerToTask(this.course.id, this.currentTaskNumber,
      enteredAnswer, enteredAnswer === correctAnswer)
      .subscribe();
  }

  confirmTestTask() {
    let testTasks = this.tasks[this.currentTaskNumber].testTasks;
    if (testTasks.some(task => !task.enteredAnswer || task.enteredAnswer === '')) {
      this.notificationService.showNotification('Not all answers to test were provided!',
        'Check the correctly filling of answers.', NotificationType.WARNING);
      return;
    }
    let isCorrect: boolean = testTasks.every(task => task.enteredAnswer === task.correctAnswer);
    if (isCorrect) {
      this.notificationService.showNotification('Answers are correct!', '', NotificationType.SUCCESS);
      this.navigateToNextTask();
    } else {
      this.notificationService.showNotification('Answer are not correct!', 'Please, try again.', NotificationType.ERROR);
    }

    let testAnswers: string = '';
    testTasks.forEach((task, index) => {
      testAnswers += '$ID$' + index + '$ANSWER$' + task.enteredAnswer + ';'
    });
    this.taskManagementService.addAnswerToTask(this.course.id, this.currentTaskNumber, testAnswers, isCorrect)
      .subscribe();
  }
}
