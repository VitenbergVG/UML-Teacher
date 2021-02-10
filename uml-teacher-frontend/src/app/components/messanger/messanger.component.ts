import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/user.model';
import { ChatService } from 'src/app/services/chat.service';
import { WebsocketService } from 'src/app/services/websocket.service';

@Component({
  selector: 'app-messanger',
  templateUrl: './messanger.component.html',
  styleUrls: ['./messanger.component.less']
})
export class MessangerComponent implements OnInit, OnDestroy {

  public users: UserModel[];

  constructor(public wsServie: WebsocketService,
    private chatService: ChatService) { }

  ngOnInit() {
    this.wsServie.connect();
    this.chatService.getUsers().subscribe(users => {
      users.map((contact) => {
        this.chatService.getCountNewMessages(contact.userId).subscribe(count => {
          contact.newMessages = count;
          return contact;
        });
      });
      this.users = users;
      console.log(this.users);
    });
  }

  ngOnDestroy(): void {
    this.wsServie.disconnect();
  }
}
