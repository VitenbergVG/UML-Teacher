import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserModel } from '../models/user.model';
import { AuthorizationService } from './authorization.service';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  public activeContactSubject = new Subject<UserModel>();

  constructor(private http: HttpClient,
    private authService: AuthorizationService) { }

  getUsers(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(environment.baseUrl + '/messenger/users',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  getCountNewMessages(senderId: number): Observable<number> {
    return this.http.get<number>(environment.baseUrl + '/messenger/messages/' + senderId
      + '/' + this.authService.currentUser.userId + '/count',
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }

  findChatMessages(senderId: number, recipientId: number): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(environment.baseUrl + '/messenger/messages/' + senderId + '/' + recipientId,
      {
        headers: new HttpHeaders({ Authorization: localStorage.getItem('token') })
      });
  }
}
