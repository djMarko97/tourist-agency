export interface IBaseUser {
    username: string;
    password: string;
  }
  
  // tslint:disable-next-line:no-empty-interface
  export interface IAuthUser extends IBaseUser {
  }
  
  export interface ICreateUser extends IBaseUser {
    rePassword: string;
    firstName: string;
    lastName: string;
  }
  
  export class AuthUser implements IAuthUser {
    constructor(public username: string,
                public password: string) {
    }
  }
  
  export class CreateUser implements ICreateUser {
    constructor(public username: string,
                public password: string,
                public rePassword: string,
                public firstName: string,
                public lastName: string) {
    }
  }
  
  export interface IUser extends IBaseUser {
    authorities: any;
  }
  
  export class User implements IUser {
    constructor(public authorities: any,
                public username: string,
                public password: string) {
    }
  }