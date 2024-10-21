package com.porkycakes.models.objects;

import lombok.Data;

@Data
public class Paso {
    int id, numPaso, receta_id;
    String descripcion;
}
