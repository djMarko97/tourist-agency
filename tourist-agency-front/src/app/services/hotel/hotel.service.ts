import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IHotel } from 'src/app/common/model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HotelService {


  constructor(private httpClient: HttpClient) { }

  public getHotels(): Observable<IHotel[]> {
    return this.httpClient.get<IHotel[]>(`${environment.baseHttpURL}/hotel`);
  }

  public addHotel(hotel: FormData): Observable<IHotel> {
    const HttpUploadOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'multipart/form-data' })
    }
    return this.httpClient.post<IHotel>(`${environment.baseHttpURL}/hotel`, hotel);
  }

  public updateHotel(hotel: IHotel): Observable<IHotel> {
    return this.httpClient.put<IHotel>(`${environment.baseHttpURL}/hotel/${hotel.id}`, hotel);
  }

  public deleteHotel(hotelId: number): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.httpClient.delete<any>(`${environment.baseHttpURL}/hotel/${hotelId}`, { headers, responseType: 'text' as 'json'});
  }

  public getHotelById(hotelId: number): Observable<IHotel> {
    return this.httpClient.get<IHotel>(`${environment.baseHttpURL}/hotel/${hotelId}`);
  }

  public getHotelsForDestination(destinationId: number): Observable<Array<IHotel>>{
    return this.httpClient.get<Array<IHotel>>(`${environment.baseHttpURL}/hotel/destination?destinationId=${destinationId}`);
  }

}
