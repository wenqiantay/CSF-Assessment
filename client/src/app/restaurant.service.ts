import { HttpClient } from "@angular/common/http";
import { inject } from "@angular/core";
import { firstValueFrom, lastValueFrom } from "rxjs";
import { MenuItem, Order } from "./models";
import * as CryptoJS from 'crypto-js';

export class RestaurantService {

  private http = inject(HttpClient)

  // TODO: Task 2.2
  // You change the method's signature but not the name
  getMenuItems(): Promise<MenuItem[]> {
    return firstValueFrom(this.http.get<MenuItem[]>('/api/menu'))
  }

  // TODO: Task 3.2
  postOrder(order: Order): Promise<any> {
    const body  = {
      username: order.username,
      password: CryptoJS.SHA224(order.password).toString(),
      items: order.items
    }

    console.log(body)
    return lastValueFrom(this.http.post('/api/food_order', body))
  }
}
