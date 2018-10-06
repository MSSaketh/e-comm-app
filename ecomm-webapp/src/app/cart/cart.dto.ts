import {CartItemDto} from './cart.item.dto';

export class CartDto {
  cartId: string;
  cartItems: CartItemDto[];
  cartTotal: number;
  // currency: String;
}
