import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { EmployeeModel } from '../models/employee.model';
import { StudentModel } from '../models/student.model';
import { UserModel } from '../models/user.model';

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(environment.baseUrl + '/admin/user/get',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  changeRoleForUser(userId: number, newRoleName: string): Observable<any> {
    return this.http.get<any>(environment.baseUrl + '/admin/change-role',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') }),
        params: new HttpParams().set('userId', userId.toString()).set('newRoleName', newRoleName)
      });
  }

  deleteUser(userId: number): Observable<boolean> {
    return this.http.get<boolean>(environment.baseUrl + '/admin/delete',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') }),
        params: new HttpParams().set('userId', userId.toString())
      });
  }

  addStudent(student: StudentModel): Observable<any> {
    return this.http.post<any>(environment.baseUrl + '/admin/add-student', student,
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  addEmployee(employee: EmployeeModel): Observable<any> {
    return this.http.post<any>(environment.baseUrl + '/admin/add-employee', employee,
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  deleteCourse(courseId: number): Observable<any> {
    return this.http.get<any>(environment.baseUrl + '/course/delete',
    {
      headers: new HttpHeaders({ Authorization: localStorage.getItem('token') }),
      params: new HttpParams().set('courseId', courseId.toString())
    });
  }
}
