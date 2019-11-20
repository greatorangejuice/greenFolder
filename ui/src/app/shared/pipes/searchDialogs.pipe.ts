import {Pipe, PipeTransform} from "@angular/core";
import {Dialog} from "../_models/dialog";


@Pipe({
  name: 'searchDialog',
})
export class SearchDialogsPipe implements PipeTransform{
  transform(dialogs: Dialog[], search='', searchParam=''): Dialog[] {
    if (!search.trim()) {
      return dialogs;
    }

    return dialogs.filter(dialog => {
      return dialog[searchParam].toLocaleLowerCase().includes(search.toLocaleLowerCase());
    })
  }
}

