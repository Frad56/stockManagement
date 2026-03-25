
import { Routes } from "@angular/router";
import { ShelfListComponent } from "./pages/shelf-list/shelf-list.component";
import { ShelfCreateComponent } from "./pages/shelf-create/shelf-create.component";
import { ShelfEditComponent } from "./pages/shelf-edit/shelf-edit.component";

export const SHELF_ROUTES:Routes = [
    
    { path:'shelf-list', component :ShelfListComponent},
    {path:'add-shelf',component:ShelfCreateComponent},
    {path:'edit-shelf/:id',component:ShelfEditComponent}
];