import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

import { Router } from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [MatSidenavModule, MatButtonModule,CommonModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  showStockManagment = false;
  showUserManagment =false;
  showAisleManagment =false;
  showSupplierManagment = false;
  showShelfManagment= false;
  showcharacteristicManagment=false;
  showUnitManagment= false;
  showProductUnitsaleManagment = false;
  showProductCharacteristicManagment =false;
  showProductVariantManagment =false;
  showCharacteristicValueManagment= false;
  showMovmentInStockManagment= false;
  
  private router = inject(Router);


//* ****************   SignUP    ************************** */
signUp(){
  this.router.navigate(['/SignUp']);
}


//////////////////// Stock Managment ////////////////////////
//Products
 getProducts(){
    this.router.navigate(['/products']);
 }

 addProduct(){
  this.router.navigate(['/add-product']);
 }


 //add Product Supplier 
  addProductSupplier(){
    this.router.navigate(['product-suppliers/add-product-supplier']);
  }

 //Categorys
 addCategory(){
  this.router.navigate(['categorys/add-category']);
 }



 //Stocks

 addStock(){
  this.router.navigate(['stocks/add-stock']);
 }

 ////////////////////////// supplier managment /////////////////////////////
 addSupplier(){
  this.router.navigate(['suppliers/add-supplier']);
 }
 allSuppliers(){
  this.router.navigate(['suppliers/suppliers']);
 }
  ////////////////////////// Aisle managment /////////////////////////////
  addAisle(){
    this.router.navigate(['aisle/add-aisle']);
   }
   allAisle(){
    this.router.navigate(['aisle/list-aisle']);
   }

   ////////////////////////// Aisle managment /////////////////////////////
  addShelf(){
    this.router.navigate(['shelf/add-shelf']);
   }
   allShelf(){
    this.router.navigate(['shelf/shelf-list']);
   }
   ////////////////////////// Characteristic managment /////////////////////////////

   addCharacteristic(){
    this.router.navigate(['characteristic/add-characteristic']);

   }
   allCharacteristic(){
    this.router.navigate(['characteristic/characteristic-list']);

   }
    ////////////////////////// Unit managment /////////////////////////////

    addUnit(){
      this.router.navigate(['unit/add-unit']);
  
     }
     allUnits(){
      this.router.navigate(['unit/unit-list']);
  
     }
    /////////////////////    ProductUnitsale  Managment                  ///////////////////////////////
    addProductUnitsale(){
      this.router.navigate(['productUnitSale/add-productUnitSale']);
  
     }
     allProductUnitsale(){
      this.router.navigate(['productUnitSale/productUnitSale-list']);
  
     }

       /////////////////////    productCharacteristic  Managment                  ///////////////////////////////
    addProductCharacteristic(){
      this.router.navigate(['productCharacteristic/add-productCharacteristic']);
  
     }
     allProductCharacteristic(){
      this.router.navigate(['productCharacteristic/productCharacteristic-list']);
  
     }
     ////////////////////   ProductVariantManagment ///////////////////////////
     addProductVariant(){
      this.router.navigate(['productVariant/add-productVariant']);
  
     }
     allProductVariant(){
      this.router.navigate(['productVariant/productVariant-list']);
  
     }

      ////////////////////   Characteristic Value Managment ///////////////////////////
      addCharacteristicValue(){
        this.router.navigate(['characteristicValue/add-characteristicValue']);
    
       }
       allCharacteristicValue(){
        this.router.navigate(['characteristicValue/characteristicValue-list']);
    
       }

          ////////////////////   Movment In Stock Managment ///////////////////////////

      addMovmentInStock(){
        this.router.navigate(['movementInStock/add-movementInStock']);
    
       }
       allMovmentInStock(){
        this.router.navigate(['movementInStock/movementInStock-list']);
    
       }
}
