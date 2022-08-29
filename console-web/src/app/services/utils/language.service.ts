import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { Language } from '../../model/utils/language'

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  private lngEnglish: Language = new Language('en', 'English', 'icon_uk_flag.png');
  private lngSpanish: Language = new Language('es', 'Español', 'icon_sp_flag.png');

  constructor() { }

  getAllLanguage(): Observable<Language[]> {
    const result: Observable<Language[]> = from([[
      this.lngEnglish,
      this.lngSpanish
    ]]);
    return result;
  }

  getDefaultLanguage(): Language {
    return this.lngSpanish;
  }

  getLanguageByCode(code: string): Language {
    let result: any = null;
    if(code !== null){
      result = (code === 'en') ? this.lngEnglish : this.lngSpanish;
    }

    return result;
  }
}
