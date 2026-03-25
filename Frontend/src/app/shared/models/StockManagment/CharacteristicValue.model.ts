import { ProductCharacteristic } from "./ProductCharacteristic.model";
import { ProductVariant } from "./ProductVariant.model";

export class CharacteristicValue{

    characteristicValueId!:number;
    productCharacteristic!:ProductCharacteristic;
    productVariant!:ProductVariant;
    value!:string; 
    
}