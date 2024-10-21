package com.porkycakes.models.DAO;

import org.sql2o.Connection;

import com.porkycakes.models.objects.Paso;
import com.porkycakes.utils.Sql2oDAO;

public class PasoDAO implements IPasoDAO {
    
    public void insertPaso(int idReceta, Paso paso){

        try( Connection con = Sql2oDAO.getSql2o().open()) {
            String sql = "INSERT INTO paso(`numPaso`, `descripcion`, `receta_id`) " + 
            "VALUES (:numPaso, :descripcion, :receta_id)";

            con.createQuery(sql)
            .addParameter("numPaso", paso.getNumPaso())
            .addParameter("descripcion", paso.getDescripcion())
            .addParameter("receta_id", idReceta)
            .executeUpdate();

        } catch (Exception e) {
            System.err.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

}
