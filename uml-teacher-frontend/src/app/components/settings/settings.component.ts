import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CourseModel } from 'src/app/models/course.model';
import { EmployeeModel } from 'src/app/models/employee.model';
import { NotificationType } from 'src/app/models/notification-type.enum';
import { StudentModel } from 'src/app/models/student.model';
import { UserModel } from 'src/app/models/user.model';
import { AdminService } from 'src/app/services/admin.service';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.less']
})
export class SettingsComponent implements OnInit, OnDestroy {

  public users: UserModel[];
  public courses: CourseModel[];
  public roles: string[] = ['ADMIN', 'USER', 'STUDENT', 'EMPLOYEE'];

  private unsubscribe = new Subject();

  constructor(private adminService: AdminService,
    private notificationService: NotificationService,
    private educationalProcessService: EducationalProcessService,
    private router: Router) { }

  ngOnInit() {
    this.adminService.getUsers()
      .pipe(takeUntil(this.unsubscribe))
      .subscribe(users => this.users = users,
        error => {
          this.notificationService.showNotification('Permission Error!',
            'You need the admin role to see this page', NotificationType.ERROR);
          this.router.navigate(['/home']);
        });
    this.educationalProcessService.getAllCourses()
      .pipe(takeUntil(this.unsubscribe))
      .subscribe(courses => this.courses = courses);
  }

  onUserDeleted(userId: number) {
    this.adminService.deleteUser(userId)
      .pipe(takeUntil(this.unsubscribe))
      .subscribe(responseStatus => {
        if (responseStatus) {
          let userIndexToDelete = this.users.findIndex(user => user.userId === userId);
          this.users.splice(userIndexToDelete, 1);
          this.notificationService.showNotification('Done!',
            'The information about user was successfully deleted.', NotificationType.SUCCESS);
        }
      });
  }

  onCourseDeleted(courseId: number) {
    this.adminService.deleteCourse(courseId)
      .pipe(takeUntil(this.unsubscribe))
      .subscribe(() => {
          let courseIndexToDelete = this.courses.findIndex(course => course.id === courseId);
          this.courses.splice(courseIndexToDelete, 1);
          this.notificationService.showNotification('Done!',
            'The information about course was successfully deleted.', NotificationType.SUCCESS);
      });
  }

  changeRoleFor(user: UserModel, newRoleName: string) {
    switch (newRoleName) {
      case 'STUDENT':
        this.adminService.addStudent(new StudentModel(user.userId)).subscribe();
        break;
      case 'EMPLOYEE':
        this.adminService.addEmployee(new EmployeeModel(user.userId)).subscribe();
        break;
    }
    this.adminService.changeRoleForUser(user.userId, newRoleName)
      .pipe(takeUntil(this.unsubscribe))
      .subscribe(() => {
        user.role = newRoleName;
        this.notificationService.showNotification('Done!', 'The role was successfully changed.', NotificationType.SUCCESS);
      });
  }

  ngOnDestroy() {
    this.unsubscribe.next();
    this.unsubscribe.complete();
    this.unsubscribe = null;
  }
}
