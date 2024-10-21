package com.porkycakes.models.objects;

import lombok.Data;

@Data
public class Receta {
    int id;
    String nombre, tiempoPrep;
    Boolean isBase;
}
