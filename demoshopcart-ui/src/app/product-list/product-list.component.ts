import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs'

import { products } from '../products';
import { Item } from '../entities/item.entity';
import { Cart } from '../entities/cart.entity';
import { Book } from '../entities/book.entity';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})

@Injectable()
export class ProductListComponent implements OnInit{
	
  constructor(private http: HttpClient) { }
  
  @Output() eventClicked = new EventEmitter<number>();
    
  ngOnInit() {
		
		localStorage.setItem('cart', JSON.stringify(this.tempcart));
	}
 	
  books:  Book[] = [];
  tempcart: Item[] = [];
  
  getBooks() {
    return this.http.get("http://localhost:8766/democart-product-service/productstore/listOfBooks");
  }

  share() {
	this.getBooks().subscribe(
     response => this.handleSuccessfulResponse(response),
    );
  }
  
  handleSuccessfulResponse(response)
	{
    this.books=response;
	}
	
	private items: Item[] = [];
	private total: number = 0;
	
	cart(book) {
		if (book) {
				var item: Item = {
					product: book,
					quantity: 1
				};
				if (localStorage.getItem('cart') == null) {
					alert("1");
					let cart: any = [];
					cart.push(JSON.stringify(item));
					localStorage.setItem('cart', JSON.stringify(cart));
				} else {
					let cart: any = JSON.parse(localStorage.getItem('cart'));
					let index: number = -1;
					for (var i = 0; i < cart.length; i++) {
						let item: Item = JSON.parse(cart[i]);
						if (item.product == book) {
							index = i;
							break;
						}
					}
					if (index == -1) {
						cart.push(JSON.stringify(item));
						localStorage.setItem('cart', JSON.stringify(cart));
					} else {
						let item: Item = JSON.parse(cart[index]);
						item.quantity += 1;
						cart[index] = JSON.stringify(item);
						localStorage.setItem("cart", JSON.stringify(cart));
					}
				}
				this.loadCart();
			} else {
				this.loadCart();
			}
		
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
		this.eventClicked.emit(this.items.length);
	}

	remove(skucode: string): void {
		let cart: any = JSON.parse(localStorage.getItem('cart'));
		let index: number = -1;
		for (var i = 0; i < cart.length; i++) {
			let item: Item = JSON.parse(cart[i]);
			if (item.product.title == skucode) {
				cart.splice(i, 1);
				break;
			}
		}
		localStorage.setItem("cart", JSON.stringify(cart));
		this.loadCart();
	}

}



/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/