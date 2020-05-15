import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { ProductListComponent } from './product-list/product-list.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';

import { routing } from './app.routing';

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
	HttpClientModule,
    routing
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    ProductListComponent,
	CartComponent,
	OrderComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/