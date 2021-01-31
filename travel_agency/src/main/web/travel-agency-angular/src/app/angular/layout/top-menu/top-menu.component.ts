import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../services/users-service.service";
import {IUser} from "../../services/backend-dtos";

@Component({
  selector: 'top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.less']
})
export class TopMenuComponent implements OnInit {

  currentUser;

  constructor(private userService: UsersService) {
    this.currentUser = userService.currentUser;
  }

  ngOnInit(): void {
  }

  getCurrentUser(): IUser {
    return this.userService.currentUser;
  }

  logout() {

  }

}
