import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthorizationService } from 'src/app/services/authorization.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.less']
})
export class NavbarComponent implements OnInit {

  public isSignUpMode: boolean;

  constructor(public authService: AuthorizationService,
    public route: ActivatedRoute) { }

  ngOnInit() {
    this.authService.getSignUpModeSubject()
      .subscribe(isSignUpMode => this.isSignUpMode = isSignUpMode);
  }

}
