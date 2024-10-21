package com.porkycakes;

import org.sql2o.Sql2o;

import com.porkycakes.controllers.RecetaCompletaControlador;
import com.porkycakes.controllers.ProductoControlador;
import com.porkycakes.utils.Sql2oDAO;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando Proyecto Porkis!");

        post("/insertarReceta", RecetaCompletaControlador.crearReceta);
        post("/crearproducto", ProductoControlador.crearProducto);
    }
}