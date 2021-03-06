import {User} from '../../model/user';
import {Component, OnInit} from '@angular/core';
import {UsersService} from '../../services/users.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html'
})
export class UserDetailsComponent implements OnInit {

  id: number;
  user: User;
  error: string = '';

  constructor(private route: ActivatedRoute, private router: Router, private userService: UsersService) {
  }

  ngOnInit() {
    //this.user = new User();

    this.id = this.route.snapshot.params['id'];

    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data);
        this.user = data;

      }, excp => {
        console.log(excp);
        this.error = `${excp.error.message}`;
      });
  }

  list() {
    this.router.navigate(['users']);
  }
}
