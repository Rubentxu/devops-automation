import { Injectable } from '@angular/core';
import { TranslocoService } from '@ngneat/transloco';
import * as crypto from 'crypto-js';
// import { MenuItem } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private translocoService: TranslocoService) { }

  getAppMenu(): any[] {

    const result: any[] = [
      {
        label: this.translocoService.translate('menu.dashboards'),
        items: [
          { label: this.translocoService.translate('menu.company'), icon: 'pi pi-home', routerLink: '/dashboard' },
          { label: this.translocoService.translate('menu.analytics'), icon: 'pi pi-chart-pie', routerLink: ['/analytics'] }
        ]
      },
      {
        label: this.translocoService.translate('menu.help-center'),
        items: [
          { label: this.translocoService.translate('menu.faqs'), icon: 'pi pi-question-circle', routerLink: '/faqs' },
          { label: this.translocoService.translate('menu.support'), icon: 'pi pi-comments', routerLink: ['/support'] }
        ]
      }
    ];
    return result;
  }

  getGravatarHash(email: string | ''): string {
    return crypto.MD5(email).toString();
  }
}
