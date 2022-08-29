import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslocoService } from '@ngneat/transloco';
import { SignUp } from 'src/app/model/auth/sign-up';
import { ResponseMessage } from 'src/app/model/utils/message';
import {AuthService} from "../../../services/auth/auth.service";
import {MessageService} from "../../../services/message/message.service";


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./../auth.component.scss']
})
export class SignUpComponent implements OnInit {

  signUpForm!: FormGroup;
  isRegistered: boolean = false;

  constructor(private authService: AuthService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private _formBuilder: FormBuilder,
              private translocoService: TranslocoService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.signUpForm = this._formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  doSignUp() {
    if(this.signUpForm.valid){
      const email: string = this.signUpForm.get('email')!.value;
      const name: string = this.signUpForm.get('name')!.value;
      const lastname: string = this.signUpForm.get('lastname')!.value;
      const password: string = this.signUpForm.get('password')!.value;

      const sigUpData: SignUp = new SignUp(email, name, lastname, password);

      // this.messageService.clear();

      console.log(sigUpData);

      this.authService.signUp(sigUpData).subscribe({
          next: (message: ResponseMessage) => {

            this.messageService.success( this.translocoService.translate(message.code));

            this.isRegistered = true;
          },
          error: (errorMsg: any) => {
            console.log(this.translocoService.translate(errorMsg.error.code));
            this.messageService.error(this.translocoService.translate(errorMsg.error.code));
          }
        }
      );
    }else{
      this.messageService.error(this.translocoService.translate("msg.complete-data"));
    }

  }

}
