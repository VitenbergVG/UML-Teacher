import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { NotificationType } from '../models/notification-type.enum';
import { NotificationModel } from '../models/notification.model';

@Injectable()
export class NotificationService {

  private notificationSubject = new Subject<NotificationModel>();

  public notification: NotificationModel;

  constructor() { }

  getNotificationSubject(): Observable<NotificationModel> {
    return this.notificationSubject.asObservable();
  }

  showNotification(title: string, text: string, type: NotificationType) {
    let notification: NotificationModel = { title: title, text: text, type: type, isShowed: true }
    this.notificationSubject.next(notification);
    window.setTimeout(() => {
      notification.isShowed = false;
      this.notificationSubject.next(notification);
    }, 10000);
  }
}
