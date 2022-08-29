import {Injectable} from '@angular/core';
import {CATALOGUE} from './mock-catalogue'
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AutomatedTask} from "../../model/task/entities";


@Injectable({
  providedIn: 'root'
})
export class CatalogueService {
  private baseUrl:string = ""
  constructor(private httpClient: HttpClient) {
  }

  getAutomatedTaskCatalogByUser(user: string): Observable<Array<AutomatedTask>> {
    // this.httpClient.get<Array<AutomatedTask>>(`${this.baseUrl}`)
    return of(CATALOGUE) as Observable<Array<AutomatedTask>>
  }

}
