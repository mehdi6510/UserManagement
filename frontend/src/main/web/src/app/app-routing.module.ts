import {UserDetailsComponent} from './components/user-details/user-details.component';
import {CreateUserComponent} from './components/create-user/create-user.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './components/user-list/user-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'add', component: CreateUserComponent },
  { path: 'details/:id', component: UserDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
