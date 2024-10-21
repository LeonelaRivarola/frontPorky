package com.porkycakes.service;

import com.porkycakes.models.DAO.IIngredienteDAO;
import com.porkycakes.models.DAO.IPasoDAO;
import com.porkycakes.models.DAO.IRecetaBaseDAO;
import com.porkycakes.models.DAO.IRecetaDAO;
import com.porkycakes.models.DAO.IngredienteDAO;
import com.porkycakes.models.DAO.PasoDAO;
import com.porkycakes.models.DAO.RecetaBaseDAO;
import com.porkycakes.models.DAO.RecetaDAO;
import com.porkycakes.models.objects.Ingrediente;
import com.porkycakes.models.objects.Paso;
import com.porkycakes.models.objects.RecetaBase;
import com.porkycakes.models.objects.RecetaCompleta;

public class RecetaCompletaService {
    IRecetaDAO recetaDAO;
    IPasoDAO pasoDAO;
    IIngredienteDAO ingredienteDAO;
    IRecetaBaseDAO recetaBaseDAO;

    public RecetaCompletaService(){
        this.recetaDAO = new RecetaDAO();
        this.pasoDAO = new PasoDAO();
        this.ingredienteDAO = new IngredienteDAO();
        this.recetaBaseDAO = new RecetaBaseDAO();
    }


    public void insertarRecetaCompleta(RecetaCompleta recetaCompleta) {
        // Validaciones de los datos obligatorios
        if (recetaCompleta.getIngredientes() == null || recetaCompleta.getIngredientes().isEmpty()) {
            throw new IllegalArgumentException("La receta debe tener al menos un ingrediente."); // Esto propaga el error para que sea manejado por el controlador
        }
    
        if (recetaCompleta.getPasos() == null || recetaCompleta.getPasos().isEmpty()) {
            throw new IllegalArgumentException("La receta debe tener al menos un paso.");
        }
    
        try {
            int idReceta = recetaDAO.insertReceta(recetaCompleta.getReceta());
            
            for (Ingrediente ing : recetaCompleta.getIngredientes()) {
                ingredienteDAO.insertIngrediente(idReceta, ing);
            }
    
            for (Paso paso : recetaCompleta.getPasos()) {
                pasoDAO.insertPaso(idReceta, paso);
            }
    
            if (recetaCompleta.getRxr() != null) {
                for (RecetaBase rxr : recetaCompleta.getRxr()) {
                    recetaBaseDAO.insertRxR(idReceta, rxr);
                }
            }
    
        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
            throw e; // Esto propaga el error para que sea manejado por el controlador
        }
    }
    


}
