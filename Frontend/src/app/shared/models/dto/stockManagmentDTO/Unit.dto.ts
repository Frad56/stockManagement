import { SizeType } from "../../enum/SizeType";
import { UnitName } from "../../enum/UnitName";

export interface UnitDTO{
        name:UnitName;
        symbol:string;
        sizeType:SizeType; 
}