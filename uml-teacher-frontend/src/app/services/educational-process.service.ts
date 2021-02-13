import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CourseTaskInfoModel } from '../models/course-task-info.model';
import { CourseModel } from '../models/course.model';
import { TaskModel } from '../models/task.model';

@Injectable()
export class EducationalProcessService {

  public tasksSubject = new BehaviorSubject<CourseTaskInfoModel[]>(undefined);

  constructor(private http: HttpClient) { }

  getAllCourses(): Observable<CourseModel[]> {
    return this.http.get<CourseModel[]>(environment.baseUrl + '/education/courses',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  getCurrentCourses(): Observable<CourseModel[]> {
    return this.http.get<CourseModel[]>(environment.baseUrl + '/education/courses/current',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  getCompletedCourse(): Observable<CourseModel[]> {
    return this.http.get<CourseModel[]>(environment.baseUrl + '/education/courses/completed',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  getCourseById(courseId: number): Observable<CourseModel> {
    return this.http.get<CourseModel>(environment.baseUrl + '/course/get',
      {
        params: new HttpParams().set('courseId', courseId.toString()),
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  joinToCourse(courseId: number): Observable<void> {
    return this.http.get<void>(environment.baseUrl + '/education/courses/join',
    {
      params: new HttpParams().set('courseId', courseId.toString()),
      headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
    });
  }
}
