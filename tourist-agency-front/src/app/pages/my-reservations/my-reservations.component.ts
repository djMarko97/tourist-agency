import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IArrangement } from 'src/app/common/model';
import { ArrangementsService } from 'src/app/services/arrangement/arrangements.service';
import { AuthService } from 'src/app/services/authority/auth.service';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService,
              private arrangementService: ArrangementsService,
              private modalService: NgbModal,
              private snackBar: MatSnackBar) { }

   arrangements: IArrangement[];

   userId: number;
   ngOnInit(): void {
     this.userId = this.authService.getCurrentUserValue().id;
     console.log(this.userId);
     this.loadArrangements();

   }

   public loadArrangements(): void{
     this.arrangementService.getArrangementsByUserId(this.userId).subscribe(
       data => {
         this.arrangements = data;
         this.arrangements.forEach(el => console.log(el));

       }
     );
   }

   public cancelReservation(arrangementId: number): void{
     this.arrangementService.removeReservationForUser(arrangementId, this.userId).subscribe((data) => {
       this.arrangements = data;
       this.openSnackBar();
     });
   }

   public openSnackBar(): void {
     const message = 'You canceled your reservation. Please let us know why';
     this.snackBar.open(message.toString(), '',
       {duration : 3000,
       verticalPosition: 'top',
       horizontalPosition: 'center'});
   }

 }
