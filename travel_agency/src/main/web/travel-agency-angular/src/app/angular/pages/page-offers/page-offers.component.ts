import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ITrip } from '../../services/backend-dtos';
import { OffersService } from '../../services/offers-service.service';
import { TicketsService } from '../../services/tickets-service.service';
import { PageComponent } from '../page-component';

@Component({
  selector: 'page-offers',
  templateUrl: './page-offers.component.html',
  styleUrls: ['./page-offers.component.less']
})
export class PageOffersComponent extends PageComponent implements OnInit {

  allOffers: ITrip[];
  availableOffers: ITrip[];
  searchedOffers: ITrip[];

  formCreateTrip;
  formFindTrips;

  constructor(private offersService: OffersService, private ticketService: TicketsService, private formBuilder: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.setUpMenuOptions([
      { id: '1', label: 'Wszystkie oferty' },
      { id: '2', label: 'Dostępne oferty' },
      { id: '3', label: 'Dodaj ofertę' },
      { id: '4', label: 'Wyszukaj oferty' }
    ]);

    this.formCreateTrip = this.formBuilder.group({
      duration: 1,
      startingDate: '2020-01-01',
      name: 'name'
    });

    this.formFindTrips = this.formBuilder.group({
      duration: 1,
      startingDate: '2020-01-01',
      name: 'name'
    });

    this.offersService.getAllTrips()
      .subscribe(response => this.allOffers = response);

    this.offersService.getAvailableTrips()
      .subscribe(response => this.availableOffers = response);
  }

  createNewTrip() {
    const buildedTrip: ITrip = this.formCreateTrip.value;

    this.offersService.createNewTrip(buildedTrip)
      .subscribe(response => console.log(response));
  }

  findTrips() {
    const buildedTrip: ITrip = this.formFindTrips.value;

    this.offersService.findTrips(buildedTrip)
      .subscribe(response => this.searchedOffers = response);
  }

  reserveTicket(trip) {
    this.ticketService.reserveTicket(trip)
      .subscribe(response => console.log(response))
  }
}