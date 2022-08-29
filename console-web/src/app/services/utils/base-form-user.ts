import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class BaseFormUser {
  private isValidEmail = /\S+@\S+\.\S+/;
  errorMessage = null;

  constructor(private fb: FormBuilder) {}

  baseForm: FormGroup = this.fb.group({
    username: [
      '',
      [Validators.required, Validators.pattern(this.isValidEmail)],
    ],
    password: ['', [Validators.required, Validators.minLength(5)]],
    rememberMe: ['', [Validators.required]],
  });

  isValidField(field: string): boolean {
    this.getErrorMessage(field);
    let fieldForm = this.baseForm.get(field) as any
    return (
      (fieldForm?.touched || fieldForm?.dirty) &&
      !fieldForm?.valid
    );
  }

  private getErrorMessage(field: string): void {
    const {errors }  = this.baseForm.get(field) as any;

    if (errors) {
      const minlenght = errors?.minlength?.requiredLength;
      const messages:any = {
        required: 'You must enter a value.',
        pattern: 'Not a valid email.',
        minlength: `This field must be longer than ${minlenght} characters`,
      };

      const errorKey:any = Object.keys(errors).find(Boolean);
      this.errorMessage = messages[errorKey];
    }
  }
}
