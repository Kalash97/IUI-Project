import { Component, OnInit } from '@angular/core';
import {PageComponent} from "../page-component";
import {FormBuilder, Validators} from "@angular/forms";
import {UsersService} from "../../services/users-service.service";
import {Router} from "@angular/router";
import {HttpBackend} from "@angular/common/http";
import {IUserCredentials} from "../../services/backend-dtos";

@Component({
  selector: 'page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.less']
})
export class PageLoginComponent extends PageComponent implements OnInit {

  formLogin;

  formCreateUser;

  failedLogin = false;

  constructor(private formBuilder: FormBuilder, private userService: UsersService, private router: Router, private httpBackend: HttpBackend) {
    super();
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      email: '',
      password: '',
      rememberMe: false
    });
    this.setUpMenuOptions([
      {id: '1', label: 'Zaloguj się'},
      {id: '2', label: 'Załóż nowe konto'}
    ]);

    this.formCreateUser = this.formBuilder.group({
      firstname: '',
      lastname: '',
      email: '',
      password: ''
    });
  }

  tryLogin() {
    this.userService.login(this.formLogin.value)
      .subscribe(
        next => {
          this.userService.currentUser = next.person;
          localStorage.setItem("id_token", next.id_token);
          this.router.navigate([''])
        },
        error => {this.failedLogin = true;
          setTimeout(() => {this.failedLogin = false}, 5000);
        }
      );
  }
  createUser() {
    const buildedUser: IUserCredentials = this.formCreateUser.value;

    this.userService.createUser(buildedUser)
      .subscribe(response => console.log(response));
  }

}
