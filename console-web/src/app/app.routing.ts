import { Routes } from "@angular/router";
import { AuthGuard } from "./core/guards/auth.guard";
// import { NoAuthGuard } from "./core/guards/no-auth.guard";
import { DefaultComponent } from "./layouts/default/default.component";
import { EmptyComponent } from "./layouts/empty/empty.component";
import { RecoverPasswordComponent } from "./modules/auth/recover-password/recover-password.component";
import { SignInComponent } from "./modules/auth/sign-in/sign-in.component";
import { SignOutComponent } from "./modules/auth/sign-out/sign-out.component";
import { SignUpComponent } from "./modules/auth/sign-up/sign-up.component";
// import { AnalyticsComponent } from "./modules/dashboards/analytics/analytics.component";

// import { FaqsComponent } from "./modules/help/faqs/faqs.component";
// import { SupportComponent } from "./modules/help/support/support.component";
import {NoAuthGuard} from "./core/guards/no-auth";
import {DashboardComponent} from "./modules/dashboard/main-board/dashboard.component";
import {TasksCatalogComponent} from "./modules/dashboard/tasks/tasks.catalog.component";

export const appRoutes: Routes = [

  {path: '', pathMatch : 'full', redirectTo: 'tasks'},

  // Admin routes
  {
    path: '',
    component: DefaultComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'tasks',
        component: TasksCatalogComponent
      }
      // {
      //   path: 'analytics',
      //   component: AnalyticsComponent
      // },
      // {
      //   path: 'faqs',
      //   component: FaqsComponent
      // },
      // {
      //   path: 'support',
      //   component: SupportComponent
      // }
    ]
  },

  // Auth routes for guests
  {
    path: '',
    component: EmptyComponent,
    canActivate: [NoAuthGuard],
    canActivateChild: [NoAuthGuard],
    children: [
      {
        path: 'login',
        component: SignInComponent
      }, {
        path: 'register',
        component: SignUpComponent
      }, {
        path: 'recover',
        component: RecoverPasswordComponent
      }
    ]
  },

  // Auth routes for authenticated users
  {
    path: '',
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    component: EmptyComponent,
    children: [
      {
        path: 'logout',
        component: SignOutComponent
      }
    ]
  },
];
