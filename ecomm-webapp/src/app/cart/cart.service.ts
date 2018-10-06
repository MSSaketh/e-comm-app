
import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import {ProductDto} from '../productCatalog/product.dto';
import {Observable} from 'rxjs/Observable';
import {CartItemDto} from './cart.item.dto';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {CartDto} from './cart.dto';

@Injectable()
export class CartService {

  // cartId: string;
  id: string;
  private baseUrl: String = 'http://localhost:9073/api/v1/cart/';
  cartItemOb:  BehaviorSubject<CartItemDto[]> = new BehaviorSubject(null);
  constructor(private http: Http) { }
  addToCart(productDTO: ProductDto): Observable<CartDto> {

    console.log(JSON.stringify(this.convertProductToCartItem(productDTO)));
      
      console.log(this.convertProductToCartItem(productDTO).itemId);
      this.id = this.convertProductToCartItem(productDTO).itemId;
      console.log(this.id);
      
      
    return this.http.put(this.baseUrl + '122', JSON.stringify(this.convertProductToCartItem(productDTO)), {headers: this.getHeaders()})
      .map(response => response.json());
  }

  readCart (): Observable<CartDto> {
    console.log(this.id);
    
    return this.http.get(this.baseUrl + '122')
      .map(response => response.json());
  }

  private convertProductToCartItem (productDTO: ProductDto): CartItemDto {
    const cartItem = new CartItemDto();
    cartItem.itemId = productDTO.productId;
    cartItem.itemName = productDTO.productName;
    // cartItem.currency = productDTO.currency;
    cartItem.itemPrice = productDTO.productPrice;
    console.log(cartItem);
    return cartItem;
  }

  

  private getHeaders() {
    const headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    return headers;
  }

}
