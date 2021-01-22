import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CredentialsModel } from '../models/credential.model';

@Injectable()
export class AuthorizationService {

  public currentUserId: number;
  private isSignUpMode = new Subject<boolean>();
  private currentUserHasAdminRole = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  getSignUpModeSubject(): Observable<boolean> {
    return this.isSignUpMode.asObservable();
  }

  emitSignUpModeSubjectChanged(isSignUpMode: boolean) {
    this.isSignUpMode.next(isSignUpMode);
  }

  getCurrentUserHasAdminRoleSubject(): Observable<boolean> {
    return this.currentUserHasAdminRole.asObservable();
  }

  login(credentials: CredentialsModel): Observable<number> {
    const token: string = 'Basic ' + btoa(credentials.username + ':' + credentials.password);
    const headers = new HttpHeaders({ Authorization: token });
    localStorage.setItem('token', token);
    return this.http.get<number>(environment.baseUrl + '/authorization/sign-in', { headers: headers });
  }

  signUp(credentials: CredentialsModel, userFullname: string): Observable<number> {
    const token: string = 'Basic ' + btoa(credentials.username + ':' + credentials.password);
    const headers = new HttpHeaders({ AuthorizationToken: token });
    localStorage.setItem('token', token);
    return this.http.post<number>(environment.baseUrl + '/authorization/sign-up',
      {}, {
      headers: headers,
      params: new HttpParams().set('fullname', userFullname)
    });
  }

  checkUserHasAdminRole() {
    this.http.get<boolean>(environment.baseUrl + '/authorization/has-admin-role',
      {
        params: new HttpParams().set('userId', this.currentUserId.toString()),
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      }).subscribe(isAdmin => this.currentUserHasAdminRole.next(isAdmin));
  }
}
