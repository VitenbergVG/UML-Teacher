import { Pipe, PipeTransform } from '@angular/core';
import { CourseTaskInfoModel } from 'src/app/models/course-task-info.model';

@Pipe({
  name: 'isFirstTask'
})
export class IsFirstTaskPipe implements PipeTransform {

  transform(taskNumber: number, tasks: CourseTaskInfoModel[]): boolean {
    return tasks ? Object.keys(tasks).indexOf(taskNumber.toString()) === 0 : true;
  }
}
