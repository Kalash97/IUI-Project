import { Component, OnInit } from '@angular/core';
import { OffersService } from '../../services/offers-service.service';

@Component({
  selector: 'page-offers',
  templateUrl: './page-offers.component.html',
  styleUrls: ['./page-offers.component.less']
})
export class PageOffersComponent implements OnInit {

  menuOptions = [
    { id: '1', label: 'Wszystkie oferty' },
    { id: '2', label: 'Dostępne oferty' },
    { id: '3', label: 'Dodaj ofertę' },
    { id: '4', label: 'Wyszukaj oferty' }
  ];

  selectedMenuOption = this.menuOptions[0];

  allOffers = [];

  constructor(private offersService: OffersService) {
    this.offersService = offersService;
  }

  ngOnInit(): void {
    this.offersService.getAllTrips().subscribe((response) => {
      // this.allOffers = response;
    });
  }

  isOptionActive(id) {
    return this.selectedMenuOption.id == id;
  }

  clickedMenuItem(option) {
    this.selectedMenuOption = option;
  }

}
