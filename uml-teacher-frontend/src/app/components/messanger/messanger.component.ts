import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/user.model';
import { AuthorizationService } from 'src/app/services/authorization.service';
import { ChatService } from 'src/app/services/chat.service';
import { WebsocketService } from 'src/app/services/websocket.service';

@Component({
  selector: 'app-messanger',
  templateUrl: './messanger.component.html',
  styleUrls: ['./messanger.component.less']
})
export class MessangerComponent implements OnInit, OnDestroy {

  public contacts: UserModel[];
  public activeContact: UserModel;
  public messages: any[];

  constructor(public wsServie: WebsocketService,
    public chatService: ChatService,
    public authService: AuthorizationService) { }

  ngOnInit() {
    // this.wsServie.connect();
    this.chatService.activeContactSubject.subscribe(contact => this.activeContact = contact);

    this.chatService.getUsers().subscribe(users => {
      users.map((contact) => {
        this.chatService.getCountNewMessages(contact.userId)
          .subscribe(count => {
            contact.newMessages = count;
            return contact;
          });
      });
      this.contacts = users;

      if (this.activeContact === undefined && users.length > 0) {
        this.chatService.activeContactSubject.next(users[0]);
        this.chatService.findChatMessages(this.authService.currentUser.userId, users[0].userId)
          .subscribe(messages => this.messages = messages);
      }
    });
  }

  ngOnDestroy() {
    // this.wsServie.disconnect();
  }
}
