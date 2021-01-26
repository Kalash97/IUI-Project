export interface ITrip {
    startingDate: string,
    duration: number,
    name: string
};

export interface ITicket {
    price: string,
    type: number,
    tripName: string
};

export interface IUser {
    firstname: string,
    lastname: string
};

export interface IUserCredentials {
    firstname: string,
    lastname: string,
    login: string,
    password: string
};