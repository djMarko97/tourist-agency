import {IDestination} from './destination.model';

export interface ITransportation {
  id?: number;
  type?: TransportationType;
  season?: Season;
  startDestination?: IDestination;
  endDestination?: IDestination;
  price?: number;
}

export class Transportation implements ITransportation {
  public constructor(public id: number, public type: TransportationType, public season: Season,
                     public startDestination: IDestination, public endDestination: IDestination,
                     public price: number) {
  }
}

export enum TransportationType {
  BUS = 'Bus',
  CAR = 'Car',
  PLANE = 'Plane',
  MINI_BUS = 'Mini bus'
}

export enum Season {
  WINTER = 'Winter',
  SPRING = 'Spring',
  SUMMER = 'Summer',
  AUTUMN = 'Autumn'
}
