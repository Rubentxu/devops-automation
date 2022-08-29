
import {Injectable} from "@angular/core";
import * as Rx from 'rxjs'
import {Job} from "./console.component";

@Injectable({
  providedIn: 'root',
})
export class WebSocketAPI {
  private subject?: Rx.Subject<MessageEvent>;
  // @ts-ignore
  private ws: WebSocket;

  constructor() {
  }

  public connect(url:string): Rx.Subject<MessageEvent> {
    if (!this.subject) {
      this.subject = this.create(url);
      console.log('Successfully connected: ' + url);
    }
    return this.subject;
  }

  private create(url:string): Rx.Subject<MessageEvent> {
    this.ws = new WebSocket(url);
    const observable = new Rx.Observable(
      (obs: Rx.Observer<MessageEvent>) => {
        this.ws.onmessage = obs.next.bind(obs);
        this.ws.onerror = obs.error.bind(obs);
        this.ws.onclose = obs.complete.bind(obs);
        return this.ws.close.bind(this.ws);
      });
    const observer = {
      next: (data: Object) => {
        if (this.ws.readyState === WebSocket.OPEN) {
          this.ws.send(JSON.stringify(data));
        }
      }
    };
    return Rx.Subject.create(observer, observable);
  }

  public send(job:Job) {
    this.ws.send(JSON.stringify(job) );
  }
}

