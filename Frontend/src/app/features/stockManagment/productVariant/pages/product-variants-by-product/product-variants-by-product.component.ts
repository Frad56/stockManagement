import { CommonModule, Location } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductVariant } from '../../../../../shared/models/StockManagment/ProductVariant.model';
import { CharacteristicValueService } from '../../../../../core/services/stockManagment/CharacteristicValueService/characteristic-value.service';
import { CharacteristicService } from '../../../../../core/services/stockManagment/characteristicService/characteristic.service';
import { ProductCharacteristic } from '../../../../../shared/models/StockManagment/ProductCharacteristic.model';
import { ProductCharacteristicService } from '../../../../../core/services/stockManagment/productCharacteristicService/product-characteristic.service';

@Component({
  selector: 'app-product-variants-by-product',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
     MatCardModule,
     MatIconModule,
     MatButtonModule],
  templateUrl: './product-variants-by-product.component.html',
  styleUrl: './product-variants-by-product.component.css'
})
export class ProductVariantsByProductComponent  implements OnInit{

  private productId!:number;
  private location = inject(Location);
  private productVariantService = inject(ProductVariantService);
  private route = inject(ActivatedRoute);
   productVariants$!: Observable<ProductVariant[]>;
  displayedColumns: string[] = ['productVariantId', 'productId', 'code', 'specificPrice', 'quantityInStock','actions'];
  private characteristicValueService = inject(CharacteristicValueService);
  private Characteristic = inject(CharacteristicService);
  private productCharacteristics = inject(ProductCharacteristicService);

  loadProductVariantsByProduct(productId:number){
    this.productVariants$ = this.productVariantService.
    findProductVariantbyProductId(productId);

  }


  ngOnInit(): void {
    this.productId = Number(this.route.snapshot.paramMap.get('id'));
    if(this.productId){
      this.loadProductVariantsByProduct(this.productId);
    }
   // alert("Product Variants for Product ID: " + this.productId);

  }

  addProductVariant(){
      window.location.href = `/productVariant/add-productVariant-with-productId/${this.productId}`;
  }
  editProductVariant(productVariantId:number){
      window.location.href = `/productVariant/edit-productVariant/${productVariantId}`;
  }

  deleteProductVariant(productVariantId:number){  
    if(confirm("Are you sure you want to delete this product variant?")){
      this.productVariantService.deleteProductVariant(productVariantId).subscribe({
        next:()=>{
          alert("Product variant deleted successfully.");
          this.loadProductVariantsByProduct(this.productId);
        },
        error:(err)=>{
          console.error("Error deleting product variant:", err);
          alert("Failed to delete the product variant. Please try again.");
        }
      });
    }
  }
  goBack(){
    this.location.back();
  }

}
