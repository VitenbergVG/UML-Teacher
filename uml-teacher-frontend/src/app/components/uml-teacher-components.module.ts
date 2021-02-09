import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { AuthorizationService } from '../services/authorization.service';
import { EducationalProcessService } from '../services/educational-process.service';
import { TaskManagementService } from '../services/task-management.service';
import { AuthorizationComponent } from './authorization/authorization.component';
import { CoursePreveiwCardComponent } from './course-preveiw-card/course-preveiw-card.component';
import { DurationPipe } from './course-preveiw-card/duration.pipe';
import { HomeStatisticsComponent } from './home-statistics/home-statistics.component';
import { HomeComponent } from './home/home.component';
import { SortByPipe } from './home/sort-by.pipe';
import { StudyProcessComponent } from './study-process/study-process.component';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from '../app-routing.module';

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
    CoursePreveiwCardComponent, DurationPipe, SortByPipe, StudyProcessComponent],
  providers: [AuthorizationService, EducationalProcessService, TaskManagementService, DurationPipe, SortByPipe]
})
export class UmlTeacherComponentsModule { }
