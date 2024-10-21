package com.porkycakes.models.objects;

import lombok.Data;

@Data
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int porciones;
    private double precio;
    private String imagen;
    private int tiempoEspera;
    private int Receta_idReceta;
    private int Categoria_id;
}
