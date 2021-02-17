import { NotificationType } from './notification-type.enum';

export class NotificationModel {
  title: string;
  text: string;
  type: NotificationType;
  isShowed: boolean;
}
