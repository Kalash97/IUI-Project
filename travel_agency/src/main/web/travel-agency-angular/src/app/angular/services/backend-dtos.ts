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
    lastname: string
};

export interface IUserCredentials {
    firstname: string,
    lastname: string,
    login: string,
    password: string
};