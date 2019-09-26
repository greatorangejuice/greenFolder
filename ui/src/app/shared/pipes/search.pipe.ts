import {Pipe, PipeTransform} from '@angular/core';
import {Task} from '../interfaces';

@Pipe({
  name: 'searchTasks',

})
export class SearchPipe implements PipeTransform{
  transform(tasks: Task[], search = '', searchParam = ''): Task[] {
    if (!search.trim()) {
      return tasks;
    }

    return tasks.filter( task => {
      return task[searchParam].toLocaleLowerCase().includes(search.toLocaleLowerCase());
    } );
  }
}
