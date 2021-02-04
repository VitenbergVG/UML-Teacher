import { Component, OnInit } from '@angular/core';
import { CourseModel } from 'src/app/models/course.model';
import { EducationalProcessService } from 'src/app/services/educational-process.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {

  public courses: CourseModel[];
  public currentCourses: CourseModel[];
  public currentCourseIndex: number = 0;
  public currentCourseSortingType: string = 'ALL_COURSES';

  constructor(public educationalProcessService: EducationalProcessService) { }

  ngOnInit() {
    this.educationalProcessService.getAllCourses()
      .subscribe(courses => this.courses = courses);
    this.educationalProcessService.getCurrentCourses()
      .subscribe(currentCourses => this.currentCourses = currentCourses);
  }
}
