import { Component, OnInit } from '@angular/core';
import { PageComponent } from '../page-component';

@Component({
  selector: 'page-tickets',
  templateUrl: './page-tickets.component.html',
  styleUrls: ['./page-tickets.component.less']
})
export class PageTicketsComponent extends PageComponent implements OnInit {

  constructor() {
    super();
  }

  ngOnInit(): void {
  }

}
