import { Component, OnInit } from '@angular/core';
import {PageComponent} from "../page-component";
import {FormBuilder} from "@angular/forms";
import {UsersService} from "../../services/users-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.less']
})
export class PageLoginComponent extends PageComponent implements OnInit {

  formLogin;

  failedLogin = false;

  constructor(private formBuilder: FormBuilder, private userService: UsersService, private router: Router) {
    super();
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      email: '',
      password: '',
      rememberMe: false
    });
  }


  tryLogin() {
    this.userService.login(this.formLogin.value)
      .subscribe(
        next => {
          localStorage.setItem("id_token", next.id_token);
          this.router.navigate([''])
        },
        error => {this.failedLogin = true;
          setTimeout(() => {this.failedLogin = false}, 5000);
        }
      )
  }
}
