import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConncetionsListComponent } from './conncetions-list.component';

describe('ConncetionsListComponent', () => {
  let component: ConncetionsListComponent;
  let fixture: ComponentFixture<ConncetionsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConncetionsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConncetionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
