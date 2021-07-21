import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Status } from '../models/status';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  
  private apiUrl = "http://localhost:3000/api/miscellaneous";

  constructor(private httpClient : HttpClient) { }

  getStatus(): Observable<Status>{
    return this.httpClient.get<Status>(this.apiUrl);
  }
}
