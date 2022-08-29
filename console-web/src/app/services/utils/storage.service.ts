import { Injectable } from '@angular/core';
import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import {LoginRespose} from "../../model/auth/login-response";


@Injectable({
  providedIn: 'root'
})
export class StorageService {


  constructor(
    private localStorage: LocalStorageService,
    private sessionStorage: SessionStorageService) { }

  getToken(): string {
    return this.localStorage.retrieve('auth_token') || this.sessionStorage.retrieve('auth_token') || null;
  }

  getLoginData(): LoginRespose {
    return this.localStorage.retrieve('auth_data') || this.sessionStorage.retrieve('auth_data') || null;
  }

  storeToken(login:LoginRespose, rememberMe: boolean) {
    if (rememberMe) {
      this.localStorage.store('auth_token', login.token);
      this.localStorage.store('auth_data', login);
    } else {
      this.sessionStorage.store('auth_token', login.token);
      this.sessionStorage.store('auth_data', login);
    }
  }

  clearToken() {
    this.localStorage.clear('auth_token');
    this.sessionStorage.clear('auth_token');
    this.localStorage.clear('auth_data');
    this.sessionStorage.clear('auth_data');
  }

  setLang(code: string) {
    this.localStorage.store('lang', code);
  }

  getLang(): string{
    return this.localStorage.retrieve('lang');
  }
}
