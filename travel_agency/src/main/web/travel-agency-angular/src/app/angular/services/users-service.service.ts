import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {IUser, IUserCredentials, IUserToken} from './backend-dtos';
import {map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  currentUser: IUser;

  endpointRoot = '/mvc/user';

  constructor(private http: HttpClient) {
  }

  getUser(user): Observable<IUser[]> {
    let params = '?';
    params += user.firstname ? 'firstname=' + user.firstname + '&' : '';
    params += user.lastname ? 'lastname=' + user.lastname + '&' : '';
    return this.http.get<IUser[]>(this.endpointRoot + '/find' + params);
  }

  getUserById(id: number) {
    return this.http.get<IUser>(this.endpointRoot + '/id/' + id)
  }

  createUser(user) {
    return this.http.post(this.endpointRoot + '/register', user);
  }

  deleteUser(user) {
    return this.http.delete(this.endpointRoot + '/delete', user);
  }

  deleteMeAndLogout() {
    return this.http.delete(this.endpointRoot + '/delete-me-and-logout');
  }

  getInfoAboutMe() {
    return this.http.get<IUser>(this.endpointRoot + '/info-about-me');
  }

  getLoggedUser() {
    return this.currentUser;
  }

  login(credentials: IUserCredentials) {

    return this.http.post<IUserToken>(this.endpointRoot +'/authenticate', credentials);
    // function authSuccessHandler(response: IUserToken) {
    //
    //   localStorage.setItem("id_token", response.id_token);
    //   return response.id_token;
    // }
    // return this.http.post<IUserToken>('user/authenticate', credentials).pipe(map(authSuccessHandler.bind(this)));
  }
}
