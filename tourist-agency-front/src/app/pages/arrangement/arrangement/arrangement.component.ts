import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { HotelService } from 'src/app/services/hotel/hotel.service';
import { Meals } from 'src/app/common/model/arrangement.model';
import {
  Arrangement,
  Destination,
  Hotel,
  IArrangement,
  IDestination,
  IHotel,
  ITransportation,
  Room,
  Transportation
} from 'src/app/common/model';
import { TransportationService } from 'src/app/services/transportation/transportation.service';
import { RoomService } from 'src/app/services/room/room.service';
import { DestinationService } from 'src/app/services/destination/destination.service';
import { ArrangementsService } from 'src/app/services/arrangement/arrangements.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { from } from 'rxjs';

@Component({
  selector: 'app-reservations',
  templateUrl: './arrangement.component.html',
  styleUrls: ['./arrangement.component.css']
})
export class ArrangementComponent implements OnInit {

  public arrangementForm: FormGroup;

  public minDate: Date;
  public maxDate: Date;
  public meals = Object.keys(Meals);
  public hotels: Array<IHotel>;
  public transportation: Array<ITransportation>;
  public rooms: Array<Room>;
  public destinations: Array<IDestination>;
  public error: string;
  public hotelError: string;
  public fieldError = 'The field must be filled';


  constructor(public formBuilder: FormBuilder, private arrangementService: ArrangementsService,
              private hotelService: HotelService, private transportationService: TransportationService,
              private roomService: RoomService, private destinationService: DestinationService,
              private snackbar: MatSnackBar) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date();
    this.maxDate = new Date(currentYear + 1, 0, 1);
  }

  ngOnInit(): void {
    debugger
    this.initializeForm();
    this.getTransportation();
    this.getDestinations();
  }

  public getRoomsForHotel(hotelId: number): void {
    this.roomService.getRoomsByHotelId(hotelId).subscribe((data) => {
      this.rooms = data;
    }, (error1 => {
    }));
  }

  public getHotelsForDestination($event: any): void {
    this.hotelService.getHotelsForDestination($event.value).subscribe((data) => {
      this.hotels = data;
      this.hotelError = '';
      this.error = '';
      this.arrangementForm.controls.hotels.reset('');
      if (this.hotels.length === 0) {
        alert('No hotels for this destination');
        this.hotelError = 'No hotels for this destination';
        this.error = 'Must choose another destination to create arrangement';
      }
    });
  }

  public onSubmit(): void {
    if (this.arrangementForm.invalid) {
      this.error = 'Form is invalid, please try again';
      return;
    }
    this.createArrangement();
  }

  public openSnackBar(): void {
    const message = 'You have successfully made arrangement';
    this.snackbar.open(message.toString(), '',
      {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
  }

  private getHotels(): void {
    this.hotelService.getHotels().subscribe((data) => {
      this.hotels = data;
    });
  }

  private getTransportation(): void {
    this.transportationService.getTransportation().subscribe((data) => {
      this.transportation = data;
    });
  }

  private getDestinations(): void {
    this.destinationService.getDestinations().subscribe((data) => {
      this.destinations = data;
    });
  }

  private initializeForm(): void {
    this.arrangementForm = this.formBuilder.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      meals: ['', [Validators.required, Validators.nullValidator]],
      hotels: ['', Validators.required],
      numberOfPeople: ['', Validators.required],
      transportation: ['', Validators.required],
      destinations: ['', Validators.required],
      numOfArrangements: ['', Validators.required]
    });
  }

  private createArrangement(): void {
    const numberOfNights = this.calculateDays();
    this.getRoomsForHotel(this.arrangementForm.controls.hotels.value);
    const hotel = new Hotel(this.arrangementForm.controls.hotels.value, null, null, null, null, null, null);
    const transportation = new Transportation(this.arrangementForm.controls.transportation.value, null, null, null, null, null);
    const destination = new Destination(this.arrangementForm.controls.destinations.value, null, null, null);

    const arrangement: IArrangement = new Arrangement(null, this.arrangementForm.controls.startDate.value,
      this.arrangementForm.controls.endDate.value, numberOfNights,
      this.arrangementForm.controls.meals.value, null, hotel, transportation, 0, this.arrangementForm.controls.numberOfPeople.value,
      destination, this.arrangementForm.controls.numOfArrangements.value);

    this.arrangementService.createArrangement(arrangement).subscribe((data) => {
      console.log('uspjesno');
      this.arrangementForm.reset('');
      this.openSnackBar();
    });
  }

  private calculateDays(): number {
    const differenceInTime = this.arrangementForm.controls.endDate.value.getTime() -
      this.arrangementForm.controls.startDate.value.getTime();
    return differenceInTime / (1000 * 3600 * 24);
  }
}
