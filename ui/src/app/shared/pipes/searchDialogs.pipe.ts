import {Pipe, PipeTransform} from "@angular/core";
import {PreviewDialog} from "../_models/previewDialog";


@Pipe({
  name: 'searchDialog',
})
export class SearchDialogsPipe implements PipeTransform{
  transform(dialogs: PreviewDialog[], search='', searchParam=''): PreviewDialog[] {
    if (!search.trim()) {
      return dialogs;
    }

    return dialogs.filter(dialog => {
      return dialog[searchParam].toLocaleLowerCase().includes(search.toLocaleLowerCase());
    })
  }
}

