import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {ProductDto} from './product.dto';

@Injectable()
export class ProductCatalogService {

  private baseUrl: string = 'http://localhost:9072/api/v1/products';
  constructor(private http: Http) { }

  getProductRecomendations (): Observable<ProductDto[]> {
    return this.http.get(this.baseUrl)
      .map(response => response.json());
  }
}
