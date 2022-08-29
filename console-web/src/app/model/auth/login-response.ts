export class LoginRespose {
  constructor(
    public token: string,
    public id: number,
    public email: string,
    public name: string,
    public lastname: string,
    public type: string,
    public roles: string[]) {}
}
