import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorizationComponent } from './authorization/authorization.component';
import { AuthorizationService } from '../services/authorization.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule
  ],
  declarations: [AuthorizationComponent],
  providers: [AuthorizationService]
})
export class UmlTeacherComponentsModule { }
