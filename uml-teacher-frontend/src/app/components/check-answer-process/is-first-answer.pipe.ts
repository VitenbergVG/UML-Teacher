import { Pipe, PipeTransform } from '@angular/core';
import { AnswerModel } from 'src/app/models/answer.model';
import { CourseTaskInfoModel } from 'src/app/models/course-task-info.model';

@Pipe({
  name: 'isFirstAnswer'
})
export class IsFirstAnswerPipe implements PipeTransform {

  transform(answer: AnswerModel, answers: AnswerModel[]): boolean {
    return answers ? answers.findIndex(answer_ => answer === answer_) === 0 : true;
  }
}
