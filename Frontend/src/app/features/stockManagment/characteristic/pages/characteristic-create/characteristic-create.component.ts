import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CharacteristicService } from '../../../../../core/services/stockManagment/characteristicService/characteristic.service';
import { Location } from '@angular/common';
import { CharacteristicTypeValue } from '../../../../../shared/models/enum/Characteristic-type-value';
import { CharacteristicDTO } from '../../../../../shared/models/dto/stockManagmentDTO/Characteristic.dto';

@Component({
  selector: 'app-characteristic-create',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule, 
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatCardModule],
  templateUrl: './characteristic-create.component.html',
  styleUrl: './characteristic-create.component.css'
})
export class CharacteristicCreateComponent {

  characteristicTypeValues = Object.values(CharacteristicTypeValue); 
  private formBuilder = inject(FormBuilder);
  private characteristicService = inject(CharacteristicService);
  private location = inject(Location);
  characteristicForm = this.formBuilder.group({
    name:[''],
    type:['',Validators.required]
  })
  

  private mapFormToCharacteristic():CharacteristicDTO{
    return this.characteristicForm.getRawValue() as unknown as CharacteristicDTO;
  }
  onSubmit(){
    if(this.characteristicForm.invalid) return;
    const characteristicDTO = this.mapFormToCharacteristic();
    this.characteristicService.addCharacteristic(characteristicDTO).subscribe({
      next:() =>{
        console.log("data:",characteristicDTO)
        this.characteristicForm.reset();
      },
      error:(err)=>{
        console.log('error',err)
      }
    })
  }

  goBack(){
    this.location.back();
  }
}
