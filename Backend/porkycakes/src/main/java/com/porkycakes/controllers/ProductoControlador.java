package com.porkycakes.controllers;

import com.google.gson.Gson;
import com.porkycakes.models.ServiceProducto;
import com.porkycakes.models.objects.Producto;

import spark.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import static spark.Spark.*;

public class ProductoControlador {
    private static Gson gson = new Gson();

    public static Route crearProducto = (Request req, Response res) -> {
        try {
            // Aceptar archivo desde multipart/form-data
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

            // Guardar el archivo (imagen)
            Part filePart = req.raw().getPart("imagen");
            String fileName = filePart.getSubmittedFileName();

            // Usar File.separator para evitar problemas de ruta
            // uso ruta base para mi pc leonela
            String rutaBase = "G:\\Mi unidad\\Facultad mica\\micaela facultad\\materias actuales\\Ayds2\\porky-cakes\\porky_cakes\\Backend\\porkycakes\\src\\main\\resources\\public\\imagenes";
            File directory = new File(rutaBase);
            if (!directory.exists()) {
                directory.mkdirs(); // Crea el directorio si no existe
            }

            File file = new File(directory, fileName); // Crea el archivo con el nombre

            // Copiar el archivo
            Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Obtener los datos del producto del cuerpo JSON
            Producto producto = gson.fromJson(req.queryParams("producto"), Producto.class);
            producto.setImagen(file.getAbsolutePath()); // Guardamos la ruta de la imagen
            ServiceProducto productoService = new ServiceProducto();
            // Insertar el producto en la base de datos
            productoService.insertarProd(producto);

            res.status(201);
            return gson.toJson("Producto creado exitosamente con imagen");
        } catch (IOException e) {
            e.printStackTrace();
            res.status(500);
            return gson.toJson("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Muestra la traza del error
            res.status(400);
            return gson.toJson("Error al crear producto: " + e.getMessage());
        }
    };
}