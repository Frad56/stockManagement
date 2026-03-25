import { Component, OnInit, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, Location } from '@angular/common';
import { Observable } from 'rxjs';
import { Unit } from '../../../../../shared/models/StockManagment/Unit.model';
import { UnitService } from '../../../../../core/services/stockManagment/unitService/unit.service';

@Component({
  selector: 'app-unit-list',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
     MatCardModule,
     MatIconModule,
     MatButtonModule],
  templateUrl: './unit-list.component.html',
  styleUrl: './unit-list.component.css'
})
export class UnitListComponent implements OnInit {
units$!:Observable<Unit[]>;
displayedColumns: string[] = ['unitId', 'name', 'symbol', 'sizeType','actions'];
private unitService = inject(UnitService);
private router = inject(Router);
private location = inject(Location);
loadUnits(){
  this.units$ = this.unitService.getUnits();
}
ngOnInit(): void {
    this.loadUnits();
}
editUnit(id:number){
this.router.navigate(['unit/edit-unit',id]);
}
deleteUnit(id:number){
  this.unitService.deleteUnit(id).subscribe(res=>{
    alert("Unit Deleted !");
    this.loadUnits();
  });
}
addUnit(){
  this.router.navigate(['unit/add-unit']);
 }
 goBack(){
  this.location.back();
}
}
