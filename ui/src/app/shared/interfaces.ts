export interface Task {
  id?: string;
  title: string;
  text: string;
  date: Date;
  author: string;
  status: string;
  isFinished?: boolean;
}

// export interface Subjects {
//   math: boolean;
//   programming: boolean;
//   electricalChains: boolean;
// }

export interface User {
  username?: string;
  email: string;
  password: string;
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

export interface FbAuthResponse {
  idToken: string;
  expiresIn: string;
  localId: string;
}

export interface Users {
  // subjects: string;
  user_id: string;
}
