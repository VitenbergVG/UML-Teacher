import { Pipe, PipeTransform } from '@angular/core';
import { CourseTaskInfoModel } from 'src/app/models/course-task-info.model';

@Pipe({
  name: 'isLastTask'
})
export class IsLastTaskPipe implements PipeTransform {

  transform(taskNumber: number, tasks: CourseTaskInfoModel[]): boolean {
    return tasks ? Object.keys(tasks).indexOf(taskNumber.toString()) === Object.keys(tasks).length - 1 : true;
  }

}
