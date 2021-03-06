import {UsersService} from '../../services/users.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "../../util/validation-util";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  error: string = '';

  constructor(private userService: UsersService, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.newUser();

    this.registerForm = this.formBuilder.group({
      title: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
      cellPhone: new FormControl('', [Validators.pattern('09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}')]),
      email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(100)]),
      username: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]),
      password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]),
      isAdmin: new FormControl(false)
    }, {
      validators: [MustMatch('password', 'confirmPassword')]
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  newUser(): void {
    this.submitted = false;
  }

  save() {
    //stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.userService.createUser(this.registerForm.value)
      .subscribe(data => {
        console.log(data);
        this.gotoList();

      }, excp => {
        console.log(excp);
        if (excp.error instanceof Array) {
          for (let err of excp.error) {
            this.error += `${err.message}.\n`;
          }
        } else {
          this.error = `${excp.error.message}`;
        }
      });
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }

  onReset() {
    this.registerForm.reset();
    this.gotoList();
  }

}
