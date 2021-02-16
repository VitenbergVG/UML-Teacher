import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CourseModel } from 'src/app/models/course.model';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

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
  public completedCoursesQuantity: number;

  constructor(public educationalProcessService: EducationalProcessService,
    private taskManagementService: TaskManagementService,
    private router: Router) { }

  ngOnInit() {
    this.educationalProcessService.tasksSubject.next(undefined);
    this.educationalProcessService.getAllCourses()
      .subscribe(courses => this.courses = courses);
    this.educationalProcessService.getCurrentCourses()
      .subscribe(currentCourses => this.currentCourses = currentCourses);
    this.educationalProcessService.getCompletedCourse()
      .subscribe(completedCourses => this.completedCoursesQuantity = completedCourses.length);
  }

  continueCourse() {
    let courseId = this.currentCourses[this.currentCourseIndex].id;
    this.taskManagementService.getLastTaskNumber(courseId).subscribe(lastTaskNumber => {
      this.router.navigate(['study/course', courseId, 'task', lastTaskNumber]);
    });
  }
}
