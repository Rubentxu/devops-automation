import {Component, OnInit} from '@angular/core';
import {CatalogueService} from "../../../services/services-catalog/catalogue.service";
import {AutomatedTask} from "../../../model/task/entities";

@Component({
  selector: 'tasks-catalog',
  templateUrl: './tasks.catalog.component.html',
  styleUrls: ['./tasks.catalog.component.scss']
})
export class TasksCatalogComponent implements OnInit {

  catalogue: Array<AutomatedTask> = [];

  constructor(private catalogueService: CatalogueService) {

  }

  ngOnInit(): void {
    this.getCatalogue()

  }

  private getCatalogue() {
    // this.catalogueService.getAutomatedTaskCatalogByUser('user').subscribe(data => {
    //   this.catalogue = data
    // })
  }

}
