import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TranslocoModule} from '@ngneat/transloco';
import {DashboardComponent} from "./main-board/dashboard.component";
import {TasksCatalogComponent} from "./tasks/tasks.catalog.component";
import {TaskComponent} from "./tasks/task.component";
import {MaterialModule} from "../material-module";


@NgModule({
  declarations: [
    DashboardComponent,
    TasksCatalogComponent,
    TaskComponent
  ],
    imports: [
        CommonModule,
        TranslocoModule,
        MaterialModule,


    ]
})
export class DashboardsModule { }
