import { Component, OnInit } from '@angular/core';
import { PageComponent } from '../page-component';

@Component({
  selector: 'page-contact',
  templateUrl: './page-contact.component.html',
  styleUrls: ['./page-contact.component.less']
})
export class PageContactComponent extends PageComponent  implements OnInit {

  constructor() { 
    super();
  }

  ngOnInit(): void {
  }

}
