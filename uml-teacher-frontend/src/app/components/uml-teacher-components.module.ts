import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorizationComponent } from './authorization/authorization.component';
import { AuthorizationService } from '../services/authorization.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EducationalProcessService } from '../services/educational-process.service';
import { HomeStatisticsComponent } from './home-statistics/home-statistics.component';
import { HomeComponent } from './home/home.component';
import { ChartsModule } from 'ng2-charts';
import { EducationMaterialModel } from '../models/education-material.model';
import { CoursePreveiwCardComponent } from './course-preveiw-card/course-preveiw-card.component';
import { DurationPipe } from './course-preveiw-card/duration.pipe';
import { SortByPipe } from './home/sort-by.pipe';

@NgModule({
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    ChartsModule
  ],
  declarations: [AuthorizationComponent, HomeComponent, HomeStatisticsComponent, CoursePreveiwCardComponent, DurationPipe, SortByPipe],
  providers: [AuthorizationService, EducationalProcessService, DurationPipe, SortByPipe]
})
export class UmlTeacherComponentsModule { }
