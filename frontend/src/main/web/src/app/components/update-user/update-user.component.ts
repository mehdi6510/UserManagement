import {Component, OnInit} from "@angular/core";
import {UsersService} from "../../services/users.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../model/User";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html'
})
export class UpdateUserComponent implements OnInit {

  id: number;
  user: User;
  submitted = false;
  error: string = '';
  updateForm: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private userService: UsersService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.loadUser(this.id);

    this.updateForm = this.formBuilder.group({
      id: new FormControl(null),
      title: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]),
      cellPhone: new FormControl('', [Validators.pattern('09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}')]),
      email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(100)]),
      username: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]),
      isAdmin: new FormControl(false),
      password: new FormControl(''),
    });
  }

  get f() {
    return this.updateForm.controls;
  }

  loadUser(id: number): void {
    this.userService.getUser(id).subscribe(data => {
      console.log(data);
      this.user = data;
      this.submitted = false;
      this.changeUserInfo(this.user);

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

  update() {
    //stop here if form is invalid
    if (this.updateForm.invalid) {
      return;
    }

    this.userService.updateUser(this.id, this.updateForm.value)
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

  changeUserInfo(user: User) {
    this.updateForm.controls['id'].setValue(user.id);
    this.updateForm.controls['title'].setValue(user.title);
    this.updateForm.controls['firstName'].setValue(user.firstName);
    this.updateForm.controls['lastName'].setValue(user.lastName);
    this.updateForm.controls['email'].setValue(user.email);
    this.updateForm.controls['cellPhone'].setValue(user.cellPhone);
    this.updateForm.controls['username'].setValue(user.username);
    this.updateForm.controls['password'].setValue(user.password);
    this.updateForm.controls['isAdmin'].setValue(user.isAdmin);
  }

  onSubmit() {
    this.submitted = true;
    this.update();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }

  onReset() {
    this.submitted = false;
    this.router.navigate(['users']);
  }

}
