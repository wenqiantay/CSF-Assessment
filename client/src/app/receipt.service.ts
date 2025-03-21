import { Injectable } from "@angular/core";
import { Receipt } from "./models";

@Injectable({ providedIn: 'root' })
export class ReceiptService {
  private receipt!: Receipt

  setReceipt(r: Receipt) {
    this.receipt = r
  }

  getReceipt(): Receipt {
    return this.receipt
  }
}
