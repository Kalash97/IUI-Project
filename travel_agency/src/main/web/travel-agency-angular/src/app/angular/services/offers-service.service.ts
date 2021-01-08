import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  endpointRoot = '/mvc/trip';

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getAllTrips() {
    return this.http.get(this.endpointRoot + '/all-trips');
  }

  createNewTrip(trip) {
    return this.http.post(this.endpointRoot + '/add', trip);
  }

}
