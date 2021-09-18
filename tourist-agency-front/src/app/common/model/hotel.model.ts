import { Byte } from '@angular/compiler/src/util';
import {IDestination} from './destination.model';

export interface IHotel {
  id?: number;
  address?: string;
  name?: string;
  rating?: number;
  destination?: IDestination;
  imageName?: string;
  imageFile?: string;
  image?: any;
}

export class Hotel implements IHotel {
  public constructor(public id: number, public address: string, public rating: number,
                     public destination: IDestination, public imageName: string, public imageFile: string,
                     public image: any) {
  }
}
