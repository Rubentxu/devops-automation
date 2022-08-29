import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TranslocoModule } from '@ngneat/transloco';
import {SignUpComponent} from "./sign-up/sign-up.component";
import {MessageService} from "../../services/message/message.service";
import {RecoverPasswordComponent} from "./recover-password/recover-password.component";
import {SignOutComponent} from "./sign-out/sign-out.component";
import {SignInComponent} from "./sign-in/sign-in.component";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {SharedModule} from "../shared/shared.module";
import {MatIconModule} from "@angular/material/icon";
import {FlexModule} from "@angular/flex-layout";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatRadioModule} from "@angular/material/radio";
import {MatSelectModule} from "@angular/material/select";
import {MaterialModule} from "../material-module";



@NgModule({
  declarations: [
    SignInComponent,
    SignOutComponent,
    SignUpComponent,
    RecoverPasswordComponent
  ],
    imports: [
        CommonModule,
        TranslocoModule,
        RouterModule,
        ReactiveFormsModule,
        FormsModule,
        MatCheckboxModule,
        MatCardModule,
        MatFormFieldModule,
        SharedModule,
        MatIconModule,
        FlexModule,
        MatToolbarModule,
        MatDividerModule,
        MatButtonModule,
        MatCheckboxModule,
        MatCheckboxModule,
        MatButtonModule,
        MatInputModule,
        MatAutocompleteModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatRadioModule,
        MatSelectModule,
        MaterialModule,
    ]

})
export class AuthModule { }
