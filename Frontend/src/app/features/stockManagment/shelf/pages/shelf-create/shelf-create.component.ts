import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { Aisle } from '../../../../../shared/models/StockManagment/Aisle.model';
import { AisleService } from '../../../../../core/services/stockManagment/aisleService/aisle.service';
import { ShelfDTO } from '../../../../../shared/models/dto/stockManagmentDTO/Shelf.dto';
import { ShelfService } from '../../../../../core/services/stockManagment/shelfService/shelf.service';


@Component({
  selector: 'app-shelf-create',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './shelf-create.component.html',
  styleUrl: './shelf-create.component.css'
})
export class ShelfCreateComponent  implements OnInit{

  private location = inject(Location);
  aisles !: Observable<Aisle[]>;
  private aisleService = inject (AisleService)
  private formBuilder = inject(FormBuilder);
  private shelfService = inject(ShelfService);
  shelfForm = this.formBuilder.group({
    shelfNameByAisle:[''],
    aisleId:['']
  })

  private mapFormToShelf(): ShelfDTO {
    return this.shelfForm.getRawValue() as unknown as ShelfDTO;

  }
  ngOnInit(){
      this.aisles = this.aisleService.getAisles();
  }

 
  onSubmit() {
    if(this.shelfForm.invalid) return;
    const shelfDTO = this.mapFormToShelf();
    this.shelfService.addShelf(shelfDTO).subscribe({
      next: (response) => {
        alert('shelf Creat successfully');
        console.log('shelf Creat successfully', response);
        this.shelfForm.reset();
      },
      error: (err) => {
        console.error('Error creating shelf', err);
      
        if (err.error?.message) {
          alert(err.error.message);   
        } else {
          alert('Erreur serveur lors création   shelf');
        }
        console.log("la response ",shelfDTO);
      }
      
    });
  }
 

  goBack(){
    this.location.back();
  }
    
}
