import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OrderService } from './order.service';
import { MenuItem, Order, Receipt } from '../models';
import { RestaurantService } from '../restaurant.service';
import { Router } from '@angular/router';
import { ReceiptService } from '../receipt.service';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit {

  // TODO: Task 3
  private fb = inject(FormBuilder)
  protected form !: FormGroup
  private router = inject(Router)

  private orderSvc = inject(OrderService)
  private restaurantSvc = inject(RestaurantService)
  private receiptSvc = inject(ReceiptService)

  protected orderItems : MenuItem[] = []
  protected order !: Order


  ngOnInit(): void {
   this.orderItems = this.orderSvc.getOrder()
    this.form = this.createForm()
    
  }

  createForm(): FormGroup {
    return this.fb.group({
      username: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [Validators.required])
    })

  }

  get grandTotal(): number {
    return this.orderItems.reduce((sum, item) => sum + item.price + item.quantity, 0)
  }

  submitOrder() {
    const username = this.form.get('username')?.value
    const password = this.form.get('password')?.value
    const orderedItem = this.orderSvc.getOrder()
    
    this.order = {
      username: username,
      password: password,
      items: orderedItem
    }

    this.restaurantSvc.postOrder(this.order).then(response => {
      console.log(response)
      this.receiptSvc.setReceipt(response)
      this.router.navigate(['/confirmation'])
    }).catch(err => {
      console.error('Order error:', err);
      alert(err.error?.message || 'Order failed. Please try again.');
    })
    
  }
}
