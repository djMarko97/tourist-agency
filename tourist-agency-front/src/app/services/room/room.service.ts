import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Room, RoomIdentity } from 'src/app/common/model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private httpClient: HttpClient) { }

  public getRoomsByHotelId(hotelId: number): Observable<Room[]> {
    return this.httpClient.get<Room[]>(`${environment.baseHttpURL}/room/hotel/${hotelId}`);
  }

  public addRoom(room: Room): Observable<Room> {
    return this.httpClient.post<Room>(`${environment.baseHttpURL}/room`, room);
  }

  public updateRoom(room: Room): Observable<Room> {
    return this.httpClient.put<Room>(`${environment.baseHttpURL}/room/${room.id.hotel_id, room.id.room_number}`, room);
  }

  public deleteRoom(roomId: RoomIdentity): Observable<any> {
    //const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    console.log(roomId);


    return this.httpClient.request<any>('DELETE', `${environment.baseHttpURL}/room`,
            {
              body: roomId,
              responseType: 'text' as 'json'
            }
    );
  }
}
