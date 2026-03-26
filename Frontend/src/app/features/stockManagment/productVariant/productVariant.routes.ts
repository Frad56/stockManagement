
import { Routes } from "@angular/router";
import { ProductVariantListComponent } from "./pages/product-variant-list/product-variant-list.component";
import { ProductVariantCreateComponent } from "./pages/product-variant-create/product-variant-create.component";
import { ProductVariantEditComponent } from "./pages/product-variant-edit/product-variant-edit.component";

export const PRODUCT_VARIANT_ROUTES:Routes = [
    
    { path:'productVariant-list', component :ProductVariantListComponent},
    {path:'add-productVariant',component:ProductVariantCreateComponent},
    {path:'add-productVariant-with-productVariantId/:id',component:ProductVariantCreateComponent},
    {path:'edit-productVariant/:id',component:ProductVariantEditComponent}
];