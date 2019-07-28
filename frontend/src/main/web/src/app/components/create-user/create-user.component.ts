import {UsersService} from '../../services/users.service';
import {User} from '../../model/user';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "../../util/validation-util";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  confirmPassword: string;
  registerForm: FormGroup;
  submitted = false;

  constructor(private userService: UsersService, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.newUser();

    this.registerForm = this.formBuilder.group({
      title: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(6)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    }, {
      validator: MustMatch('password', 'confirmPassword')
      //cellPhone: ['', [Validators.required, Validators.pattern("09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}")]],
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  newUser(): void {
    this.submitted = false;
    this.user = new User();
  }

  save() {
    //stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.userService.createUser(this.user)
      .subscribe(data => {
          console.log(data);
          this.gotoList();
        },
        error => {
          window.alert(error);
          console.log(error);
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
