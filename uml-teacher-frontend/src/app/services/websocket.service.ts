import { Injectable } from "@angular/core";
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { AuthorizationService } from "./authorization.service";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  webSocketEndPoint: string = 'http://localhost:8080/ws';
  topic: string;
  stompClient: any;

  constructor(private authService: AuthorizationService) {
    this.topic = '/user/' + this.authService.currentUser.userId + '/queue/messages';
  }

  connect() {
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect(
      {},
      function (frame) {
        _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
          _this.onMessageReceived(sdkEvent);
        });
        //_this.stompClient.reconnect_delay = 2000;
      },
      this.errorCallBack
    );
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
      this.connect();
    }, 5000);
  }

  /**
   * Send message to sever via web socket
   */
  send(message) {
    if (message.trim() !== "") {
      const msg = {
        senderUserId: this.authService.currentUser.userId,
        recipientUserId: 6,
        content: message,
        timestamp: new Date(),
      };

      this.stompClient.send("/app/chat", {}, JSON.stringify(msg));
    }
  }

  onMessageReceived(message) {
    console.log('Message Recieved from Server :: ' + message);
    // this.appComponent.handleMessage(JSON.stringify(message.body));
  }

}