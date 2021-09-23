import {HttpErrorResponse} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {DomSanitizer} from '@angular/platform-browser';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {Hotel, IDestination, IHotel} from 'src/app/common/model';
import {DestinationService} from 'src/app/services/destination/destination.service';
import {HotelService} from 'src/app/services/hotel/hotel.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  public hotels: IHotel[];
  public destinations: IDestination[];
  public editHotel: IHotel;
  public deleteHotel: IHotel;
  public hotel: Hotel;

  selectedFile: File;


  constructor(private hotelService: HotelService,
              private destinationService: DestinationService,
              private router: Router,
              private snackBar: MatSnackBar,
              private domSanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.getHotels();
    this.getDestinations();
  }

  public getDestinations(): void {
    this.destinationService.getDestinations().subscribe(
      (response: IDestination[]) => {
        this.destinations = response;
        console.log(this.destinations);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getHotels(): void {
    this.hotelService.getHotels().subscribe(
      (response: IHotel[]) => {
        this.hotels = response;
        console.log(this.hotels);
        this.loadImages();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddHotel(addForm: NgForm): void {

    console.log(this.selectedFile);
    const hotelAdd = new FormData();
    hotelAdd.append('imageFile', this.selectedFile, this.selectedFile.name);
    hotelAdd.append('name', addForm.value.name);
    hotelAdd.append('address', addForm.value.address);
    hotelAdd.append('rating', addForm.value.rating);
    hotelAdd.append('destination_id', addForm.value.destination.id);


    hotelAdd.forEach((value, key) => {
      console.log(key + ' ' + value);
    });

    document.getElementById('add-hotel-form').click();
    this.hotelService.addHotel(hotelAdd).subscribe(
      (response: IHotel) => {
        console.log(response);
        this.getHotels();
        this.openSnackBar('Hotel with id: ' + response.id + ' is successfully added!');
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );

    addForm.reset();
  }

  public onDeleteHotel(hotelId: number): void {

    this.hotelService.deleteHotel(hotelId).subscribe(
      (response: string) => {
        console.log(response);
        this.getHotels();
        this.openSnackBar(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateHotel(hotel: IHotel): void {
    console.log(hotel);

    this.hotelService.updateHotel(hotel).subscribe(
      (response: IHotel) => {
        console.log(response);
        this.getHotels();
        this.openSnackBar('Hotel with id:  ' + response.id + ' is successfully updated!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public hotelDetails(id: number): void {
    this.router.navigate(['details', id]);
  }

  public onOpenModal(hotel: IHotel, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'edit') {
      this.editHotel = hotel;
      button.setAttribute('data-target', '#updateHotelModal');
    }
    if (mode === 'delete') {
      this.deleteHotel = hotel;
      button.setAttribute('data-target', '#deleteHotelModal');
    }
    container.appendChild(button);
    button.click();
  }

  // Gets called when the user selects an image
  public onFileChanged(event): void {
    this.selectedFile = event.target.files[0];
  }

  public openSnackBar(message: string): void {
    this.snackBar.open(message.toString(), '',
      {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
  }

  private loadImages(): void {
    for (const hotel of this.hotels) {
      const imageNameParts = hotel.imageName.split('.');
      const imageExtension = imageNameParts[imageNameParts.length - 1];
      hotel.image = 'data:image/' + imageExtension + ';base64,' + hotel.image;
      this.domSanitizer.bypassSecurityTrustResourceUrl(hotel.image);
    }
  }
}
