import {Component, OnInit} from "@angular/core";
import {LanguageService} from "../../../../services/utils/language.service";
import {Language} from "../../../../model/utils/language";
import {TranslocoService} from "@ngneat/transloco";

@Component({
  selector: 'devops-language-select',
  template: `
    <ng-container layout="row" layout-align="center center">
      <div fxLayout="row" fxLayoutAlign="center center" >
        <mat-icon>language</mat-icon>
        <button mat-button [matMenuTriggerFor]="menu">
          {{selectedLanguage.name}}
          <mat-icon>arrow_drop_down</mat-icon>
        </button>
        <mat-menu #menu="matMenu">
          <a mat-menu-item *ngFor="let item of languages" (click)="changeSiteLanguage(item)">{{item.name}}</a>
        </mat-menu>
      </div>     
    </ng-container>
  `
})
export class LanguageComponent implements OnInit{

  languages!: Language[];
  selectedLanguage!: Language;

  constructor(
      private languageService: LanguageService,
      private translocoService: TranslocoService,
  ) {
  }


  ngOnInit(): void {
    this.languageService.getAllLanguage().subscribe({
          next: (values: Language[]) => {
            this.languages = values;
          }
        }
    );
    this.selectedLanguage = this.languageService.getDefaultLanguage();

  }

  changeSiteLanguage(language: any): void {
    this.translocoService.setActiveLang(language.code);
    this.selectedLanguage = language
  }
}
