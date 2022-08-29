import {TaskGateway} from "../gateway/task-gateway";
import {AutomatedTask} from "../entities";
import {Observable} from "rxjs";

export class GetTaskUseCases {
  constructor( private taskGateWay: TaskGateway) {}
  getAlbumById ($id: string) : Observable<AutomatedTask> {
    return this.taskGateWay.getByID($id);
  }

  getAllTask () : Observable<Array<AutomatedTask>> {
    return this.taskGateWay.getAll();
  }

  getAllByUser(user: string): Observable<Array<AutomatedTask>>{
    return this.taskGateWay.getAllByUser(user);
  }
}
