import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Item } from '../entities/item.entity';


@Component({
	templateUrl: 'order.component.html'
})


export class OrderComponent implements OnInit {

	private items: Item[] = [];
	private total: number = 0;
	
	 @Output() eventClicked = new EventEmitter<number>();
	
	orderResponse:  string[] = [];
	
	orderUrl = 'http://localhost:8766/democart-order-service/orderprocess/createOrder'; 

	constructor(
		private http: HttpClient
	) { }
	
	
	ngOnInit() {
		this.loadCart();
		this.placeOrder();
	}

	loadCart(): void {
		this.total = 0;
		this.items = [];
		let cart = JSON.parse(localStorage.getItem('cart'));
		
		for (var i = 0; i < cart.length; i++) {
			let item = JSON.parse(cart[i]);
			this.items.push({
				product: item.product,
				quantity: item.quantity
			});
			this.total += item.product.price * item.quantity;
		}
	}	

	placeOrder() {
		this.eventClicked.emit(this.items.length);
		let itemMap = new Map<object, string>(); 
		let cart = JSON.parse(localStorage.getItem('cart'));
		
		for (var i = 0; i < cart.length; i++) {
			let item = JSON.parse(cart[i]);
			itemMap[item.product.skucode]=item.quantity;
		}
		var orderRequest = { "itemMap" : itemMap, "customerId": "demo"};
		this.http.post(this.orderUrl, orderRequest).subscribe(response => this.handleSuccessfulResponse(response),	);
		
	}
	
	
  
  handleSuccessfulResponse(response)
	{
    this.orderResponse=response;
	}

}