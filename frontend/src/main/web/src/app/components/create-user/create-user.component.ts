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
  submitted = false;

  constructor(private userService: UsersService, private router: Router) {
  }

  ngOnInit() {
  }

  newUser(): void {
    this.submitted = false;
    this.user = new User();
  }

  save() {
    this.userService.createUser(this.user)
      .subscribe(data => {
          console.log(data);
          this.newUser();
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
    this.submitted = false;
    //this.registerForm.reset();
    this.router.navigate(['users']);
  }

}
