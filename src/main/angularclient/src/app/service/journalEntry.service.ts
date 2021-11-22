import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JournalEntry, JournalEntryResponse} from '../model/journal-entry';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JournalEntryService {

  private journalEntryUrl: string = 'http://localhost:8082/api/journalentries';

  constructor(private http: HttpClient) {}

  public findAllByUsername(username: string): Observable<Array<JournalEntryResponse>> {
    return this.http.get<Array<JournalEntryResponse>>('http://localhost:8082/api/journalentries/' + username);
  }

  public createJournalEntry(journalEntry: JournalEntry): Observable<JournalEntry>{
    return this.http.post<any>(this.journalEntryUrl, journalEntry);
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
