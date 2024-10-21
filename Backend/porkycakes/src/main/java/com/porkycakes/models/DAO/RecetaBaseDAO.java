package com.porkycakes.models.DAO;

import org.sql2o.Connection;

import com.porkycakes.models.objects.RecetaBase;
import com.porkycakes.utils.Sql2oDAO;

public class RecetaBaseDAO implements IRecetaBaseDAO{
    
    public void insertRxR(int idReceta, RecetaBase rxr){

         try( Connection con = Sql2oDAO.getSql2o().open()) {
            String sql = "INSERT INTO recetaxreceta(`receta_id`,`receta_id1`) " + 
            "VALUES (:receta_id,:receta_id1)";

             con.createQuery(sql)
            .addParameter("receta_id", idReceta)
            .addParameter("receta_id1", rxr.getIdRecetaAsociada())
            .executeUpdate(); 

        } catch (Exception e) {
            System.err.println("Error al insertar en la base de datos: " + e.getMessage());
        }

    }
}
