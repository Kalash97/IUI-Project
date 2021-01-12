import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITrip } from './backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  endpointRoot = '/mvc/trip';

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getAllTrips(): Observable<ITrip[]> {
    return this.http.get<ITrip[]>(this.endpointRoot + '/all-trips');
  }

  createNewTrip(trip) {
    return this.http.post(this.endpointRoot + '/add', trip);
  }

}
