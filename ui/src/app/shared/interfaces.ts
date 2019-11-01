import {Role} from "./_models/role";

export interface Task {
  secretId?: string;
  executor?: string;
  university?: string;
  taskStatus?: string;
  name: string;
  subject: string;
  course: string;
  customer: string;
  specification: string;
  description: string;
  faculty: string;
  keywords: string;
  // deadline: Date;
  deadline: string;
  type: string;
  offers?: Offer[];
}

export interface Offer {
  executor: string;
  secretId: string;
  bid: number;
  comment: string;
  id?: number;
  offerStatus?: string;
  customer?: string;
}

export interface CustomerResponse {
  customerResponse: 'string';
  offerId?: number;
  executor: string;
  taskSecretId: string;
}

export interface User {
  username?: string;
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
  role?: Role;
}

export interface AllAccountInfo {
  inboxMessages: Message[];
  outgoingMessages: Message[];
  inboxOffers: object[];
  tasksLikeCustomer: Task[];
  tasksLikeExecutor: Task[];
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

export interface Univers {
  allUniversityAsMap: object;
}

export interface Permissions {
  exp: number;
  iat: number;
  roles: Role[];
  sub: string;
  // sub === username;
}
