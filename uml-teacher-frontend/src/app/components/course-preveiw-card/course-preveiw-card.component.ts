import { Component, Input, OnInit } from '@angular/core';
import { CourseModel } from 'src/app/models/course.model';

@Component({
  selector: 'course-preveiw-card',
  templateUrl: './course-preveiw-card.component.html',
  styleUrls: ['./course-preveiw-card.component.less']
})
export class CoursePreveiwCardComponent implements OnInit {

  @Input()
  public course: CourseModel;

  constructor() { }

  ngOnInit() {
  }

}
