import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotificationType } from 'src/app/models/notification-type.enum';
import { AuthorizationService } from 'src/app/services/authorization.service';
import { NotificationService } from 'src/app/services/notification.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

@Component({
  selector: 'uml-editor',
  templateUrl: './uml-editor.component.html',
  styleUrls: ['./uml-editor.component.less']
})
export class UmlEditorComponent implements OnInit {

  @Input()
  public courseId: number;
  @Input()
  public taskNumber: number;
  @Input()
  public studentUserId: number;

  public key: string;
  public answerId: number;
  public answerIsCorrect: boolean;

  constructor(private taskManagementService: TaskManagementService,
    private notificationService: NotificationService,
    public authService: AuthorizationService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .subscribe(params => {
        this.answerId = params['answerId'];
      });
    this.key = this.studentUserId + '_' + this.courseId + '_' + this.taskNumber;
  }

  sendToTeacherForCheck() {
    if (this.authService.currentUser.role !== 'EMPLOYEE') {
      this.taskManagementService.addAnswerToTask(this.courseId, this.taskNumber, this.key)
        .subscribe(() => this.notificationService.showNotification('Answer was send to teacher!',
          'Please wait the review.', NotificationType.SUCCESS));
    } else {
      this.taskManagementService.checkAnswer(this.answerId, this.answerIsCorrect)
        .subscribe(() => this.notificationService.showNotification('Answer was checked!',
          '', NotificationType.SUCCESS));
    }
  }
}
