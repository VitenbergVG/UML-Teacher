import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnswerModel } from 'src/app/models/answer.model';
import { CourseModel } from 'src/app/models/course.model';
import { AuthorizationService } from 'src/app/services/authorization.service';
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

  public btCourseOperationText = 'View course';

  constructor(private router: Router,
    private authService: AuthorizationService,
    private educationalProcessService: EducationalProcessService,
    private taskManagementService: TaskManagementService) { }

  ngOnInit() {
    switch (this.authService.currentUser.role) {
      case 'STUDENT':
        this.btCourseOperationText = 'View course'
        break;
      case 'EMPLOYEE':
        this.btCourseOperationText = 'Check course'
        break;
    }
  }

  viewCourse() {
    switch (this.authService.currentUser.role) {
      case 'STUDENT':
        this.educationalProcessService.joinToCourse(this.course.id).subscribe();
        this.taskManagementService.getLastTaskNumber(this.course.id).subscribe(lastTaskNumber => {
          this.router.navigate(['study/course', this.course.id, 'task', lastTaskNumber]);
        });
        break;
      case 'EMPLOYEE':
        this.taskManagementService.getAnswerForTask(this.course.id, true).subscribe((answers: AnswerModel[]) => {
          this.taskManagementService.answersToCheck = answers;
          this.router.navigate(['check/answer', answers[0].id]);
        });
        break;
    }
  }
}
