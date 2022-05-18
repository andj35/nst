import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'fon-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  loggedIn: boolean = false;
  userInfo: string;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.loggedIn = this.authenticationService.isLoggedIn();
    if (this.loggedIn) {
      this.userInfo = this.authenticationService.getUserInfo();
    }
  }
  
  logout() {
    sessionStorage.removeItem('loggedUser');
  }
}
