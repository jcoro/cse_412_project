import { Pipe, PipeTransform } from '@angular/core';
import {Food} from "../model/food";

@Pipe({ name: 'appFilter' })
export class FilterPipe implements PipeTransform {
  /**
   * Pipe filters the list of elements based on the search text provided
   *
   * @param items list of Foods
   * @param searchText search string
   * @param selectedFoodGroup foodGroup number
   * @returns list of elements filtered by search text or []
   */
  transform(items: Food[], searchText: string, selectedFoodGroup: number): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }

    searchText = searchText.toLocaleLowerCase();

    let returnItems = items;

    if (selectedFoodGroup !== 0){
      returnItems = items.filter(f => f.fdGrpCd === selectedFoodGroup);
    }

    returnItems = returnItems.filter(it => {
      return it.longDesc.toLocaleLowerCase().includes(searchText);
    });


    return returnItems;
  }
}
