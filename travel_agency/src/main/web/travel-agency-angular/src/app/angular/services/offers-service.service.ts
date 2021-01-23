import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITrip } from './backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  endpointRoot = '/mvc/trip';

  constructor(private http: HttpClient) { }

  getAllTrips(): Observable<ITrip[]> {
    return this.http.get<ITrip[]>(this.endpointRoot + '/all-trips');
  }

  getAvailableTrips(): Observable<ITrip[]> {
    return this.http.get<ITrip[]>(this.endpointRoot + '/available-trips');
  }

  createNewTrip(trip) {
    return this.http.post(this.endpointRoot + '/add', trip);
  }

  findTrips(trip): Observable<ITrip[]> {
    let params = '?';
    params += trip.startingDate ? 'startingDate=' + trip.startingDate + '&' : '';
    params += trip.duration ? 'duration=' + trip.duration + '&' : '';
    params += trip.name ? 'name=' + trip.name + '&' : '';
    return this.http.get<ITrip[]>(this.endpointRoot + '/find-trips' + params);
  }

}
