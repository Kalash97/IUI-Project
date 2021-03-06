import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { PageComponent } from '../page-component';
import { TicketsService } from '../../services/tickets-service.service';
import { ITicket, IUser } from '../../services/backend-dtos';
import {UsersService} from "../../services/users-service.service";

@Component({
  selector: 'page-tickets',
  templateUrl: './page-tickets.component.html',
  styleUrls: ['./page-tickets.component.less']
})
export class PageTicketsComponent extends PageComponent implements OnInit {

  myTickets: ITicket[];
  searchedTickets: ITicket[];

  formSearchByUser;

  ticketCancellationSuccess: boolean = false;

  constructor(private formBuilder: FormBuilder, private ticketsService: TicketsService, private userService: UsersService) {
    super();
  }

  ngOnInit(): void {
    this.setUpMenuOptions([
      { id: '1', label: 'Moje bilety' },
    ]);
    if(this.userService?.currentUser?.role=='EMPLOYEE') {
      this._menuOptions['1'] = { id: '2', label: 'Bilety użytkownika' };
    }

    this.formSearchByUser = this.formBuilder.group({
      firstname: 'name',
      lastname: 'last name'
    });

    this.ticketsService.getMyTickets()
      .subscribe(response => this.myTickets = response);
  }

  findTicketsByUser() {
    const buildedUser: IUser = this.formSearchByUser.value;

    this.ticketsService.getUserTickets(buildedUser)
      .subscribe(response => this.searchedTickets = response);
  }

  cancelTicket(ticket) {
    this.ticketsService.cancelTicket(ticket)
      .subscribe(response => {
        console.log(response);
        this.ticketCancellationSuccess = true;
        setTimeout(() => {this.ticketCancellationSuccess = false}, 3000);
      });

  }
}
