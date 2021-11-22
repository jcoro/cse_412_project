import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {JournalEntry} from '../model/journal-entry';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JournalEntryService {

  private journalEntryUrl: string;

  constructor(private http: HttpClient) {
    this.journalEntryUrl = 'http://localhost:8082/api/journalentries'
  }

  // public findAllByUsername(username: string): Observable<JournalEntry[]> {
  //   const url = `${this.journalEntryUrl}/${username}`;
  //   let result = this.http.get<JournalEntry[]>(url);
  //   return result
  // }

  public createJournalEntry(journalEntry: JournalEntry): Observable<JournalEntry>{
    return this.http.post<JournalEntry>(this.journalEntryUrl, journalEntry);
  }

  // public updateJournalEntry(journalEntry: JournalEntry){
  //   const url = `${this.journalEntryUrl}/${journalEntry.j_id}`;
  //   return this.http.put<JournalEntry>(url, journalEntry)
  // }
  //
  // public deleteJournalEntry(journalEntry: JournalEntry){
  //   const url = `${this.journalEntryUrl}/${journalEntry.j_id}`;
  //   return this.http.delete(url);
  // }

}
