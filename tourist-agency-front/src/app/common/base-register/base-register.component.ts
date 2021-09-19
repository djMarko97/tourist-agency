import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-base-register',
  templateUrl: './base-register.component.html',
  styleUrls: ['./base-register.component.css']
})
export class BaseRegisterComponent implements OnInit {

  @Input() public formConfiguration: any;
  @Input() public error: string;
  @Input() public headerMessage: string;
  @Input() public buttonLabel: string;
  @Input() public header: string;

  @Output() public formSubmitted: EventEmitter<any> = new EventEmitter<any>();

  public registrationForm: FormGroup;

  constructor(public formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    if (!this.formConfiguration) {
      this.error = 'Invalid form configuration!;';
      return;
    }

    this.buttonLabel = this.buttonLabel ? this.buttonLabel : 'Register';
    this.header = this.header ? this.header : 'Register';

    this.initializeForm();
  }

  private initializeForm(): void {
    const controlConfig: {[key: string]: any} = {};
    const formControls: {[key: string]: any} = this.formConfiguration.controls;
    for (let i = 0; i < formControls.length; ++i) {
      const formControl = formControls[i];
      const formControlValidators: Array<string> = formControl.validators;

      const controlName: string = formControl.controlName;
      controlConfig[controlName] = ['', formControlValidators];
    }

    this.registrationForm = this.formBuilder.group(controlConfig);
  }

  public onFormSubmit(): void {
    if (this.registrationForm.invalid) {
      return;
    }
    const formValues = this.getFormValues();
    this.formSubmitted.emit({values: formValues});
  }

  public redirect(): void {
    this.router.navigate(this.formConfiguration.navigation.path);
  }

  private getFormValues(): {[key: string]: string} {
    const formControls = this.registrationForm.controls;
    const formControlKeys = Object.keys(formControls);

    const formValues: {[key: string]: string} = {};
    for (let i = 0; i < formControlKeys.length; i++) {
      const formKey = formControlKeys[i];
      const formControl = formControls[formKey];
      formValues[formKey] = formControl.value;
    }

    return formValues;
  }


}
