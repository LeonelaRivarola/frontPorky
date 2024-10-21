package com.porkycakes.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.porkycakes.models.objects.Ingrediente;
import com.porkycakes.models.objects.Paso;
import com.porkycakes.models.objects.Receta;
import com.porkycakes.models.objects.RecetaBase;
import com.porkycakes.models.objects.RecetaCompleta;
import com.porkycakes.service.RecetaCompletaService;

import spark.Request;
import spark.Response;
import spark.Route;

public class RecetaCompletaControlador {

    // crear receta
    public static Route crearReceta = (Request req, Response res) -> {

        Gson gson = new Gson();

        try {
            String body = req.body();

            RecetaCompleta recetaCompleta = gson.fromJson(body, RecetaCompleta.class);
            RecetaCompletaService rcService = new RecetaCompletaService();

            rcService.insertarRecetaCompleta(recetaCompleta);
            res.status(201); // Esto da OK, creado
            return "Receta insertada correctamente";
        } catch (IllegalArgumentException e) { //captura la excepcion de validacion del service
            res.status(400);
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    };
}