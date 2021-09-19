import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {AuthUser, CreateUser, IUser} from '../../common//model/user.model';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly BASE_URL = 'spring/tourist-agency/auth';

  private readonly LOCAL_STORAGE_USER: string = 'currentUser';
  private readonly LOCAL_STORAGE_USER_TOKEN: string = 'userToken';

  private readonly API_REGISTER = '/register';
  private readonly API_LOGIN = '/login';

  private currentUserSubject: BehaviorSubject<any>;
  private currentUser: Observable<any>;

  constructor(private httpClient: HttpClient) {
    let user = localStorage.getItem(this.LOCAL_STORAGE_USER);
    user = user ? JSON.parse(user) : null;
    this.currentUserSubject = new BehaviorSubject<any>(user);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public isAuthenticated(): boolean {
    return !!localStorage.getItem(this.LOCAL_STORAGE_USER);
  }

  public getCurrentUserValue(): any {
    return this.currentUserSubject.value;
  }

  public getUserToken(): any {
    if (localStorage.getItem(this.LOCAL_STORAGE_USER_TOKEN)) {
      return JSON.parse(localStorage.getItem(this.LOCAL_STORAGE_USER_TOKEN) as string).token;
    }
  }

  public register(createUser: CreateUser): Observable<IUser> {
    const url = this.BASE_URL + this.API_REGISTER;
    return this.httpClient.post<IUser>(url, createUser);
  }

  public login(authUser: AuthUser): Observable<IUser> {
    const url = this.BASE_URL + this.API_LOGIN;

    return this.httpClient.post<IUser>(url, authUser, {observe: 'response'}).pipe(
      map(response => {
        localStorage.setItem(this.LOCAL_STORAGE_USER, JSON.stringify(response.body));
        localStorage.setItem(this.LOCAL_STORAGE_USER_TOKEN, JSON.stringify({token: response.headers.get('authorization')}));
        this.currentUserSubject.next(response.body);
        return response.body as IUser;
      })
    );
  }

  public logout(): void {
    localStorage.removeItem(this.LOCAL_STORAGE_USER);
    localStorage.removeItem(this.LOCAL_STORAGE_USER_TOKEN);
    this.currentUserSubject.next(null);
  }

  public isAdmin(): boolean {
    return this.checkLoggedInUser('ROLE_ADMIN');
  }

  private checkLoggedInUser(role: string): boolean {
    const user = this.getCurrentUserValue();
    if (user) {
      const authorities = user.authorities;
      if (authorities) {
        for (const authority of authorities) {
          if (authority.authority === role) {
            return true;
          }
        }
      }
    }
    return false;
  }

}
