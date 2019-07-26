import {UsersService} from '../../services/users.service';
import {User} from '../../model/user';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  reenterPass: string;
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
          console.log(error)
        });
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
