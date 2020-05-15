import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';

const routes: Routes = [
	{ path: '', component: ProductListComponent },
	{ path: 'products', component: ProductListComponent },
	{ path: 'cart', component: CartComponent },
	{ path: 'order', component: OrderComponent },
	{ path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(routes);