import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnswerModel } from 'src/app/models/answer.model';
import { AuthorizationService } from 'src/app/services/authorization.service';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { NotificationService } from 'src/app/services/notification.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

@Component({
  selector: 'check-answer-process',
  templateUrl: './check-answer-process.component.html',
  styleUrls: ['./check-answer-process.component.less']
})
export class CheckAnswerProcessComponent implements OnInit {

  public answer: AnswerModel;
  public taskNumber: number;
  public question: string;
  public studentName: string;
  public studentUserId: number;

  constructor(private route: ActivatedRoute,
    private router: Router,
    public taskManagementService: TaskManagementService,
    private educationalProcessService: EducationalProcessService,
    private authService: AuthorizationService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.route.params
      .subscribe(params => {
        let answerId = params['answerId'];
        this.answer = this.taskManagementService.answersToCheck.find(answer => answer.id == answerId);
        this.taskNumber = parseInt(Object.keys(this.answer.courseTask)[0]);
        this.question = Object.values(this.answer.courseTask)[0];
        this.studentUserId = parseInt(Object.keys(this.answer.student)[0]);
        this.studentName = Object.values(this.answer.student)[0];
      });
  }

  navigateToNextTask() {
    let answerIdx = this.taskManagementService.answersToCheck.findIndex(answer => answer === this.answer);
    if (answerIdx === this.taskManagementService.answersToCheck.length - 1) {
      // this._initializeCourse(this.course.id);
      return;
    }
    let nextAnswer = this.taskManagementService.answersToCheck[answerIdx + 1];
    this.router.navigate(['check/answer', nextAnswer.id]);
  }

  navigateToPrevTask() {
    let answerIdx = this.taskManagementService.answersToCheck.findIndex(answer => answer === this.answer);
    if (answerIdx === 0) {
      return;
    }
    let prevAnswer = this.taskManagementService.answersToCheck[answerIdx - 1];
    this.router.navigate(['check/answer', prevAnswer.id]);
  }
}
