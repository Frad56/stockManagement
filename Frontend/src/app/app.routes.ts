import { Routes } from '@angular/router';

export const routes: Routes = [

{path:'',redirectTo: 'login', pathMatch:'full'},
{path:'', loadChildren:() =>import('./auth/auth.routes').then(m => m.authRoutes)},
{path:'', loadChildren:() =>import('./features/stockManagment/product/product.routes').then(m => m.PRODUCT_ROUTES)},

{path:'categorys', loadChildren:() => import('./features/stockManagment/category/category.routes').then(m => m.CATEGORY_ROUTES)},
{path:'suppliers', loadChildren:() =>import('./features/supplierManagement/supplier/supplier.routes').then(m => m.SUPPLIER_ROUTES)},
{path:'product-suppliers', loadChildren:() =>import('./features/supplierManagement/productSupplier/productSupplier.routes').then(m => m.PRODUCT_SUPPLIER_ROUTES)},
{path:'aisle',loadChildren:()=>import('./features/stockManagment/Aisle/aisle.routes').then(m => m.Aisle_ROUTES)},
{path:'characteristic',loadChildren:()=>import('./features/stockManagment/characteristic/characteristic.routes').then(m => m.CHARACTERISTIC_ROUTES)},

{path:'characteristicValue',loadChildren:()=>import('./features/stockManagment/characteristicValue/characteristicValue.routes').then(m => m.CHARACTERISTIC_VALUE_ROUTES)},

{path:'movementInStock',loadChildren:()=>import('./features/stockManagment/movementInStock/movementInStock.routes').then(m => m.MOVEMENT_IN_STOCK_ROUTES)},

{path:'productCharacteristic',loadChildren:()=>import('./features/stockManagment/productCharacteristic/productCharacteristic.routes').then(m => m.PRODUCT_CHARACTERISTIC_ROUTES)},


{path:'productUnitSale',loadChildren:()=>import('./features/stockManagment/productUnitSale/productUnitSale.routes').then(m => m.PRODUCT_UNIT_SALE_ROUTES)},

{path:'productVariant',loadChildren:()=>import('./features/stockManagment/productVariant/productVariant.routes').then(m => m.PRODUCT_VARIANT_ROUTES)},

{path:'shelf',loadChildren:()=>import('./features/stockManagment/shelf/shelf.routes').then(m => m.SHELF_ROUTES)},


{path:'unit',loadChildren:()=>import('./features/stockManagment/unit/unit.routes').then(m => m.Unit_ROUTES)},



];
