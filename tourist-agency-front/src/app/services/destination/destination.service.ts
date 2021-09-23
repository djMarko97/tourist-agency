import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IDestination } from 'src/app/common/model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {

  constructor(private httpClient: HttpClient) { }

  public getDestinations(): Observable<IDestination[]> {
    return this.httpClient.get<IDestination[]>(`${environment.baseHttpURL}/destination`);
  }

}
