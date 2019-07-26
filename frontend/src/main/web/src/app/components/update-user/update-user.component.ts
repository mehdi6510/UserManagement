import {Component, OnInit} from "@angular/core";
import {UsersService} from "../../services/users.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../model/User";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html'
})
export class UpdateUserComponent implements OnInit {
  id: string;
  user: User;
  reenterPass: string;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UsersService,) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loadUser(this.id);
  }

  loadUser(id: string): void {
    this.userService.getUser(id).subscribe(data => {
      this.user = data;
      console.log(data);
    }, error => {
      console.log(error)
    });
  }

  update() {
    this.userService.updateUser(this.id, this.user).subscribe(data => {
        console.log(data);
        this.submitted = false;
        this.gotoList();
      }, error => {
        console.log(error)
      }
    );

  }

  onSubmit() {
    this.submitted = true;
    this.update();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }

}
