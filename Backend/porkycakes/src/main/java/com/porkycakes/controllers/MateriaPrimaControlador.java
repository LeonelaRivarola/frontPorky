package com.porkycakes.controllers;


import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.porkycakes.models.DAO.MateriaPrimaDAO;
import com.porkycakes.models.objects.MateriaPrima;
import com.porkycakes.models.objects.RecetaCompleta;

import spark.Request;
import spark.Response;
import spark.Route;

public class MateriaPrimaControlador {

    // (a) Agregar una materia prima (ingrediente)
    public static Route insertMateriaPrima = (Request req, Response res) -> {

        Gson gson = new Gson();

        // trato de parsear el json del body del request directamente a la mp
        // usando el body para hacer el post, permite tener mas seguridad y privacidad
        // al momento de enviar la solicitud
        // al contrario que si usamos queryParams (mejor usarlo para el get o consultas
        // simples)
        try {

            //instancia de receta completa
            RecetaCompleta recetaCompleta = gson.fromJson(req.body(), RecetaCompleta.class);

            // intancia de MP
            MateriaPrima materiaPrima = gson.fromJson(req.body(), MateriaPrima.class); // si logramos parsear,
                                                                                       // simplificamos ademas mucho
                                                                                       // codigo

            MateriaPrimaDAO mpDAO = new MateriaPrimaDAO();
            int id_mp = mpDAO.insertMP(materiaPrima);
            if (id_mp != 0) {
                System.out.println("Materia Prima de prueba insertada en el id: " + id_mp);
                return "Materia Prima de prueba insertada en el id: " + id_mp;
            } else {
                System.out.println("Error");
                return "Algo falló";
            }
        } catch (JsonSyntaxException e) {
            // Manejar errores si el JSON no es válido, usando lib Gson
            res.status(400);
            return "Error en el formato del JSON: " + e.getMessage();
        }
    };

    // (b) Obtener una materia prima por su ID (ingrediente)
    public static Route getMateriaPrimaPorId = (Request req, Response res) -> {
        int idMateriaPrima = Integer.parseInt(req.queryParams("id"));
        MateriaPrimaDAO mpDAO = new MateriaPrimaDAO();
        MateriaPrima materiaPrima = mpDAO.getMPById(idMateriaPrima);

        if (materiaPrima != null) {
            // Convertir el objeto a JSON
            Gson gson = new Gson();
            res.type("application/json");
            return gson.toJson(materiaPrima);
        } else {
            res.status(404); // Establecer el código de estado HTTP 404 si no se encuentra el objeto
            return "Materia prima no encontrada";
        }
    };

    public static Route getAllMateriaPrima = (Request req, Response res) -> {
        MateriaPrimaDAO mpDAO = new MateriaPrimaDAO();
        List<MateriaPrima> materiasPrimas = mpDAO.getAllMP();

        if (materiasPrimas != null && !materiasPrimas.isEmpty()) {
            Gson gson = new Gson();
            res.type("application/json");
            return gson.toJson(materiasPrimas);
        } else {
            res.status(404);
            return "No se encontraron materias primas";
        }
    };

    public static Route updateMateriaPrima = (Request req, Response res) -> {

        Gson gson = new Gson();

        try {
            MateriaPrima materiaPrima = gson.fromJson(req.body(), MateriaPrima.class); // explicacion al principio
            MateriaPrimaDAO mpDAO = new MateriaPrimaDAO();
            if (mpDAO.updateMP(materiaPrima)) {
                return "Materia Prima" + materiaPrima.getId() + " Actualizada";
            } else {
                System.out.println("Error");
                return "Algo falló al actualizar la materia prima con id" + materiaPrima.getId();
            }
        } catch (JsonSyntaxException e) {
            // Manejar errores si el JSON no es válido, usando lib Gson
            res.status(400);
            return "Error en el formato del JSON: " + e.getMessage();
        } 
    };

    public static Route deleteMateriaPrima = (Request req, Response res) -> {
        int mpID = Integer.parseInt(req.queryParams("id"));
        MateriaPrimaDAO mpDAO = new MateriaPrimaDAO();
        if (mpDAO.deleteMP(mpID)) {
            return "Materia Prima eliminada";
        } else {
            return "Error al eliminar la materia prima";
        }
    };
}
