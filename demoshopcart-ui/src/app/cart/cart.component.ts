import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,  Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Item } from '../entities/item.entity';
import { Cart } from '../entities/cart.entity';


@Component({
	templateUrl: 'cart.component.html'
})

export class CartComponent implements OnInit {

	private items: Item[] = [];
	private total: number = 0;
	shopcart: Cart = JSON.parse(localStorage.getItem('cart')); 
	
	orderUrl = 'http://localhost:8766/democart-order-service/orderprocess/createOrder'; 

	constructor(
		private router: Router,
		private http: HttpClient
	) { }
	
	
	ngOnInit() {
		this.loadCart();
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

	remove(id: string): void {
		let cart: any = JSON.parse(localStorage.getItem('cart'));
		let index: number = -1;
		for (var i = 0; i < cart.length; i++) {
			let item: Item = JSON.parse(cart[i]);
			if (item.product.title == id) {
				cart.splice(i, 1);
				break;
			}
		}
		localStorage.setItem("cart", JSON.stringify(cart));
		this.loadCart();
	}
	
	

	placeOrder() {		
		this.router.navigate(['/order']);
	}

}