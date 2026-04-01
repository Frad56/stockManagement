import { Component, Inject, OnInit, inject } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductVariantService } from '../../../../../core/services/stockManagment/productVariantService/product-variant.service';
import { Observable } from 'rxjs';
import { Product } from '../../../../../shared/models/StockManagment/product.model';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { ProductVariantDTO } from '../../../../../shared/models/dto/stockManagmentDTO/ProductVariant.dto';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-variant-create',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './product-variant-create.component.html',
  styleUrl: './product-variant-create.component.css'
})
export class ProductVariantCreateComponent implements OnInit {

  private productVariantService = inject(ProductVariantService);
  private productService = inject(ProductService);
  private formBuilder = inject(FormBuilder);

  protected products$!:Observable<Product[]>;
  private location = inject(Location);

  
  id!:number;
  private route = inject(ActivatedRoute);

  productVariantForm= this.formBuilder.group({
    code:[''],
    specificPrice:[],
    quantityInStock:[],
    productId:[null as number | null, Validators.required],
  })

  ngOnInit(): void {
      this.products$ =this.productService.getProducts();
      this.id = Number(this.route.snapshot.paramMap.get('id'));
      if(this.id){
        this.productVariantForm.patchValue({
          productId:this.id
        });
      }
    }

  private mapFormToProductVariant(): ProductVariantDTO {
    return this.productVariantForm.getRawValue() as unknown as ProductVariantDTO;
  }

  onSubmit(){
    if(this.productVariantForm.invalid)return;
    const productVariantDTO = this.mapFormToProductVariant();
    this.productVariantService.addProductVariant(productVariantDTO).subscribe({
      next:(response)=>{
        alert('Product Variant Created successfully');
        this.productVariantForm.reset();
      },
      error: (err) => {
        console.error('Error creating product variant', err);
      
        if (err.error?.message) {
          alert(err.error.message); 
        } else {
          alert('Erreur serveur lors création  ');
        }
        console.log("la response ",productVariantDTO);
      }
    })
  }
  goBack(){
    this.location.back();
  }
}
