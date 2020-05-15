import { Component, OnInit, Input } from '@angular/core';
import { Cart } from '../entities/cart.entity';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
	
  @Input() event: string; 
  
  ngOnInit() {}


}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/