package com.porkycakes.models.DAO;

import java.util.List;

import org.sql2o.Connection;

import com.porkycakes.models.objects.Ingrediente;
import com.porkycakes.utils.Sql2oDAO;

public class IngredienteDAO implements IIngredienteDAO {
    
    public List<Ingrediente> getIngredientes(int idReceta){ //por ahora no se necesita, lo que envio son las materias prima
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            String querySQL = "SELECT * FROM `ingrediente`"; 
            List<Ingrediente> ingredientes = con.createQuery(querySQL).executeAndFetch(Ingrediente.class);
            return ingredientes; 
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return null;
        }
    }

    public void insertIngrediente(int idReceta, Ingrediente ing){
        try( Connection con = Sql2oDAO.getSql2o().open()) {
            String sql = "INSERT INTO ingrediente(`receta_id`, `materiaprima_id`, `cantidad`) " + 
            "VALUES (:receta_id, :materiaPrima_id, :cantidad)";

            con.createQuery(sql)
            .addParameter("receta_id", idReceta)
            .addParameter("materiaPrima_id", ing.getMateriaPrima_id())
            .addParameter("cantidad", ing.getCantidad())
            .executeUpdate();

        } catch (Exception e) {
            System.err.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }
}
