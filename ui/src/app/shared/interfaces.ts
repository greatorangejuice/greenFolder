export interface Task {
  id: number;
  name: string;
  subject: string;
  customer: string;
  executor: string;
  specification: string;
  faculty: string;
  keywords: string;
  taskStatus: string;
  date: Date;
}

export interface User {
  username: string;
  email?: string;
  password?: string;
  name?: string;
  surname?: string;
  city?: string;
  university?: string;
  faculty?: string;
  course?: string;
  userStatus?: string;
  webMoneyAccount?: string;
  roles?: object[];
}

export interface AllAccountInfo {
  inboxMessages: Message[];
  outgoingMessages: Message[];
  inboxOffers: object[];
  tasksLikeCustomer: object[];
  tasksLikeExecutor: object[];
  user: User;
}

export interface Message {
  id: number;
  messageBody: string;
  messageHead: string;
  userFrom: string;
  userTo: string;
}

export interface Token {
  idToken: string
}

export interface loginResponse {
  token: string;
  tokenLifeTime: string;
}

export interface Users {
  user_id: string;
}

export interface Passwords {
  currentPassword: string;
  newPassword: string;
}
