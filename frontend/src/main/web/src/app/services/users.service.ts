import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = 'http://localhost:8080/usermanagement/api/users';

  constructor(private http: HttpClient) {
  }

  static getOptions(): any {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return {headers: headers};
  }

  // sendCredential() {
  //   // const url = 'http://localhost:8181/token';
  //   const encodedCredential = "test" + ':' + "test123";
  //   const basicHeader = 'Basic ' + btoa(encodedCredential);
  //   const headers = new Headers();
  //   headers.append('Content-Type', 'application/x-wwww-form-urlencoded');
  //   headers.append('Authorization', basicHeader);
  //   const opts = new RequestOptions({headers: headers});
  //   return this.http.get(url, opts);
  // }

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, UsersService.getOptions());
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, UsersService.getOptions());
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user, UsersService.getOptions());
  }

  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value, UsersService.getOptions());
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

}
