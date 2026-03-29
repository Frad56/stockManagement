import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ProductVariant } from '../../../../shared/models/StockManagment/ProductVariant.model';
import { ProductVariantDTO } from '../../../../shared/models/dto/stockManagmentDTO/ProductVariant.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductVariantService {

  private apiUrl = 'http://localhost:8080/api/productVariant';
  private http = inject(HttpClient);

  getProductVariant():Observable<ProductVariant[]>{
    return this.http.get<ProductVariant[]>(`${this.apiUrl}/ListProductVariants`);
  }

  addProductVariant(productVariant : ProductVariantDTO):Observable<ProductVariant>{
    console.log("productVariant service is called to add a new productVariant");
    return this.http.post<ProductVariant>(`${this.apiUrl}/addProductVariant`,productVariant);
  }

  findProductVariantById(id:number):Observable<ProductVariant>{
    return this.http.get<ProductVariant>(`${this.apiUrl}/find/${id}`);
  }

  editProductVariant(productVariant:ProductVariantDTO , productVariantId:number):Observable<ProductVariant>{
    return this.http.put<ProductVariant>(`${this.apiUrl}/update/${productVariantId}`,productVariant);
  }

  deleteProductVariant(productVariantId:number):Observable<string>{
    return this.http.delete<string>(`${this.apiUrl}/delete/${productVariantId}`);
  }

  hasProductVariants(productId:number):Observable<{ hasVariants: boolean }>{
    return this.http.get<{ hasVariants: boolean }>(`${this.apiUrl}/${productId}/has-variants`);
  }
  //

  findProductVariantbyProductId(productId:number):Observable<ProductVariant[]>{
    return this.http.get<ProductVariant[]>(`${this.apiUrl}/products/${productId}/variants`);
  }
}
