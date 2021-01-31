import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { IUser, IUserCredentials } from '../../services/backend-dtos';
import { PageComponent } from '../page-component';
import { UsersService } from '../../services/users-service.service';

@Component({
  selector: 'page-user',
  templateUrl: './page-user.component.html',
  styleUrls: ['./page-user.component.less']
})
export class PageUserComponent extends PageComponent implements OnInit {

  infoAboutMe: string[];
  searchedUsers: IUser[];
  formCreateUser;
  formSearchUser;

  constructor(private formBuilder: FormBuilder, private userService: UsersService) {
    super();
  }

  ngOnInit(): void {
    this.setUpMenuOptions([
      { id: '1', label: 'Informacje o mnie' },
      // this.userService?.currentUser?.role=='EMPLOYEE'?{ id: '2', label: 'Wyszukaj użytkownika' }:null,
    ]);
    if(this.userService?.currentUser?.role=='EMPLOYEE') {
      this._menuOptions['1'] = { id: '2', label: 'Wyszukaj użytkownika' };
    }

    this.formSearchUser = this.formBuilder.group({
      firstname: 'name',
      lastname: 'last name'
    });

    this.userService.getInfoAboutMe()
      .subscribe(response => {
        this.infoAboutMe[0] = response.firstname;
      });
  }

  findUser() {
    const buildedUser: IUser = this.formSearchUser.value;

    this.userService.getUser(buildedUser)
      .subscribe(response => this.searchedUsers = response);
  }

  deleteUser(){
    this.userService.deleteMeAndLogout()
      .subscribe(response => console.log(response));
  }
}
