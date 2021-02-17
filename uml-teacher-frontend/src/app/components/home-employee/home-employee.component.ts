import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CourseModel } from 'src/app/models/course.model';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

@Component({
  selector: 'home-employee',
  templateUrl: './home-employee.component.html',
  styleUrls: ['./home-employee.component.less']
})
export class HomeEmployeeComponent implements OnInit {

  public courses: CourseModel[];
  public currentCourseSortingType: string = 'ALL_COURSES';

  constructor(public educationalProcessService: EducationalProcessService) { }

  ngOnInit() {
    this.educationalProcessService.getCourseById(true)
      .subscribe(courses => this.courses = courses);
  }
}
