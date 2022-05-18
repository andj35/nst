import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/model/user.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_URL: string = environment.API_URL;

  constructor(private httpClient: HttpClient) { }

  public loginUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.API_URL + "/user/login", user);
  }

  public registerUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.API_URL + "/user/register", user);
  }
}
