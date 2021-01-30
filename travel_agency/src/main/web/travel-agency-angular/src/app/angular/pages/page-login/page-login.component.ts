import { Component, OnInit } from '@angular/core';
import {PageComponent} from "../page-component";
import {FormBuilder} from "@angular/forms";
import {UsersService} from "../../services/users-service.service";

@Component({
  selector: 'page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.less']
})
export class PageLoginComponent extends PageComponent implements OnInit {

  formLogin;

  constructor(private formBuilder: FormBuilder, private userService: UsersService) {
    super();
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      email: 'email',
      password: 'password'
    });
  }

}
