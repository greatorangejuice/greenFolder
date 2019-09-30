export interface Task {
  id?: string;
  title: string;
  text: string;
  date: Date;
  author: string;
  status: string;
  isFinished?: boolean;
}

export interface Subjects {
  math: boolean;
  programming: boolean;
  electricalChains: boolean;
}

export interface User {
  email: string;
  password: string;
  name?: string;
  faculty?: string;
  uid?: string;
  vkId?: string;
  returnSecureToken?: boolean;
  subjects?: Subjects;
  math?: any;
  // idToken?: string;
}

export interface FbAuthResponse {
  idToken: string;
  expiresIn: string;
  localId: string;
}

export interface Users {
  // subjects: string;
  user_id: string;
}
