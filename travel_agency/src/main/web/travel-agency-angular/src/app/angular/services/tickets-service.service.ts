import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITicket } from './backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  endpointRoot = '/mvc/ticket';

  constructor(private http: HttpClient) { }

  getAllTickets(): Observable<ITicket[]> {
    return this.http.get<ITicket[]>(this.endpointRoot + '/all-tickets');
  }

  getMyTickets(): Observable<ITicket[]> {
    return this.http.get<ITicket[]>(this.endpointRoot + '/my-tickets');
  }

  getUserTickets(user): Observable<ITicket[]> {
    let params = '?';
    params += user.firstname ? 'firstname=' + user.firstname + '&' : '';
    params += user.lastname ? 'lastname=' + user.lastname : '';
    return this.http.get<ITicket[]>(this.endpointRoot + '/of-user' + params);
  }

  cancelTicket(ticket) {
    return this.http.get(this.endpointRoot + '/cancel-ticket?ticketId=' + ticket.id);
  }

  reserveTicket(trip) {
    return this.http.get(this.endpointRoot + '/reserve-ticket?tripId=' + trip.id);
  }
}
