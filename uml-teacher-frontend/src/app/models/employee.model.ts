import { UserModel } from "./user.model";

export class EmployeeModel {

  userId: number;
  hiringDate: string;
  phoneNumber: string;
  email: string;

  constructor(userId: number) {
    this.userId = userId;
    let now: Date = new Date();
    this.hiringDate = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getSeconds();
    this.phoneNumber = '';
    this.email = '';
  }
} 