import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from "./components/header/header.component";
import {FooterComponent} from "./components/footer/footer.component";
import {SidebarComponent} from "./components/sidebar/sidebar.component";
import { MatDividerModule} from "@angular/material/divider";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatMenuModule} from "@angular/material/menu";
import {MatListModule} from "@angular/material/list";
import {RouterModule} from "@angular/router";
import {AlertDialogComponent} from "./components/notification/alert-dialog.component";
import {ConfirmationDialogComponent} from "./components/notification/confirmation-dialog.component";
import { SpinnerComponent } from './components/spinner/spinner.component';
import {LanguageComponent} from "./components/language/language.component";
import {MaterialModule} from "../material-module";



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    AlertDialogComponent,
    ConfirmationDialogComponent,
    SpinnerComponent,
    LanguageComponent
  ],
    imports: [
        CommonModule,
        MatDividerModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        FlexLayoutModule,
        MatMenuModule,
        MatListModule,
        RouterModule,
        MaterialModule
    ],
  exports: [
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    SpinnerComponent,
    LanguageComponent
  ]
})
export class SharedModule { }
