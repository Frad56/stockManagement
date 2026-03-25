import { Component, OnInit, inject } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ShelfDTO } from '../../../../../shared/models/dto/stockManagmentDTO/Shelf.dto';
import { AisleService } from '../../../../../core/services/stockManagment/aisleService/aisle.service';
import { ShelfService } from '../../../../../core/services/stockManagment/shelfService/shelf.service';
import { Observable } from 'rxjs';
import { Aisle } from '../../../../../shared/models/StockManagment/Aisle.model';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-shelf-edit',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './shelf-edit.component.html',
  styleUrl: './shelf-edit.component.css'
})
export class ShelfEditComponent implements OnInit{
  private location = inject(Location);
  aisles !: Observable<Aisle[]>;
  private aisleService = inject (AisleService)
  private formBuilder = inject(FormBuilder);
  private shelfService = inject(ShelfService);
  private route = inject(ActivatedRoute);

  id!:number;
  shelfForm = this.formBuilder.group({
    shelfNameByAisle:[''],
    aisleId:this.formBuilder.control<number | null>(null, Validators.required)
  })

  private mapFormToShelf(): ShelfDTO {
    return this.shelfForm.getRawValue() as unknown as ShelfDTO;

  }
  ngOnInit(){
      this.aisles = this.aisleService.getAisles();
      this.id = Number(this.route.snapshot.paramMap.get('id'));
      if(this.id){
        this.shelfService.findShelfById(this.id).subscribe({
          
          next:(shelf)=>{
            console.log("shelf:",shelf);
          
            this.shelfForm.patchValue({
              shelfNameByAisle:shelf.shelfNameByAisle,    
            });
          },
          error:(err)=>{
            console.log("Error loading shelf",err);
          }
        });
      }
  }

 
  onSubmit() {
    if(this.shelfForm.invalid) return;
    const shelfDTO = this.mapFormToShelf();
    this.shelfService.editShelf(shelfDTO,this.id).subscribe({
      next: (response) => {
        console.log('shelf edit successfully', response);
        this.goBack();
      },
      error: (err) => {
        console.error('Error editing shelf', err);
      
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
