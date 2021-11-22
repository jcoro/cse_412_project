import {Component} from '@angular/core';
import {DatePipe} from "@angular/common";
import {faTimes} from '@fortawesome/free-solid-svg-icons';
import {FoodService} from "../service/food.service";
import {JournalEntryService} from "../service/journalEntry.service";
import {Food} from "../model/food";
import {JournalEntry} from "../model/journal-entry";

@Component({
  selector: 'journal',
  templateUrl: './journal.component.html',
})
export class JournalComponent {
  faTimes = faTimes;
  datePipe = new DatePipe('en-US');
  now = new Date();
  selectedDate = this.datePipe.transform(this.now, 'YYYY-MM-dd');
  searchText = '';
  selectedFoodGroup: number = 0;
  foods: Food[];
  journalEntries: JournalEntry[] = [];
  showSugars: boolean = false;
  showFat: boolean = false;
  showVits: boolean = false;
  toggleSugarButtonName: any = 'Show';
  toggleFatButtonName: any = 'Show';
  toggleVitsButtonName: any = 'Show';
  constructor(private FoodService: FoodService, private JournalEntryService: JournalEntryService) {
  }

  ngOnInit(): void {
    this.FoodService.findAll().subscribe(data => {
      this.foods = data;
    }, error => {
      console.log("ERROR fetching Foods")
      console.log(error)
    });
  }

  changeSelectedDate(value) {
    this.selectedDate = value;
  }

  selectFood(food) {
    this.searchText = '';
    let entry = new JournalEntry();
    entry.username = 'user'
    entry.amount = 1;
    entry.journalDate = this.selectedDate;
    entry.orderIndex = this.journalEntries.length;
    entry.ndbNo = food.ndbNo;
    this.JournalEntryService.createJournalEntry(entry);
  }

  updateEntryAmount(i, event) {
    this.journalEntries[i].amount = +event.target.value;
  }

  updateUnitAmount(i, event) {
    this.journalEntries[i].seq = +event.target.value;
  }

  deleteEntry(i) {
    this.journalEntries = this.journalEntries.filter((e,index) => index !== i);
  }

  // getCaloriesForEntry(i) {
  //   if (this.journalEntries.length > 0) {
  //     let gramweight = +this.journalEntries[i].measurements.find(m => m.seq === this.journalEntries[i].seq).gramweight;
  //     return Math.round((this.journalEntries[i].amount * gramweight * this.journalEntries[i].nutrient_value.find(nv => nv.nutr_no === 208).nutr_val) / 100);
  //   }
  // }

  foodGroupUpdated(event){
    this.selectedFoodGroup = +event.target.value;
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
