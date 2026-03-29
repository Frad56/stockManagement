import { Component, OnInit, inject } from '@angular/core';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, Location } from '@angular/common';
import { Observable } from 'rxjs';
import { ProductVariant } from '../../../../../shared/models/StockManagment/ProductVariant.model';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';

@Component({
  selector: 'app-product-variant-list',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule],
  templateUrl: './product-variant-list.component.html',
  styleUrl: './product-variant-list.component.css'
})
export class ProductVariantListComponent  implements OnInit{
private location = inject(Location);
private router = inject(Router);
private productVariantService = inject(ProductVariantService);
productVariants$ !:Observable<ProductVariant[]>;
displayedColumns: string[] = ['productVariantId', 'code', 'specificPrice', 'quantityInStock', 'productId','actions'];

loadProductVariants(){
  this.productVariants$ = this.productVariantService.getProductVariant();

}

ngOnInit(): void {
  this.loadProductVariants();
}


editProductVariant(id:number){
  this.router.navigate(['productVariant/edit-productVariant',id]);
  }


deleteProductVariant(id:number){
    this.productVariantService.deleteProductVariant(id).subscribe(res => {
      alert("Product Variant Deleted !");
      this.loadProductVariants();
    });
  
  }
  addProductVariant(){
    this.router.navigate(['productVariant/add-productVariant']);
  }
  goBack(){
    this.location.back();
  }

}
