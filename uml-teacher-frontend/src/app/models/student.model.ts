import { UserModel } from "./user.model";

export class StudentModel {

  userId: number;
  eduId: number;
  userContacts: string;

  constructor(userId: number) {
    this.userId = userId;
    this.eduId = 1;
    this.userContacts = '';
  }
} 
