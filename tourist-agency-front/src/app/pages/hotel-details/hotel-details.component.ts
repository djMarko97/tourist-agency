import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { IHotel, Room, RoomIdentity, RoomType } from 'src/app/common/model';
import { HotelService } from 'src/app/services/hotel/hotel.service';
import { RoomService } from 'src/app/services/room/room.service';

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {
  id: number;
  hotel: IHotel;
  rooms: Room[];
  roomType = RoomType;
  enumKeys =[];
  room: Room;
  roomIdentity: RoomIdentity={};
  public editRoom: Room;
  public deleteRoom: Room;

  constructor(private route: ActivatedRoute,private router: Router,
              private hotelService: HotelService,
              private roomService: RoomService,
              private fb: FormBuilder,
              private _snackBar: MatSnackBar,
              private domSanitizer: DomSanitizer)
              {
                this.enumKeys=Object.keys(this.roomType);
              }

  ngOnInit(): void {
    //this.hotel = new Hotel();

    console.log(this.enumKeys);

    this.id = this.route.snapshot.params['id'];

    this.hotelService.getHotelById(this.id)
      .subscribe(
        (response: IHotel) => {
          console.log(response);
          this.hotel = response;
          this.loadImage();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );

      this.getRooms();
  }

  public back(){
    this.router.navigate(['hotels']);
  }

  public getRooms(): void {
    this.roomService.getRoomsByHotelId(this.id).subscribe(
      (response: Room[]) => {
        this.rooms = response;
        console.log(this.rooms);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddRoom(addForm: NgForm): void {

    this.room = addForm.value;
    this.roomIdentity.hotel_id = this.id;
    this.roomIdentity.room_number = addForm.value.room_number;
    this.room.id = this.roomIdentity;

    this.room.hotel = {};
    this.room.hotel.id = this.hotel.id;
    this.room.hotel.name = this.hotel.name;
    this.room.hotel.address = this.hotel.address;
    this.room.hotel.rating = this.hotel.rating;
    this.room.hotel.destination = this.hotel.destination;
    this.room.available = true;
    console.log(this.room);

    let flag = this.checkIfExists(this.room);
    console.log(flag);
    document.getElementById('add-room-form').click();
    if(flag == true){
      this.openSnackBar("Room " + this.room.id.room_number + " already exists!");

    }else{

      this.roomService.addRoom(this.room).subscribe(
        (response: Room) => {
          console.log(response);
          this.getRooms();
          this.openSnackBar("Room " + response.id.room_number + " is successfully added!");
          addForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
      );
    }

    addForm.reset();
  }

  public onDeleteRoom(roomId: RoomIdentity): void {

    console.log(roomId);
    this.roomService.deleteRoom(roomId).subscribe(
      (response: String) => {
        console.log(response);
        this.getRooms();
        this.openSnackBar(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateRoom(room: Room, room_number: string): void {

    this.roomIdentity.hotel_id = this.id;
    this.roomIdentity.room_number = room_number;
    room.id = this.roomIdentity;
    room.hotel = {};
    room.hotel.id = this.hotel.id;
    room.hotel.name = this.hotel.name;
    room.hotel.address = this.hotel.address;
    room.hotel.rating = this.hotel.rating;
    room.hotel.destination = this.hotel.destination;
    room.available = true;

    console.log(room);

    this.roomService.updateRoom(room).subscribe(
      (response: Room) => {
        console.log(response);
        this.getRooms();
        this.openSnackBar("Room " + room.id.room_number + " is successfully updated!");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(room: Room, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'edit') {
      this.editRoom = room;
      button.setAttribute('data-target', '#updateRoomModal');
    }
    if (mode === 'delete') {
      this.deleteRoom = room;
      button.setAttribute('data-target', '#deleteRoomModal');
    }
    container.appendChild(button);
    button.click();
  }

  public openSnackBar(message: String){
    this._snackBar.open(message.toString(), '',
    {
      duration : 3000,
      verticalPosition: 'top',
      horizontalPosition: 'center'
    });
  }

  public checkIfExists(room: Room): boolean{
    for(let index in this.rooms){
      if((this.rooms[index].id.hotel_id == room.id.hotel_id)
          && (this.rooms[index].id.room_number== room.id.room_number)){
        return true;
      }
    }
    return false;
  }

  private loadImage(): void {

      const imageNameParts = this.hotel.imageName.split('.');
      const imageExtension = imageNameParts[imageNameParts.length - 1];
      this.hotel.image = 'data:image/' + imageExtension + ';base64,' + this.hotel.image;
      this.domSanitizer.bypassSecurityTrustResourceUrl(this.hotel.image);
  }

}
