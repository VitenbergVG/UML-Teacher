import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TestCourseTaskModel } from 'src/app/models/test-course-task.model';

@Component({
  selector: 'test-task',
  templateUrl: './test-task.component.html',
  styleUrls: ['./test-task.component.less']
})
export class TestTaskComponent implements OnInit {

  @Input()
  public taskIndex: number;

  @Input()
  public task: TestCourseTaskModel;

  constructor() { }

  ngOnInit() {
  }
}
