import { FormControl } from "@angular/forms";

export class DateValidators{

        public static validarFechaNacimiento(elemento: FormControl)
        {
          let invalido : boolean = false;
        
         let texto = elemento.value;
         let aux:Date = new Date(texto)
         let fechaSeleccionada: Date = new Date(aux.getUTCFullYear(),aux.getUTCMonth(),aux.getUTCDate())
        
         invalido = fechaSeleccionada > new Date(new Date().getFullYear()-18,new Date().getMonth(),new Date().getDate())
        
         return invalido ?{fechaInvalida:true}:null;
        
        }
        
        public static validarFechaIngreso(elemento: FormControl)
        {
          let invalido : boolean = false;
        
         let texto = elemento.value;
         let aux:Date = new Date(texto)
         let fechaSeleccionada: Date = new Date(aux.getUTCFullYear(),aux.getUTCMonth(),aux.getUTCDate())
        
         invalido = fechaSeleccionada > new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate())
        
         return invalido ?{fechaInvalida:true}:null;
        
        }
}