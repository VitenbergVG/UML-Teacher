import { Pipe, PipeTransform } from '@angular/core';
import { AnswerModel } from 'src/app/models/answer.model';

@Pipe({
  name: 'isLastAnswer'
})
export class IsLastAnswerPipe implements PipeTransform {

  transform(answer: AnswerModel, answers: AnswerModel[]): boolean {
    return answers ? answers.findIndex(answer_ => answer === answer_) === answers.length : true;
  }
}
