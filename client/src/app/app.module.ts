import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideHttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu.component';
import { PlaceOrderComponent } from './components/place-order.component';

import { ConfirmationComponent } from './components/confirmation.component';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantService } from './restaurant.service';
import { OrderService } from './components/order.service';
import { ReceiptService } from './receipt.service';


const appRoutes: Routes = [
  {path: '', component: MenuComponent},
  {path: 'placeorder', component: PlaceOrderComponent},
  {path: 'confirmation', component: ConfirmationComponent}
]


@NgModule({
  declarations: [
    AppComponent, MenuComponent, PlaceOrderComponent, ConfirmationComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes)
  ],
  providers: [ provideHttpClient(), RestaurantService, OrderService, ReceiptService],
  bootstrap: [AppComponent]
})
export class AppModule { }
