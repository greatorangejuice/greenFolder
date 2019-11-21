import {Message} from "./message";

export interface Dialog {
  firstUsername: string;
  secondUsername: string;
  messages: Message[];
}
