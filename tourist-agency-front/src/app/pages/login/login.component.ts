import { Component, OnInit } from '@angular/core';
import {Validators} from '@angular/forms';
import {AuthService} from '../../services/authority/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {first} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public formConfiguration: any;
  public error: string;
  private returnUrl!: string;

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthService) {
    // redirect if user already logged in
    if (this.authService.getCurrentUserValue()) {
      this.router.navigate(['/']);
    }
    this.initializeFormConfiguration();
  }

  ngOnInit(): void {
  }

  public onSubmit($event: any): void {
    const user = $event.values;
    this.authService.login(user).pipe(first()).subscribe(
      data => {
        const url = this.returnUrl ? this.returnUrl : '/';
        this.router.navigate([url]);
      }, error => {
        this.error = error.statusText;
      }
    );
  }

  private initializeFormConfiguration(): void {
    this.formConfiguration = {
      controls: [{
        controlName: 'username',
        type: 'text',
        placeholder: 'E-mail',
        validators: [Validators.required, Validators.email],
        error: 'Email not valid'
      }, {
        controlName: 'password',
        type: 'password',
        placeholder: 'Password',
        validators: [Validators.required],
        error: 'Password not valid'
      }],
      navigation: {
        text: 'Don\'t have an account yet? Register',
        path: ['/register']
      }
    };
  }
}
