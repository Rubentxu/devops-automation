import { Component, OnInit } from '@angular/core';
import {Language} from "../../model/utils/language";
import {StorageService} from "../../services/utils/storage.service";
import {TranslocoService} from "@ngneat/transloco";
import {LanguageService} from "../../services/utils/language.service";
import {UtilsService} from "../../services/utils/utils.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {LoginRespose} from "../../model/auth/login-response";

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.scss']
})
export class DefaultComponent implements OnInit {
  sideBarVsibility: boolean = true

  items!: any[];
  login!: LoginRespose;
  languages!: Language[];
  selectedLanguage!: Language;
  constructor(private authService: AuthService,
              private router: Router,
              private utilsService: UtilsService,
              private languageService: LanguageService,
              private translocoService: TranslocoService,
              private storageService: StorageService) { }



  ngOnInit(): void {
    this.login = this.authService.getLoginData();

    this.languageService.getAllLanguage().subscribe({
        next: (values: Language[]) => {
          this.languages = values;
        }
      }
    );

    const activeLang = this.languageService.getLanguageByCode(this.storageService.getLang());

    this.selectedLanguage = (activeLang !== null) ? activeLang : this.languageService.getDefaultLanguage();

    this.loadLanguage(this.selectedLanguage.code);
  }

  doLogout(): void {
    this.authService.logout().subscribe(
      {
        complete: () => {
          this.router.navigate(['login']);
        }
      }
    );
  }

  getGravatarHash(): string {
    return this.utilsService.getGravatarHash(this.login.email);
  }

  changeSiteLanguage(language: any): void {
    this.loadLanguage(language.value.code);
  }

  loadLanguage(languageCode: string): void {

    this.translocoService.load(languageCode).subscribe({
      next: () => {
        this.translocoService.setActiveLang(languageCode);
        this.storageService.setLang(languageCode);
        this.items = this.utilsService.getAppMenu();
      }
    })
  }

  sideBarToggler($event: any)  {
    this.sideBarVsibility= !this.sideBarVsibility
  }
}
