import { Pipe, PipeTransform } from '@angular/core';
import {DatePipe} from "@angular/common";

@Pipe({ name: 'dateFilter' })
export class DateFilterPipe implements PipeTransform {
  /**
   * Pipe filters the list of elements based on the search text provided
   *
   * @param items list of journal entries
   * @param selectedDate date string
   * @returns list of elements filtered by selectedDate or []
   */
  transform(items: any[], selectedDate: string): any[] {
    if (!items) {
      return [];
    }
    let datePipe = new DatePipe('en-US');

    return items.filter(it => {
      return datePipe.transform(it.journal_date, 'YYYY-MM-dd') === selectedDate;
    });
  }
}
