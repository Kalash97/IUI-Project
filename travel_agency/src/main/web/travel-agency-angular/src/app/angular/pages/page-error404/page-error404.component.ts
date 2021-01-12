import { Component, OnInit } from '@angular/core';
import { PageComponent } from '../page-component';

@Component({
  selector: 'page-error404',
  templateUrl: './page-error404.component.html',
  styleUrls: ['./page-error404.component.less']
})
export class PageError404Component extends PageComponent implements OnInit {

  constructor() {
    super();
  }

  ngOnInit(): void {
  }

}
