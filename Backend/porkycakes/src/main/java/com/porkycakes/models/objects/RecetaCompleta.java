package com.porkycakes.models.objects;

import java.util.List;
import lombok.Data;

@Data
public class RecetaCompleta {
    Receta receta;
    List<Paso> pasos;
    List<Ingrediente> ingredientes;
    List<RecetaBase> rxr; 
}
