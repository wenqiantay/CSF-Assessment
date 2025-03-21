import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { MenuItem } from '../models';
import { Router } from '@angular/router';
import { OrderService } from './order.service';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {

  private restaurantSvc = inject(RestaurantService)
  protected menuItemList : MenuItem[] = []
  private router = inject(Router)
  protected selectedItems : MenuItem[] = []
  private orderSvc = inject(OrderService)

  // TODO: Task 2
  ngOnInit(): void {
    this.restaurantSvc.getMenuItems().then( result => {
      console.log(result)
      this.menuItemList = result.map(i => ({...i, quantity: 0}))
    }).catch(err => 
      console.log(err)
    )

  }

  updateQuantity(itemName: string, delta: number) {
    const menuItem = this.menuItemList.find(i => i.name === itemName)
    if(!menuItem) return
    
    if(menuItem){
      menuItem.quantity += delta
    }
  }

  clearButton(itemName: string) {
    const menuItem = this.menuItemList.find(i => i.name === itemName)

    if(menuItem) {
      menuItem.quantity = 0
    }
  }

  get totalQuantity(): number {
    return this.menuItemList.reduce((total, item) => total + item.quantity, 0)
  }

  get totalPrice(): number {
    return this.menuItemList.reduce((total, item) => total + item.quantity* item.price, 0)
  }

  placeOrder() {
    this.selectedItems = this.menuItemList.filter(item => item.quantity > 0)
    this.orderSvc.setOrder(this.selectedItems)
    this.router.navigate(['/placeorder'])
  }
}
