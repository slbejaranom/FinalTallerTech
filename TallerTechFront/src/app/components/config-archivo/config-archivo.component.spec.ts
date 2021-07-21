import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigArchivoComponent } from './config-archivo.component';

describe('ConfigArchivoComponent', () => {
  let component: ConfigArchivoComponent;
  let fixture: ComponentFixture<ConfigArchivoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfigArchivoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigArchivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
