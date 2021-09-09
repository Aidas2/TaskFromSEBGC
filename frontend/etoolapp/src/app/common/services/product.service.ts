import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product-model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public backendUrl = environment.eToolAppApi;

  constructor(private httpClient: HttpClient) {
  }

  public findProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.backendUrl + 'products/all');
  }
}
