import {Hotel, IHotel} from './hotel.model';
import {Room} from './room.model';
import {ITransportation} from './transportation.model';
import {User} from './user.model';
import {IDestination} from './destination.model';

export interface IArrangement {
  id?: number;
  dateFrom?: Date;
  dateTo?: Date;
  numberOfNights?: number;
  meals?: Meals;
  rooms?: Room[];
  hotel?: IHotel;
  transportation?: ITransportation;
  totalPrice?: number;
  people?: number;
  destination?: IDestination;
  numberOfArrangementsLeft?: number;
}

export class Arrangement implements IArrangement {
  constructor(public id: number, public dateFrom: Date, public dateTo: Date, public numberOfNights: number,
              public meals: Meals, public rooms: Room[], public hotel: IHotel, public transportation: ITransportation,
              public totalPrice: number, public people: number, public destination: IDestination,
              public numberOfArrangementsLeft: number) {
  }
}

export enum Meals {
  BB = 'Bed and breakfast',
  PP = 'Half board',
  FB = 'Full board',
  ALL = 'All inclusive',
  WF = 'Without food'
}
