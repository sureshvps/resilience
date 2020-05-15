import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ]
})
export class AppComponent  {
	
  public clickedEvent: string;
  
  onActivate(componentReference) {
	  if(componentReference.eventClicked) {
		   componentReference.eventClicked.subscribe((data) => {
			this.clickedEvent = data;
			})
	  }
  
  }
	
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/