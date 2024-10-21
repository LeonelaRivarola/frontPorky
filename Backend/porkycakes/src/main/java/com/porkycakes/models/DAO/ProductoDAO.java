package com.porkycakes.models.DAO;

import org.sql2o.Connection;
import com.porkycakes.models.objects.Producto;
import com.porkycakes.utils.Sql2oDAO;

public class ProductoDAO implements IProductoDAO {
    @Override
    public void insertarProducto(Producto producto) {
        try (Connection con = Sql2oDAO.getSql2o().open()) { // si tengo la conexión de la bd abierta
            String insertSQL = "INSERT INTO producto (nombre, descripcion, porciones, precio, imagen, tiempoEspera, Receta_idReceta, Categoria_id) "
                    +
                    "VALUES (:nombre, :descripcion, :porciones, :precio, :imagen, :tiempoEspera, :Receta_idReceta, :Categoria_id)";

            int id = con.createQuery(insertSQL, true) // Eliminar el punto al final
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("porciones", producto.getPorciones())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("imagen", producto.getImagen())
                    .addParameter("tiempoEspera", producto.getTiempoEspera()) // Asegúrate de agregar todos los
                                                                              // parámetros
                    .addParameter("Receta_idReceta", producto.getReceta_idReceta())
                    .addParameter("Categoria_id", producto.getCategoria_id())
                    .executeUpdate() // Ejecuta la consulta
                    .getKey(Integer.class); // Obtiene la clave generada (ID)

            // System.out.println("Producto insertado con ID: " + id);
            // System.out.println("Nombre: " + producto.getNombre());
            // System.out.println("Descripción: " + producto.getDescripcion());
            // System.out.println("Porciones: " + producto.getPorciones());
            // System.out.println("Precio: " + producto.getPrecio());
            // System.out.println("Imagen: " + producto.getImagen());
            // System.out.println("Tiempo de Espera: " + producto.getTiempoEspera());
            // System.out.println("Receta ID: " + producto.getReceta_idReceta());
            // System.out.println("Categoría ID: " + producto.getCategoria_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}