import {AutomatedTask} from "../entities";
import {Observable} from "rxjs";

export abstract class TaskGateway {
  abstract getByID($id: string): Observable<AutomatedTask>;
  abstract getAllByUser(user: string): Observable<Array<AutomatedTask>>;
  abstract getAll(): Observable<Array<AutomatedTask>>;
}
