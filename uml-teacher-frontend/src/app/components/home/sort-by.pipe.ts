import { Pipe, PipeTransform } from '@angular/core';
import { CourseModel } from 'src/app/models/course.model';
import { CourseSortingType } from './course-sorting-type.enum';

@Pipe({
  name: 'sortBy'
})
export class SortByPipe implements PipeTransform {

  transform(courses: CourseModel[], sortType: string): CourseModel[] {
    if (!courses) { return; }
    switch (CourseSortingType[sortType]) {
      case CourseSortingType.ALL_COURSES:
        return courses.sort((a, b) => a.name.localeCompare(b.name));
      case CourseSortingType.THE_NEWEST:
        return courses.sort((a, b) => new Date(b.createdDate).getTime() - new Date(a.createdDate).getTime());
      case CourseSortingType.TOP_RATED:
        return courses.sort((a, b) => b.rating - a.rating);
      case CourseSortingType.MOST_POPULAR:
        return courses;
    }
  }
}
