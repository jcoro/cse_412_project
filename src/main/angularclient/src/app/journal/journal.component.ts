import {Component} from '@angular/core';
import {faTimes} from '@fortawesome/free-solid-svg-icons';
import {FoodService} from "../service/food.service";
import {JournalEntryService} from "../service/journalEntry.service";
import {Food} from "../model/food";
import {JournalEntry, JournalEntryResponse, JournalEntryUpdate} from "../model/journal-entry";
import {AuthService} from "../service/auth.service";
import {FoodGroupService} from "../service/foodgroup.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'journal',
  templateUrl: './journal.component.html',
})
export class JournalComponent {
  faTimes = faTimes;
  selectedDate: Date = new Date(Date.now());
  searchText = '';
  selectedFoodGroup = new FormControl(0);
  foods: Food[];
  journalEntries: JournalEntryResponse[] = [];
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
              private foodGroupService: FoodGroupService) {
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
      }
    }, error => {
      console.log("ERROR fetching Journal Entries");
      console.log(error);
    });
  }

  changeSelectedDate(value) {
    let dateArray = value.split('-')
    let month = +dateArray[1]
    if (month === 1){
      month = 12;
    } else {
      month = month - 1
    }
    let date = new Date()
    date.setFullYear(dateArray[0])
    date.setMonth(month)
    date.setDate(dateArray[2])
    this.selectedDate = date;
  }

  sameDay(d1, d2) {
    let date1 = new Date(d1);
    let date2 = new Date(d2);
    return date1.getFullYear() === date2.getFullYear() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getDate() === date2.getDate();
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
        long_desc:data.long_desc,
        fdgrp_cd: data.fdgrp_cd,
        measurement: data.measurement,
        nutrient_value: data.nutrient_value
      }
      this.journalEntries.push(journalEntryResponse);
    });
  }

  updateEntryAmount(i, event) {
    this.journalEntries[i].amount = +event.target.value;
    this.editJournalEntry(i);
  }

  updateUnitMeasureSequence(i, event) {
    this.journalEntries[i].seq = +event.target.value;
    this.editJournalEntry(i);
  }

  editJournalEntry(i){
    const entry: JournalEntryUpdate = {
      j_id: this.journalEntries[i].j_id,
      username: this.journalEntries[i].username,
      journal_date: this.journalEntries[i].journal_date,
      order_index: this.journalEntries[i].order_index,
      amount: this.journalEntries[i].amount,
      seq: this.journalEntries[i].seq,
      ndb_no: this.journalEntries[i].ndb_no
    }
    this.JournalEntryService.updateJournalEntry(entry).subscribe(data => {
      this.journalEntries[i] = {
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

  deleteEntry(i) {
    let j_id = this.journalEntries[i].j_id
    this.journalEntries = this.journalEntries.filter((e,index) => index !== i);
    this.JournalEntryService.deleteJournalEntry(j_id).subscribe({
      next: data => {
        console.log('Delete successful');
      },
      error: error => {
        console.error('There was an error deleting the entry', error.message);
      }
    });
  }

  getCaloriesForEntry(i) {
    if (this.journalEntries.length > 0) {
      let gramweight = +this.journalEntries[i].measurement.find(m => m.seq === this.journalEntries[i].seq).gramweight;
      return Math.round((this.journalEntries[i].amount * gramweight * this.journalEntries[i].nutrient_value.find(nv => nv.nutrNo === 208).nutrVal) / 100);
    }
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
