import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthGuard } from './guards/auth.guard';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UmlTeacherComponentsModule } from './components/uml-teacher-components.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ChartsModule } from 'ng2-charts';
import { DurationPipe } from './components/course-preveiw-card/duration.pipe';
import { NotificationComponent } from './components/notification/notification.component';

@NgModule({
  declarations: [
    AppComponent, NavbarComponent, NotificationComponent
  ],
  imports: [
    CommonModule, BrowserModule, RouterModule, FormsModule, HttpClientModule, AppRoutingModule, UmlTeacherComponentsModule,
    ChartsModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
