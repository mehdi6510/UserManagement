import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = 'http://localhost:8080/usermanagement/api/users';

  constructor(private http: HttpClient) {}

  getUser(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  createUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  updateUser(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

}
