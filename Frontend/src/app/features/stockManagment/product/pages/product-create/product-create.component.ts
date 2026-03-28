import { Component, OnInit } from '@angular/core';
import {ProductDTO} from '../../../../../shared/models/dto/stockManagmentDTO/product.dto';
import { ProductService } from '../../../../../core/services/stockManagment/productService/product.service';
import { inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Observable } from 'rxjs';
import { Category } from '../../../../../shared/models/StockManagment/Category.model';
import { CategoryService } from '../../../../../core/services/stockManagment/categoryService/category.service';
import { Location } from '@angular/common';
import { AisleService } from '../../../../../core/services/stockManagment/aisleService/aisle.service';
import { Aisle } from '../../../../../shared/models/StockManagment/Aisle.model';
import { MatIcon } from '@angular/material/icon';
import { ActivatedRoute, Router } from '@angular/router';
import { FormStateService } from '../../../../../core/services/form-state.service';

@Component({
  selector: 'app-product-create',
  standalone: true,
  imports: [ReactiveFormsModule,
            MatFormFieldModule,
            MatSelectModule, 
            MatInputModule,
            MatButtonModule,
            CommonModule,
            MatCardModule,
          MatIcon],
  templateUrl: './product-create.component.html',
  styleUrl: './product-create.component.css'
})
export class ProductCreateComponent implements OnInit{

  private formStateService = inject(FormStateService);
  categorys!:Observable<Category[]>;
  aisles!:Observable<Aisle[]>;
  private productService  = inject(ProductService);
  private categoryService = inject(CategoryService);
  private location = inject(Location);

  private router = inject(Router);
  private aisleService = inject(AisleService);
  fromBuilder= inject(FormBuilder);
  productForm = this.fromBuilder.group({
    reference : [''],
    designation:[''],
    brand:[''],
    description: [''],
    basePrice: ['', [Validators.required, Validators.min(0)]],
    categoryId:['',Validators.required],
    aisleId:['',Validators.required]
  });

  ngOnInit(){
    this.categorys=this.categoryService.leafCategoryList();
    this.aisles =this.aisleService.getAisles();
    console.log( "###########")
    console.log( this.categorys)

    const savedData = this.formStateService.getProductForm();

    if(savedData){
      this.productForm.patchValue(savedData);
    }
  }
  private mapFormToProduct(): ProductDTO {
    return this.productForm.getRawValue() as unknown as ProductDTO;
  }

  onSubmit() {
    if(this.productForm.invalid) return;
    const productDTO = this.mapFormToProduct();
    this.productService.addProduct(productDTO).subscribe({
      next: (response) => {
        console.log('Product Created successfully', response);
        alert('Product Created successfully');
        this.productForm.reset();
        this.formStateService.clearProductForm(); 
      },
      error: (err) => {
        console.error('Error creating product', err);
      
        if (err.error?.message) {
          alert(err.error.message);   // message Spring
        } else {
          alert('Erreur serveur lors création   product');
        }
        console.log("la response ",productDTO);
      }
      
    });
  }
 
//Categorys
addCategory(){
  this.formStateService.setProductForm(this.productForm.value);
  this.router.navigate(['categorys/add-category']);
 }
  
 addAisle(){
   this.formStateService.setProductForm(this.productForm.value);
  this.router.navigate(['aisle/add-aisle']);
 }
goBack(){
  this.location.back();
}
  
  

}
