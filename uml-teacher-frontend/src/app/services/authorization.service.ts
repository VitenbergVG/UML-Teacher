import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CredentialsModel } from '../models/credential.model';
import { UserModel } from '../models/user.model';

@Injectable()
export class AuthorizationService {

  public currentUser: UserModel;
  private isSignUpMode = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  getSignUpModeSubject(): Observable<boolean> {
    return this.isSignUpMode.asObservable();
  }

  emitSignUpModeSubjectChanged(isSignUpMode: boolean) {
    this.isSignUpMode.next(isSignUpMode);
  }

  login(credentials: CredentialsModel): Observable<UserModel> {
    const token: string = 'Basic ' + btoa(credentials.username + ':' + credentials.password);
    const headers = new HttpHeaders({ Authorization: token });
    localStorage.setItem('token', token);
    return this.http.get<UserModel>(environment.baseUrl + '/authorization/sign-in', { headers: headers });
  }

  signUp(credentials: CredentialsModel, userFullname: string): Observable<UserModel> {
    const token: string = 'Basic ' + btoa(credentials.username + ':' + credentials.password);
    const headers = new HttpHeaders({ AuthorizationToken: token });
    localStorage.setItem('token', token);
    return this.http.post<UserModel>(environment.baseUrl + '/authorization/sign-up',
      {}, {
      headers: headers,
      params: new HttpParams().set('fullname', userFullname)
    });
  }
}
