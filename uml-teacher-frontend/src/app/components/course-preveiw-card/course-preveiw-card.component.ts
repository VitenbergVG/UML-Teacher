import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseModel } from 'src/app/models/course.model';
import { EducationalProcessService } from 'src/app/services/educational-process.service';
import { TaskManagementService } from 'src/app/services/task-management.service';

@Component({
  selector: 'course-preveiw-card',
  templateUrl: './course-preveiw-card.component.html',
  styleUrls: ['./course-preveiw-card.component.less']
})
export class CoursePreveiwCardComponent implements OnInit {

  @Input()
  public course: CourseModel;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private educationalProcessService: EducationalProcessService,
    private taskManagementService: TaskManagementService) { }

  ngOnInit() {
  }

  viewCourse() {
    this.educationalProcessService.joinToCourse(this.course.id).subscribe();
    this.taskManagementService.getLastTaskNumber(this.course.id).subscribe(lastTaskNumber => {
      this.router.navigate(['study/course', this.course.id, 'task', lastTaskNumber]);
    });
  }
}
