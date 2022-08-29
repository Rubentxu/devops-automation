import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from "./layouts/layout.module";
import {ReactiveFormsModule} from "@angular/forms";
import {FormlyModule} from "@ngx-formly/core";
import {FormlyMaterialModule} from "@ngx-formly/material";
import {AuthInterceptor} from "./core/interceptors/auth.interceptor";
import {RouterModule} from "@angular/router";
import {appRoutes} from "./app.routing";
import {NgxWebstorageModule} from "ngx-webstorage";
import {AuthModule} from "./modules/auth/auth.module";
import {DashboardsModule} from "./modules/dashboard/dashboards.module";
import {TranslocoRootModule} from "./transloco-root.module";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {SharedModule} from "./modules/shared/shared.module";
import {MaterialModule} from "./modules/material-module";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    NgxWebstorageModule.forRoot(),
    TranslocoRootModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormlyModule.forRoot(),
    FormlyMaterialModule,
    LayoutModule,
    AuthModule,
    DashboardsModule,
    MatSnackBarModule,
    SharedModule,
    MaterialModule,
    FlexLayoutModule,
    MatButtonModule

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
