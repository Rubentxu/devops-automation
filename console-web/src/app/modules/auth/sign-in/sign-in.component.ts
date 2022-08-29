import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslocoService } from '@ngneat/transloco';
import { LoginRequest } from 'src/app/model/auth/login';
import { LoginRespose } from 'src/app/model/auth/login-response';
import { Language } from 'src/app/model/utils/language';
import {AuthService} from "../../../services/auth/auth.service";
import {MessageService} from "../../../services/message/message.service";
import {LanguageService} from "../../../services/utils/language.service";
import {BaseFormUser} from "../../../services/utils/base-form-user";



@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  hide = true;
  signInForm!: FormGroup;
  languages!: Language[];
  selectedLanguage!: Language;
  loading: boolean = false;

  constructor(private authService: AuthService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              public loginForm: BaseFormUser,
              private translocoService: TranslocoService,
              private messageService: MessageService,
              private languageService: LanguageService
  ) { }

  ngOnInit(): void {
    this.loginForm.baseForm.get('rememberMe')?.setValidators(null);
    this.loginForm.baseForm.get('rememberMe')?.updateValueAndValidity();

    this.languageService.getAllLanguage().subscribe({
      next: (values: Language[]) => {
        this.languages = values;
      }}
    );

    this.selectedLanguage = this.languageService.getDefaultLanguage();

  }

  doLogin() {
    // this.messageService.clear();

    if (this.loginForm.baseForm.valid) {

      const formValue = this.loginForm.baseForm.value;
      const loginData: LoginRequest = new LoginRequest(formValue.username, formValue.password, formValue.rememberMe);

      this.authService.signIn(loginData).subscribe(
        {
          next: (login: LoginRespose) => {
            if (login) {
              this.router.navigate(['']);
            }
            const redirectURL = this.activatedRoute.snapshot.queryParamMap.get('redirectURL') || '';
            this.router.navigateByUrl(redirectURL);
          },
          error: () => {
            this.messageService.error(this.translocoService.translate("sign-in.invalid-credentials") );
          }
        }
      );
    } else {
      this.messageService.error( this.translocoService.translate("msg.complete-data") );
    }

  }

  checkField(field: string): boolean {
    return this.loginForm.isValidField(field);
  }


  changeSiteLanguage(language: any): void {
    this.translocoService.setActiveLang(language.code);
    this.selectedLanguage = language
  }

}
