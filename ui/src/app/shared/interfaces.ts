export interface Task {
  id?: string;
  title: string;
  text: string;
  date: Date;
  author: string;
  status: string;
  isFinished?: boolean;
}

export interface User {
  username: string;
  email?: string;
  password?: string;
  university?: string;
  name?: string;
  surname?: string;
  faculty?: string;
  course?: string;
  uid?: string;
  vkId?: string;
  returnSecureToken?: boolean;
  city?: string;
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
