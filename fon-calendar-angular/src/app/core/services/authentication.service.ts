import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loggedUser: User;

  constructor() {
    var value = sessionStorage['loggedUser'];
    if (value) {
      this.loggedUser = JSON.parse(value);
    }
  }

  getLoggedUser(): User {
    return this.loggedUser;
  }

  setLoggedUser(user: User) {
    this.loggedUser = user;
  }

  isUserAuthenticated(): boolean {
    if (!this.loggedUser) {
      return false;
    }
    return this.loggedUser.enabled;
  }

  isLoggedIn(): boolean {
    return this.loggedUser ? true : false;
  }

  getUserInfo(): string {
    return this.loggedUser.firstName + " " + this.loggedUser.lastName;
  }

}
