import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JournalEntry, JournalEntryResponse, JournalEntryUpdate} from '../model/journal-entry';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JournalEntryService {

  private journalEntryUrl: string = 'http://localhost:8082/api/journalentries';

  constructor(private http: HttpClient) {
  }

  public findAllByUsername(username: string): Observable<Array<JournalEntryResponse>> {
    return this.http.get<Array<JournalEntryResponse>>(this.journalEntryUrl + '/' + username);
  }

  public createJournalEntry(journalEntry: JournalEntry): Observable<JournalEntryResponse>{
    return this.http.post<any>(this.journalEntryUrl, journalEntry);
  }

  public updateJournalEntry(journalEntryUpdate: JournalEntryUpdate): Observable<JournalEntryResponse>{
    const url = `${this.journalEntryUrl}/${journalEntryUpdate.j_id}`;
    return this.http.post<any>(url, journalEntryUpdate)
  }

  public deleteJournalEntry(j_id: number, journalEntry){
    const url = `${this.journalEntryUrl}/${j_id}/delete`;
    console.log(journalEntry);
    return this.http.post(url, journalEntry);
  }

}
