import { Component, OnInit, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { Shelf } from '../../../../../shared/models/StockManagment/Shelf.model';
import { Location } from '@angular/common';
import { ShelfService } from '../../../../../core/services/stockManagment/shelfService/shelf.service';
import { AisleService } from '../../../../../core/services/stockManagment/aisleService/aisle.service';
import { Aisle } from '../../../../../shared/models/StockManagment/Aisle.model';


@Component({
  selector: 'app-shelf-list',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
     MatCardModule,
     MatIconModule,
     MatButtonModule],
  templateUrl: './shelf-list.component.html',
  styleUrl: './shelf-list.component.css'
})
export class ShelfListComponent implements OnInit {

  shelfs$! :Observable<Shelf[]>;
  private shelfService = inject(ShelfService);
  displayedColumns: string[] = ['shelfId', 'shelfNameByAisle','aisleId','actions'];
  private aisleService = inject(AisleService);

  private location = inject(Location);
  private router = inject (Router);

  loadShelfs(){
    this.shelfs$ = this.shelfService.getShelfs();
  }
  ngOnInit(): void {
    this.loadShelfs();
  }
  goBack(){
    this.location.back();
  }
  editSehlf(id :number){
    this.router.navigate(['shelf/edit-shelf',id]);
  }
  deleteShelf(id : number){
    this.shelfService.deleteShelf(id).subscribe( res =>{
      alert("Shelf deleted !");
      this.loadShelfs();
    })
  }
 
  addShelf(){
    this.router.navigate(['shelf/add-shelf']);

  }
    

}
