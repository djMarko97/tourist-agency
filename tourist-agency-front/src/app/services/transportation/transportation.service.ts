import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { ITransportation } from 'src/app/common/model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TransportationService {

  constructor(private httpClient: HttpClient) { }

  public getTransportation(): Observable<Array<ITransportation>> {
    return this.httpClient.get<Array<ITransportation>>(`${environment.baseHttpURL}/transportation`);
  }
}
