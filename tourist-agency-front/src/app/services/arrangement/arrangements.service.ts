import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import { Arrangement, IArrangement } from 'src/app/common/model';
import {environment} from 'src/environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArrangementsService {

  constructor(private httpClient: HttpClient) {
  }

  public getAllArrangements(): Observable<IArrangement[]> {
    return this.httpClient.get<IArrangement[]>(`${environment.baseHttpURL}/reservation`);
  }

  public getArrangementsByUserId(id: number): Observable<IArrangement[]> {
    return this.httpClient.get<IArrangement[]>(`${environment.baseHttpURL}/auth/reservations/${id}`);
  }

  public createArrangement(arrangement: IArrangement): Observable<any> {
    return this.httpClient.post(`${environment.baseHttpURL}/reservation`, arrangement);
  }

  public addReservationForUser(idReservation: number, idUser: number): Observable<Array<IArrangement>> {
    return this.httpClient.post<Array<Arrangement>>(`${environment.baseHttpURL}/auth/reservation/${idReservation}/user/${idUser}`, {});
  }

  public removeReservationForUser(idReservation: number, idUser: number): Observable<Array<IArrangement>> {
    return this.httpClient.delete<Array<Arrangement>>(`${environment.baseHttpURL}/auth/reservation/${idReservation}/user/${idUser}`);
  }
}
