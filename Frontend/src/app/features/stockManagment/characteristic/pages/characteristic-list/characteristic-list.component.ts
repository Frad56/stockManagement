import { Component, OnInit, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule, Location } from '@angular/common';
import { Observable } from 'rxjs';
import { Characteristic } from '../../../../../shared/models/StockManagment/Characteristic.model';
import { CharacteristicService } from '../../../../../core/services/stockManagment/characteristicService/characteristic.service';

@Component({
  selector: 'app-characteristic-list',
  standalone: true,
  imports: [CommonModule
    ,MatTableModule,
     MatCardModule,
     MatIconModule,
     MatButtonModule],
  templateUrl: './characteristic-list.component.html',
  styleUrl: './characteristic-list.component.css'
})
export class CharacteristicListComponent implements OnInit {


protected characteristics$!:Observable<Characteristic[]>;
displayedColumns: string[] = ['characteristicId', 'name', 'type','actions'];
private router = inject(Router);
private characteristicService = inject(CharacteristicService);
private location = inject(Location);



 loadCharacteristics(){
  this.characteristics$ = this.characteristicService.getCharacteristic();
}
ngOnInit(): void {
  this.loadCharacteristics();
}

 deleteCharacteristic(id:number){
  this.characteristicService.deleteCharacteristic(id).subscribe(res => {
    alert("characteristic Deleted !");
    this.loadCharacteristics();
  });
 
}
addCharacteristic(){
  this.router.navigate(['characteristic/add-characteristic']);
 }
editCharacteristic(id:number){
  this.router.navigate(['characteristic/edit-characteristic',id]);
 }
goBack(){
  this.location.back();
}
}
