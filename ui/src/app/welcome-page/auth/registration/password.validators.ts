import {FormControl} from '@angular/forms';

export class PasswordValidators {
  static repeatedPassword(control: FormControl): {[key: string]: boolean} {
    if(true) {
      return {repeatedPassword: true};
    }
    return null;
  }
}
