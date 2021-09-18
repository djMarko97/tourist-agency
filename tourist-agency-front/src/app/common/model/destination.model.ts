export interface IDestination {
    id?: number;
    name?: string;
    postalCode?: number;
    state?: State;
  }
  
  export class Destination implements IDestination {
    public constructor(public id: number, public name: string,
                       public postalCode: number, public state: State) {
    }
  }
  
  export interface State {
    id?: string;
    name?: string;
  }
  