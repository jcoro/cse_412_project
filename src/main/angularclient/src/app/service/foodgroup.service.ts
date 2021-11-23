import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Food} from '../model/food';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodGroupService {

  constructor(private http: HttpClient) {}

  public findAll(): Observable<Food[]> {
    return this.http.get<any[]>('http://localhost:8082/api/foodgroups');
  }

}
