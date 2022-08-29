import { Component, OnInit } from '@angular/core';
import { TranslocoService } from '@ngneat/transloco';
// import { MessageService } from 'primeng/api';
import { Language } from 'src/app/model/utils/language';
import {StorageService} from "../../services/utils/storage.service";
import {LanguageService} from "../../services/utils/language.service";


@Component({
  selector: 'app-empty',
  templateUrl: './empty.component.html',
  styleUrls: ['./../layouts.component.scss']
})
export class EmptyComponent implements OnInit {
  languages!: Language[];
  selectedLanguage!: Language;
  constructor(private translocoService: TranslocoService,
              private storageService: StorageService,
              private languageService: LanguageService) { }

  ngOnInit(): void {
    this.languageService.getAllLanguage().subscribe({
        next: (values: Language[]) => {
          this.languages = values;
        }
      }
    );
    const activeLang = this.languageService.getLanguageByCode(this.storageService.getLang());

    this.selectedLanguage = (activeLang !== null) ? activeLang : this.languageService.getDefaultLanguage();

    this.translocoService.setActiveLang(this.selectedLanguage.code);

  }

  changeSiteLanguage(language: any): void {
    this.translocoService.setActiveLang(language.value.code);
    this.storageService.setLang(language.value.code);

  }

}
