import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CourseTaskInfoModel } from 'src/app/models/course-task-info.model';
import { CourseModel } from 'src/app/models/course.model';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

@Component({
  selector: 'study-process',
  templateUrl: './study-process.component.html',
  styleUrls: ['./study-process.component.less']
})
export class StudyProcessComponent implements OnInit {

  public course: CourseModel;
  public tasks: CourseTaskInfoModel[];
  public currentTaskId: number;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private taskManagementService: TaskManagementService,
    private educationalProcessService: EducationalProcessService) { }

  ngOnInit() {
    this.route.params
      .subscribe(params => {
        let courseId = params['courseId'];
        this.currentTaskId = params['taskId'];
        this.taskManagementService.getTasksFromCourse(courseId)
        .subscribe(tasks => this.tasks = tasks);
        this.educationalProcessService.getCourseById(courseId)
        .subscribe(course => this.course = course);
      });
  }

  navigateToNextTask(isLastTask: boolean) {
    if (isLastTask) {
      return;
    }
    this.currentTaskId++;
    this.router.navigate(['study/course', this.course.id, 'task', this.currentTaskId]);
    console.log(this.currentTaskId)
  }

  navigateToPrevTask(isFirstTask: boolean) {
    if (isFirstTask) {
      return;
    }
    this.currentTaskId--;
    this.router.navigate(['study/course', this.course.id, 'task', this.currentTaskId]);
    console.log(this.currentTaskId)

  }

  confirmTextTask() {

  }
}
