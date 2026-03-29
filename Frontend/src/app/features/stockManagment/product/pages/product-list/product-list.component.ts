import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../../../../../shared/models/StockManagment/product.model';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { Location } from '@angular/common';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';


@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule
           ,MatTableModule,
            MatCardModule,
            MatIconModule,
            MatButtonModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {

  products$! : Observable<Product[]>;
  displayedColumns: string[] = ['product_id', 'reference', 'designation', 'brand', 'description', 'basePrice','category','aisle','actions'];
  private productService  = inject(ProductService);
  private router = inject(Router);
  private location = inject(Location);
  private productVariantService = inject(ProductVariantService);
  condition!: boolean;
  productConditions: { [productId: number]: boolean } = {};
  loadProducts(){
    this.products$ = this.productService.getProducts();
    
  }
  ngOnInit() {
    this.loadProducts();
  }

  editProduct(id:number){
  this.router.navigate(['/edit-product',id]);
  }


  deleteProduct(id:number){
    this.productService.deleteProduct(id).subscribe(res => {
      alert("product Deleted !");
      this.loadProducts();
    });
  
  }
  addProduct(){
    this.router.navigate(['/add-product']);
  }

  goBack(){
    this.location.back();
  }

  addProductUnitsale(id:number){
    this.router.navigate(['productUnitSale/add-productUnitSale-with-ProductId',id]);
  }
  
  addProductVariant(id:number){
    this.router.navigate(['productVariant/add-productVariant-with-productId',id]);
  }


  productVariantList(id:number){
    this.router.navigate(['productVariant/productVariant-list-with-productId',id]);
  }



  addCharacteristicValue(){
    this.router.navigate(['characteristicValue/add-characteristicValue']);
  }

  addCharacteristic(id:number){
    this.router.navigate(['characteristic/add-characteristic-with-productId',id]);
  }


}
