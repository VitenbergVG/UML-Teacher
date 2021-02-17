import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AnswerModel } from '../models/answer.model';
import { CourseTaskInfoModel } from '../models/course-task-info.model';

@Injectable()
export class TaskManagementService {

  public answersToCheck: AnswerModel[];

  constructor(private http: HttpClient) { }

  getTasksFromCourse(courseId: number): Observable<CourseTaskInfoModel[]> {
    return this.http.get<CourseTaskInfoModel[]>(environment.baseUrl + '/task/course-tasks',
      {
        params: new HttpParams().set('courseId', courseId.toString()),
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  getLastTaskNumber(courseId: number): Observable<number> {
    return this.http.get<number>(environment.baseUrl + '/task/last',
      {
        params: new HttpParams().set('courseId', courseId.toString()),
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  addAnswerToTask(courseId: number, taskNumber: number, answer: string, isCorrect?: boolean): Observable<AnswerModel> {
    let params = isCorrect
      ? new HttpParams()
        .set('courseId', courseId.toString())
        .set('taskNumber', taskNumber.toString())
        .set('isCorrect', String(isCorrect))
      : new HttpParams()
        .set('courseId', courseId.toString())
        .set('taskNumber', taskNumber.toString());
    return this.http.post<AnswerModel>(environment.baseUrl + '/education/answers/add', answer,
      {
        params: params,
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  checkAnswer(answerId: number, isCorrect: boolean): Observable<any> {
    return this.http.post<any>(environment.baseUrl + '/education/answers/check', isCorrect,
      {
        params: new HttpParams().set('answerId', answerId.toString()),
        headers: new HttpHeaders()
          .set('Authorization', localStorage.getItem('token'))
          .set('Content-Type', 'application/json')
      });
  }

  getAnswerForTask(courseId: number, unchecked?: boolean, studentUserId?: number, taskNumber?: number): Observable<any> {
    let params;
    if (!studentUserId && !taskNumber) {
      params = new HttpParams()
        .set('courseId', courseId.toString())
        .set('unchecked', String(unchecked));
    } else if (unchecked === undefined) {
      params = new HttpParams()
        .set('courseId', courseId.toString())
        .set('taskNumber', taskNumber.toString())
        .set('studentUserId', studentUserId.toString());
    }
    return this.http.get<any>(environment.baseUrl + '/education/answers/get',
      {
        params: params,
        headers: new HttpHeaders()
          .set('Authorization', localStorage.getItem('token'))
      });
  }
}
