import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TaskGateway} from "../../model/task/gateway/task-gateway";
import {AutomatedTask} from "../../model/task/entities";
import {delay, Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export  class TaskApiGateway implements TaskGateway {
  private url:string

  constructor(private http: HttpClient) {
    this.url = environment.API_URL
  }

  getByID($id: string): Observable<AutomatedTask> {
    return this.http.get<AutomatedTask>(this.url+$id).pipe(delay(2000));
  }
  getAll(): Observable<AutomatedTask[]> {
    return this.http.get<Array<AutomatedTask>>(this.url);
  }
  saveNew(_alb: AutomatedTask): Observable<void> {
    throw new Error('Method not implemented.');
  }

  getAllByUser(user: string): Observable<Array<AutomatedTask>> {
    throw new Error('Method not implemented.');
  }

}
