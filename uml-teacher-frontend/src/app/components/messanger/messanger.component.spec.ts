/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MessangerComponent } from './messanger.component';

describe('MessangerComponent', () => {
  let component: MessangerComponent;
  let fixture: ComponentFixture<MessangerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessangerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessangerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
