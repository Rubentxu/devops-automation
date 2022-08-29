import {Component, OnDestroy, OnInit} from '@angular/core';

import {WebSocketAPI} from './websocket-api';
import {map, Subject, Subscription} from "rxjs";
import {CloseEvent} from "sockjs-client";

export interface Job {
  id: string,
  author: string,
  name: string,
  pipeline: string
}

export interface SocketResponse {
  errorMessage: string
  isError: boolean
  data: string
}

@Component({
  selector: 'console',
  templateUrl: './console.component.html',
  styleUrls: ['./console.component.css']
})
export class ConsoleComponent implements OnInit {

  title = 'client';
  autor = ''
  message = '';
  public messages: Array<SocketResponse>;
  client: any;
  subject = new Subject();

  // private clientWebSocket: WebSocket;

  constructor(private wsService: WebSocketAPI) {
    this.messages = []
    wsService
      .connect("ws://localhost:8080/job_executed")
      .pipe(map((response: MessageEvent): SocketResponse => {
        return JSON.parse(response.data) as SocketResponse;
      })).subscribe(msg => {
      this.addMessage(msg)
    });
  }

  // constructor() {
  //   this.clientWebSocket = new WebSocket("ws://localhost:8080/job_executed");
  // }

  ngOnInit(): void {
    // this.messages = [];


    // this.clientWebSocket.onopen = (open:Event)=> {
    //   console.log("clientWebSocket.onopen", 'clientWebSocket');
    //   console.log("clientWebSocket.readyState", "websocketstatus");
    //
    // }
    // this.clientWebSocket.onclose = (event:CloseEvent)=> {
    //   console.log("clientWebSocket.onclose", 'this.clientWebSocket', event);
    //
    // }
    // this.clientWebSocket.onerror = (error:Event)=> {
    //   console.log("clientWebSocket.onerror", 'this.clientWebSocket', error);
    //
    // }
    // this.clientWebSocket.onmessage = (message)=> {
    //   console.log("clientWebSocket.onmessage", 'this.clientWebSocket', message);
    //   this.addMessage(message.data);
    //
    // }
  }


  addErrorMessage(prefix: string, error: string) {
    /*  var ul = document.getElementById("messages");
     var li = document.createElement("li");
     li.appendChild(document.createTextNode(prefix + error));
     ul.appendChild(li); */
    console.log(`Error : ${error}`)
  }

  addMessage(newMessage: SocketResponse) {
    this.messages = [...this.messages, newMessage];
  }

  ngOnDestroy(): void {
    this.subject.unsubscribe();
    if (this.client) {
      console.log(`OnDestroy : `)
      this.client.close();
    }
    // let unsubscribe = this.topicSubscription.unsubscribe();
  }

  requestByAutor(socket: any, data: any) {
    console.log(`Request by autor ${socket}`)
    socket
      .requestStream({
        data: data,
//                   metadata:  String.fromCharCode('jobs.by.author'.length) + 'jobs.by.author',
        metadata: String.fromCharCode('jobs.execute'.length) + 'jobs.execute',
      })
      .subscribe({
        onComplete: () => console.log('complete'),
        onError: (error: string) => {
          console.log("Connection has been closed due to:: " + error);
          this.addErrorMessage("Connection has been closed due to ", error);
        },
        onNext: (payload: any) => {
          console.log(payload.data);
          this.addMessage(payload.data);
        },
        onSubscribe: (subscription: any) => {
          subscription.request(2147483647);
        },
      });
  }

  sendMessage(newValue: any) {
    console.log("Sending message:" + newValue);
    this.autor = newValue;
    console.log("Sending autor:" + this.autor);
    this.wsService.send({
      author: `${this.autor ? this.autor : 'manolo'}`,
      id: "aaa",
      name: "listar ficheros",
      pipeline: "jenkinsfile"
    } as Job);
    this.messages = []

    // this.clientWebSocket.send(`{ "author": "${this.autor?this.autor:'manolo'}", "id": "aaa", "name": "listar ficheros", "pipeline": "jenkinsfile" }` );
  }

}
