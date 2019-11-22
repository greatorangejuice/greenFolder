export interface Message {
  date: Date;
  id: number;
  message: string;
  userFrom: string;
  userTo: string;
  viewed: boolean;
}
