package com.porkycakes.models;

import com.porkycakes.models.DAO.IProductoDAO;
import com.porkycakes.models.DAO.ProductoDAO;
import com.porkycakes.models.objects.Producto;

public class ServiceProducto {

    private IProductoDAO iProductoDAO;

    public ServiceProducto(IProductoDAO iProductoDAO) {
        this.iProductoDAO = iProductoDAO;
    }

    public ServiceProducto() {
        this.iProductoDAO = new ProductoDAO(); // Asegúrate de que el DAO esté inicializado
    }

    public void insertarProd(Producto producto) {

        iProductoDAO.insertarProducto(producto);
    }
}
