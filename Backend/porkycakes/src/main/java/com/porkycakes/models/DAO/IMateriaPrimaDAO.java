package com.porkycakes.models.DAO;

import java.util.List;

import com.porkycakes.models.objects.MateriaPrima;

public interface IMateriaPrimaDAO {
    public int insertMP(MateriaPrima materiaPrima); //agregar una Materia Prima
    public MateriaPrima getMPById(int id); //Obtener una materia prima por su ID
    public List<MateriaPrima> getAllMP(); //Obtener todos las materias prima
    public boolean updateMP(MateriaPrima materiaPrima); //Actualizar una materia prima
    public boolean deleteMP(int id); //Eliminar una materia prima
}
