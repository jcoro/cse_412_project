import {Component} from '@angular/core';
import {faTimes, faGripVertical} from '@fortawesome/free-solid-svg-icons';
import {FoodService} from "../service/food.service";
import {JournalEntryService} from "../service/journalEntry.service";
import {Food} from "../model/food";
import {JournalEntry, JournalEntryResponse, JournalEntryUpdate} from "../model/journal-entry";
import {AuthService} from "../service/auth.service";
import {FoodGroupService} from "../service/foodgroup.service";
import {FormControl} from "@angular/forms";
import {CdkDragDrop, CdkDragStart, moveItemInArray} from "@angular/cdk/drag-drop";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'journal',
  templateUrl: './journal.component.html',
  providers: [DatePipe]
})
export class JournalComponent {
  faTimes = faTimes;
  faGripVertical = faGripVertical;
  selectedDate: Date = new Date(Date.now());
  selectedDateString: string;
  searchText = '';
  selectedFoodGroup = new FormControl(0);
  foods: Food[];
  journalEntries: JournalEntryResponse[] = [];
  daysJournalEntries: JournalEntryResponse[] = [];
  showSugars: boolean = false;
  showFat: boolean = false;
  showVits: boolean = false;
  toggleSugarButtonName: any = 'Show';
  toggleFatButtonName: any = 'Show';
  toggleVitsButtonName: any = 'Show';
  foodGroups = [];

  constructor(private FoodService: FoodService,
              private JournalEntryService: JournalEntryService,
              private authService: AuthService,
              private foodGroupService: FoodGroupService,
              private datePipe: DatePipe) {
  }

  ngOnInit(): void {
    this.foodGroupService.findAll().subscribe(data => {
      this.foodGroups.push({foodGrpCode: 0, foodGrpDesc: 'All Food Groups'});
      this.foodGroups.push(...data);
    }, error => {
      console.log("ERROR fetching Foods")
      console.log(error)
    })
    this.FoodService.findAll().subscribe(data => {
      this.foods = data;
    }, error => {
      console.log("ERROR fetching Foods")
      console.log(error)
    });
    this.JournalEntryService.findAllByUsername(this.authService.getUserName()).subscribe(data => {
      if (data != null) {
        this.journalEntries.push(...data);
        this.getSelectedDaysJournalEntries();
      }
    }, error => {
      console.log("ERROR fetching Journal Entries");
      console.log(error);
    });
    this.selectedDateString = this.datePipe.transform(this.selectedDate, 'yyyy-MM-dd');
  }

  changeSelectedDate(value) {
    let dateArray = value.split('-')
    let month = +dateArray[1]
    if (month === 1) {
      month = 12;
    } else {
      month = month - 1
    }
    let date = new Date();
    date.setFullYear(+dateArray[0]);
    date.setMonth(month);
    date.setDate(+dateArray[2]);
    this.selectedDate = date;
    this.getSelectedDaysJournalEntries();
  }

  sameDay(entry) {
    let currentDate;
    if (this == undefined) {
      currentDate = new Date(Date.now());
    } else {
      currentDate = this.selectedDate;
    }
    let date1 = new Date(entry.journal_date);
    let date2 = new Date(currentDate.toISOString());

    return date1.getFullYear() === date2.getFullYear() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getDate() === date2.getDate();
  }

  getSelectedDaysJournalEntries() {
    let date2 = this.selectedDate;
    this.daysJournalEntries = [];
    this.journalEntries.forEach((e) => {
      let date1 = new Date(e.journal_date);
      if (date1.getFullYear() === date2.getFullYear() &&
        date1.getMonth() === date2.getMonth() &&
        date1.getDate() === date2.getDate()){
        this.daysJournalEntries.push(e);
      }
    });
    this.daysJournalEntries.sort((firstEntry, secondEntry) =>
    {
      const res = firstEntry.order_index - secondEntry.order_index
      return res === 0 ? this.getCaloriesWithEntry(firstEntry) - this.getCaloriesWithEntry(secondEntry) : res;
    });
  }

  selectFood(food) {
    this.searchText = '';
    const entry: JournalEntry = {
      username: this.authService.getUserName(),
      amount: 1,
      journal_date: this.selectedDate,
      order_index: this.journalEntries.length,
      ndb_no: food.ndbNo,
      seq: 1
    };

    this.JournalEntryService.createJournalEntry(entry).subscribe(data => {
      const journalEntryResponse: JournalEntryResponse = {
        j_id: data.j_id,
        username: data.username,
        journal_date: data.journal_date,
        order_index: data.order_index,
        amount: data.amount,
        seq: data.seq,
        ndb_no: data.ndb_no,
        long_desc: data.long_desc,
        fdgrp_cd: data.fdgrp_cd,
        measurement: data.measurement,
        nutrient_value: data.nutrient_value
      }
      this.journalEntries.push(journalEntryResponse);
      this.getSelectedDaysJournalEntries();
    });
  }

  updateEntryAmount(entry, event) {
    entry.amount = +event.target.value;
    this.journalEntries[this.journalEntries.indexOf(this.journalEntries.find(e => e.j_id === entry.j_id))].amount = +event.target.value;
    this.journalEntries.find(e => e.j_id === entry.j_id).amount = +event.target.value;
    this.editJournalEntry(entry);
    this.getSelectedDaysJournalEntries();
  }

  updateUnitMeasureSequence(entry, event) {
    entry.seq = +event.target.value;
    this.journalEntries[this.journalEntries.indexOf(this.journalEntries.find(e => e.j_id === entry.j_id))].seq = +event.target.value;
    this.editJournalEntry(entry);
  }
  started(event: CdkDragStart<any>){
    event.source.element.nativeElement.querySelector<HTMLElement>('.first-column').style.width = '400px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.first-column').style.padding = '15px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.amount-column').style.width = '200px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.amount-column').style.padding = '15px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.unit-column').style.width = '200px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.unit-column').style.padding = '15px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.last-column').style.width = '100px';
    event.source.element.nativeElement.querySelector<HTMLElement>('.last-column').style.padding = '15px';
  }
  drop(event: CdkDragDrop<String[]>) {
    const oldIndex = event.previousIndex;
    const newIndex = event.currentIndex;
    const currentOrderIndex = this.daysJournalEntries[newIndex].order_index;
    if (newIndex > oldIndex) {
      for (let i = newIndex; i >= oldIndex; i--) {
        this.daysJournalEntries[i].order_index = (i === oldIndex) ?
          currentOrderIndex : this.daysJournalEntries[i - 1].order_index;
        this.editJournalEntry(this.daysJournalEntries[i]);
      }
    } else if (newIndex < oldIndex) {
      for (let i = newIndex; i <= oldIndex; i++) {
        this.daysJournalEntries[i].order_index = (i === oldIndex) ?
          currentOrderIndex : this.daysJournalEntries[i + 1].order_index;
        this.editJournalEntry(this.daysJournalEntries[i]);
      }
    }
    moveItemInArray(this.daysJournalEntries, oldIndex, newIndex);
  }
  editJournalEntry(updatedEntry) {
    const entry: JournalEntryUpdate = {
      j_id: updatedEntry.j_id,
      username: updatedEntry.username,
      journal_date: updatedEntry.journal_date,
      order_index: updatedEntry.order_index,
      amount: updatedEntry.amount,
      seq: updatedEntry.seq,
      ndb_no: updatedEntry.ndb_no
    }
    this.JournalEntryService.updateJournalEntry(entry).subscribe(data => {
      this.journalEntries[this.journalEntries.indexOf(this.journalEntries.find(e => e.j_id === data.j_id))] = {
        j_id: data.j_id,
        username: data.username,
        journal_date: data.journal_date,
        order_index: data.order_index,
        amount: data.amount,
        seq: data.seq,
        ndb_no: data.ndb_no,
        long_desc: data.long_desc,
        fdgrp_cd: data.fdgrp_cd,
        measurement: data.measurement,
        nutrient_value: data.nutrient_value
      };
    });
  }

  deleteEntry(entry) {
    let j_id = entry.j_id;
    this.JournalEntryService.deleteJournalEntry(j_id, entry).subscribe({
      next: data => {
        console.log('Delete successful ', data);
        this.journalEntries = this.journalEntries.filter((e) => e.j_id !== j_id);
        this.getSelectedDaysJournalEntries();
      },
      error: error => {
        console.error('There was an error deleting the entry', error.message);
      }
    });
  }

  getCaloriesForEntry(i) {
    if (this.daysJournalEntries.length > 0) {
      let gramweight = +this.daysJournalEntries[i].measurement.find(m => m.seq === this.daysJournalEntries[i].seq).gramweight;
      return Math.round((this.daysJournalEntries[i].amount * gramweight * this.daysJournalEntries[i].nutrient_value.find(nv => nv.nutrNo === 208).nutrVal) / 100);
    }
  }

  getCaloriesWithEntry(entry) {
    let gramweight = +entry.measurement.find(m => m.seq === entry.seq).gramweight;
    return Math.round((entry.amount * gramweight * entry.nutrient_value.find(nv => nv.nutrNo === 208).nutrVal) / 100);
  }

  calculateTotal(nutrientNumber) {
    let total = 0
    if (this.daysJournalEntries.length > 0) {
      this.daysJournalEntries.forEach(function (entry) {
        let gramweight = +entry.measurement.find(m => m.seq === entry.seq).gramweight;
        let nutrient_value = entry.nutrient_value.find(nv => nv.nutrNo === nutrientNumber);
        if (nutrient_value != undefined){
          total += (entry.amount * gramweight * entry.nutrient_value.find(nv => nv.nutrNo === nutrientNumber).nutrVal) / 100;
        }
      })
    }
    return Math.round(total);
  }

  calculatePercentage(nutrientNumber, rda) {
    let total = this.calculateTotal(nutrientNumber);
    return Math.round((total / rda) * 100);
  }

  calculateWeight() {
    let total = 0
    if (this.daysJournalEntries.length > 0) {
      this.daysJournalEntries.forEach(function (entry) {
        total += +entry.measurement.find(m => m.seq === entry.seq).gramweight;
      })
    }
    return total;
  }

  calculateCaloriesFrom(nutrientNumber){
    let calories = this.calculateTotal(208);
    let nutrientTotal = this.calculateTotal(nutrientNumber)
    let caloriesFromNutrient = 0;
    if(nutrientNumber === 203 || nutrientNumber === 205){
      caloriesFromNutrient = nutrientTotal * 4;
    } else if (nutrientNumber === 204){
      caloriesFromNutrient = nutrientTotal * 9;
    }
    return Math.round((caloriesFromNutrient / calories) * 100);
  }

  toggleSugars() {
    this.showSugars = !this.showSugars;
    if (this.showSugars)
      this.toggleSugarButtonName = "Hide";
    else
      this.toggleSugarButtonName = "Show";
  }

  toggleFat() {
    this.showFat = !this.showFat;
    if (this.showFat)
      this.toggleFatButtonName = "Hide";
    else
      this.toggleFatButtonName = "Show";
  }

  toggleVits() {
    this.showVits = !this.showVits;
    if (this.showVits)
      this.toggleVitsButtonName = "Hide";
    else
      this.toggleVitsButtonName = "Show";
  }


}
