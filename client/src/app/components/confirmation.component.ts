import { Component, inject, OnInit } from '@angular/core';
import { Receipt } from '../models';
import { ReceiptService } from '../receipt.service';

@Component({
  selector: 'app-confirmation',
  standalone: false,
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})
export class ConfirmationComponent implements OnInit{

  // TODO: Task 5
  protected receipt !: Receipt
  private receiptSvc = inject(ReceiptService)

  ngOnInit(): void {
    
    this.receipt = this.receiptSvc.getReceipt()
    
  }
}
