package com.porkycakes.models.DAO;

import java.util.List;

import com.porkycakes.models.objects.Ingrediente;

public interface IIngredienteDAO {
    
    public List<Ingrediente> getIngredientes(int idReceta);  //obtener ingredientes de una receta
    public void insertIngrediente(int idReceta, Ingrediente ing); // inserta un ingrediente para una receta
}
