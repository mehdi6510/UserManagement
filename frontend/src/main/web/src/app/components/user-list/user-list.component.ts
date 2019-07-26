import {Observable} from "rxjs";
import {UsersService} from "../../services/users.service";
import {User} from "../../model/user";
import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html"
})
export class UserListComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private userService: UsersService, private router: Router) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
  }

  deleteUser(id: string) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: string){
    this.router.navigate(['details', id]);
  }

  updateUser(id: string) {
    this.router.navigate(['update', id]);
  }

}
