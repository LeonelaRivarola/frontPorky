package com.porkycakes.models.DAO;

import org.sql2o.Connection;

import com.porkycakes.models.objects.Receta;
import com.porkycakes.utils.Sql2oDAO;

public class RecetaDAO implements IRecetaDAO {
    
    public int insertReceta(Receta receta){

        try( Connection con = Sql2oDAO.getSql2o().open()) {
            String sql = "INSERT INTO receta(`nombre`, `tiempoPrep`,`isBase`) " + 
            "VALUES (:nombre, :tiempoPrep,:isBase)";

            int id = con.createQuery(sql)
            .addParameter("nombre",receta.getNombre())
            .addParameter("tiempoPrep", receta.getTiempoPrep())
            .addParameter("isBase", receta.getIsBase())
            .executeUpdate()
            .getKey(Integer.class);

            return id;

        } catch (Exception e) {
            System.err.println("Error al insertar en la base de datos: " + e.getMessage());
            return 0;
        }

    }
}
