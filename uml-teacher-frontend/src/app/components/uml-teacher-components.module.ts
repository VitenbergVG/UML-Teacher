import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { ChartsModule } from 'ng2-charts';
import { AppRoutingModule } from '../app-routing.module';
import { AdminService } from '../services/admin.service';
import { AuthorizationService } from '../services/authorization.service';
import { EducationalProcessService } from '../services/educational-process.service';
import { NotificationService } from '../services/notification.service';
import { TaskManagementService } from '../services/task-management.service';
import { AuthorizationComponent } from './authorization/authorization.component';
import { CheckAnswerProcessComponent } from './check-answer-process/check-answer-process.component';
import { IsFirstAnswerPipe } from './check-answer-process/is-first-answer.pipe';
import { IsLastAnswerPipe } from './check-answer-process/is-last-answer.pipe';
import { CoursePreveiwCardComponent } from './course-preveiw-card/course-preveiw-card.component';
import { DurationPipe } from './course-preveiw-card/duration.pipe';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';
import { HomeStatisticsComponent } from './home-statistics/home-statistics.component';
import { HomeComponent } from './home/home.component';
import { SortByPipe } from './home/sort-by.pipe';
import { MessangerComponent } from './messanger/messanger.component';
import { RoleDescriptionPipe } from './settings/role-description.pipe';
import { SettingsComponent } from './settings/settings.component';
import { IsFirstTaskPipe } from './study-process/is-first-task.pipe';
import { IsLastTaskPipe } from './study-process/is-last-task.pipe';
import { StudyProcessComponent } from './study-process/study-process.component';
import { TestTaskComponent } from './test-task/test-task.component';
import { SanitizeUrlPipe } from './uml-editor/sanitize-url.pipe';
import { UmlEditorComponent } from './uml-editor/uml-editor.component';

@NgModule({
  imports: [
    ReactiveFormsModule,
    RouterModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    ChartsModule,
    NgCircleProgressModule.forRoot({
      radius: 20,
      toFixed: 0,
      maxPercent: 100,
      outerStrokeWidth: 4,
      outerStrokeColor: '#000000',
      outerStrokeGradientStopColor: '#000000',
      titleFontSize: '13',
      animateTitle: false,
      showSubtitle: false,
      showUnits: true,
      showBackground: false,
      showInnerStroke: false,
      clockwise: false,
      lazy: true
    })
  ],
  declarations: [AuthorizationComponent, HomeComponent, HomeStatisticsComponent,
    CoursePreveiwCardComponent, DurationPipe, SortByPipe, StudyProcessComponent,
    IsFirstTaskPipe, IsLastTaskPipe, TestTaskComponent, SettingsComponent, RoleDescriptionPipe,
    UmlEditorComponent, SanitizeUrlPipe, MessangerComponent, HomeEmployeeComponent, CheckAnswerProcessComponent, 
    IsLastAnswerPipe, IsFirstAnswerPipe],
  providers: [AuthorizationService, EducationalProcessService, TaskManagementService,
    DurationPipe, SortByPipe, IsFirstTaskPipe, IsLastTaskPipe, NotificationService,
    RoleDescriptionPipe, AdminService, SanitizeUrlPipe, IsLastAnswerPipe, IsFirstAnswerPipe]
})
export class UmlTeacherComponentsModule { }
