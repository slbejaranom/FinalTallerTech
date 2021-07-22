import { Component, OnInit } from '@angular/core';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { Status } from 'src/app/models/status';
import { StatusService } from 'src/app/services/status.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  status : Status;

  constructor(private statusService : StatusService) {
    this.status = new Status();
  }

  ngOnInit() {
    this.statusService.getStatus().subscribe( value => {
      this.status = value;
    });

    
  }
}
