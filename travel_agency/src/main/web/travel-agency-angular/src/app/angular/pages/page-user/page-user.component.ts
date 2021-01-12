import { Component, OnInit } from '@angular/core';
import { PageComponent } from '../page-component';

@Component({
  selector: 'page-user',
  templateUrl: './page-user.component.html',
  styleUrls: ['./page-user.component.less']
})
export class PageUserComponent extends PageComponent implements OnInit {

  constructor() {
    super();
  }

  ngOnInit(): void {
  }

}
