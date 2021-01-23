import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUser } from './backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  endpointRoot = '/mvc/user';

  constructor(private http: HttpClient) { }

  getUser(user): Observable<IUser[]> {
    let params = '?';
    params += user.firstname ? 'firstname=' + user.firstname + '&' : '';
    params += user.lastname ? 'lastname=' + user.lastname + '&' : '';
    return this.http.get<IUser[]>(this.endpointRoot + '/find' + params);
  }

  createUser(user) {
    return this.http.post(this.endpointRoot + '/add', user);
  }

  deleteUser(user) {
    return this.http.delete(this.endpointRoot + '/delete', user);
  }

  deleteMeAndLogout(){
    return this.http.delete(this.endpointRoot + '/delete-me-and-logout');
  }

  getInfoAboutMe() {
    return this.http.get<string[]>(this.endpointRoot + '/info-about-me');
  }
}
