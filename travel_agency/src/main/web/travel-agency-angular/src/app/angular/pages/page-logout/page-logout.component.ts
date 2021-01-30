import { Component, OnInit } from '@angular/core';
import {PageComponent} from "../page-component";
import {UsersService} from "../../services/users-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'page-logout',
  templateUrl: './page-logout.component.html',
  styleUrls: ['./page-logout.component.less']
})
export class PageLogoutComponent extends PageComponent implements OnInit {

  constructor(private userService: UsersService, private router: Router) {
    super();
  }

  ngOnInit(): void {
    this.userService.currentUser = null;
    localStorage.removeItem("id_token");
    setTimeout(() => {this.router.navigate([''])}, 3000);
  }

}
