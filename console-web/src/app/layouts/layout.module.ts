import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DefaultComponent} from './default/default.component';
import {DashboardComponent} from "../modules/dashboard/main-board/dashboard.component";
import {RouterModule} from "@angular/router";
import {SharedModule} from "../modules/shared/shared.module";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatDividerModule} from "@angular/material/divider";
import {MatCardModule} from "@angular/material/card";
import {FlexModule} from "@angular/flex-layout";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatIconModule} from "@angular/material/icon";
import {MatGridListModule} from "@angular/material/grid-list";
import {EmptyComponent} from "./empty/empty.component";
import {TranslocoModule} from "@ngneat/transloco";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {ConfirmationDialogComponent} from "../modules/shared/components/notification/confirmation-dialog.component";
import {AlertDialogComponent} from "../modules/shared/components/notification/alert-dialog.component";
import {MaterialModule} from "../modules/material-module";



@NgModule({
  declarations: [
    DefaultComponent,
    EmptyComponent,
  ],
    imports: [
        CommonModule,
        RouterModule,
        SharedModule,
        MatSidenavModule,
        MatDividerModule,
        MatCardModule,
        FlexModule,
        MatProgressBarModule,
        MatIconModule,
        MatGridListModule,
        TranslocoModule,
        BrowserAnimationsModule,
        FormsModule,
        MaterialModule,

    ]
})
export class LayoutModule { }
