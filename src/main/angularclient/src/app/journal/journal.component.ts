import { Component } from '@angular/core';

@Component({
  selector: 'journal',
  templateUrl: './journal.component.html',
})
export class JournalComponent {
  public showSugars:boolean = false;
  public showFat:boolean = false;
  public showVits:boolean = false;
  public toggleSugarButtonName:any = 'Show';
  public toggleFatButtonName:any = 'Show';
  public toggleVitsButtonName:any = 'Show';

  toggleSugars() {
    this.showSugars = !this.showSugars;
    if(this.showSugars)
      this.toggleSugarButtonName = "Hide";
    else
      this.toggleSugarButtonName = "Show";
  }

  toggleFat() {
    this.showFat = !this.showFat;
    if(this.showFat)
      this.toggleFatButtonName = "Hide";
    else
      this.toggleFatButtonName = "Show";
  }

  toggleVits() {
    this.showVits = !this.showVits;
    if(this.showVits)
      this.toggleVitsButtonName = "Hide";
    else
      this.toggleVitsButtonName = "Show";
  }


}
