import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CredentialsModel } from 'src/app/models/credential.model';
import { AuthorizationService } from 'src/app/services/authorization.service';

@Component({
  selector: 'authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.less'],
})
export class AuthorizationComponent implements OnInit {
  public credentials: CredentialsModel = { username: '', password: '' };
  public userFullname: string = '';

  public isAuthorizationErrorOccured: boolean = false;
  public isSignUpMode: boolean = false;

  constructor(private authService: AuthorizationService,
    private router: Router) { }

    ngOnInit() {
      this.authService.getSignUpModeSubject()
        .subscribe(isSignUpMode => {
          this.isSignUpMode = isSignUpMode;
        });
      this.isAuthorizationErrorOccured = false;
      localStorage.setItem('token', '');
    }

  login() {
    this.authService.login(this.credentials).subscribe(userId => {
      this.isAuthorizationErrorOccured = false;
      this.authService.currentUserId = userId;
      this.authService.checkUserHasAdminRole();
      this.router.navigate(['/home']);
    }, error => this.isAuthorizationErrorOccured = true);
  }

  signUp() {
    this.authService.signUp(this.credentials, this.userFullname)
      .subscribe(userId => {
        this.isAuthorizationErrorOccured = false;
        this.authService.currentUserId = userId;
        this.authService.checkUserHasAdminRole();
        this.router.navigate(['/home']);
      }, error => this.isAuthorizationErrorOccured = true);
  }

}
