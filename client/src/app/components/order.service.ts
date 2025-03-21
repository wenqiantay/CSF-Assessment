import { Injectable } from "@angular/core";
import { MenuItem } from "../models";

@Injectable()
export class OrderService {
    private _orderItems : MenuItem[] = []

    setOrder(items: MenuItem[]) {
        this._orderItems = items
    }

    getOrder(): MenuItem[] {
        return this._orderItems
    }
}