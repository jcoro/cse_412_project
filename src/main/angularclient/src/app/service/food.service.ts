import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Food} from '../model/food';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private foodUrl: string;

  constructor(private http: HttpClient) {
    this.foodUrl = 'http://localhost:8082/api/foods';
  }

  public findAll(): Observable<Food[]> {
    let result = this.http.get<Food[]>(this.foodUrl);
    console.log(result)
    return result;
  }

}
