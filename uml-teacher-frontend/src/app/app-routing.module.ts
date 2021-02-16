import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorizationComponent } from './components/authorization/authorization.component';
import { CheckAnswerProcessComponent } from './components/check-answer-process/check-answer-process.component';
import { HomeEmployeeComponent } from './components/home-employee/home-employee.component';
import { HomeComponent } from './components/home/home.component';
import { MessangerComponent } from './components/messanger/messanger.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SettingsComponent } from './components/settings/settings.component';
import { StudyProcessComponent } from './components/study-process/study-process.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: AuthorizationComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full', canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'study/course/:courseId/task/:taskNumber', component: StudyProcessComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'messanger', component: MessangerComponent, canActivate: [AuthGuard] },
  { path: 'settings', component: SettingsComponent, canActivate: [AuthGuard] },
  { path: 'home-employee', component: HomeEmployeeComponent, canActivate: [AuthGuard] },
  { path: 'check/answer/:answerId', component: CheckAnswerProcessComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'login' },

  //FOR TESTING PURPOSE
  // { path: 'login', component: AuthorizationComponent },
  // { path: 'home', component: HomeComponent },
  // { path: 'study/course/:courseId/task/:taskId', component: StudyProcessComponent },
  // { path: 'profile', component: ProfileComponent },
  // { path: 'messanger', component: MessangerComponent },
  // { path: 'settings', component: SettingsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
