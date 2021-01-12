import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ITrip } from '../../services/backend-dtos';
import { OffersService } from '../../services/offers-service.service';
import { PageComponent } from '../page-component';

@Component({
  selector: 'page-offers',
  templateUrl: './page-offers.component.html',
  styleUrls: ['./page-offers.component.less']
})
export class PageOffersComponent extends PageComponent implements OnInit {

  allOffers: ITrip[];

  selectedTrip: ITrip = null;

  formCreateTrip;

  constructor(private offersService: OffersService, private formBuilder: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.setUpMenuOptions([
      { id: '1', label: 'Wszystkie oferty' },
      { id: '2', label: 'Dostępne oferty' },
      { id: '3', label: 'Dodaj ofertę' },
      { id: '4', label: 'Wyszukaj oferty' }
    ]);

    this.offersService.getAllTrips()
      .subscribe(response => this.allOffers = response);

    this.formCreateTrip = this.formBuilder.group({
      duration: 1,
      startingDate: '2020-01-01'
    });
  }

  createNewTrip() {
    const buildedTrip: ITrip = this.formCreateTrip.value;

    this.offersService.createNewTrip(buildedTrip)
      .subscribe(response => console.log(response));
  }
}