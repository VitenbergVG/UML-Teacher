import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { NotificationModel } from 'src/app/models/notification.model';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.less']
})
export class NotificationComponent implements OnInit, OnDestroy {

  public notification: NotificationModel;

  private unsubscribe = new Subject();

  constructor(private notificationService: NotificationService) { }

  ngOnInit() {
    this.notificationService.getNotificationSubject()
      .pipe(takeUntil(this.unsubscribe))
      .subscribe(notification => this.notification = notification);
  }

  ngOnDestroy() {
    this.unsubscribe.next();
    this.unsubscribe.complete();
    this.unsubscribe = null;
  }
}
