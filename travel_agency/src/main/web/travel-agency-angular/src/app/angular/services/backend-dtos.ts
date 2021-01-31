export interface ITrip {
    id: number,
    startingDate: string,
    duration: number,
    name: string
};

export interface ITicket {
    id: number,
    price: string,
    type: number,
    tripName: string
};

export interface IUser {
    id: number,
    firstname: string,
    lastname: string,
    email: string,
    role: string
};

export interface IUserCredentials {
    firstname: string,
    lastname: string,
    login: string,
    email: string,
    password: string,
    rememberMe: boolean
};

export interface IUserToken {
  id_token: string,
  person: IUser
}


