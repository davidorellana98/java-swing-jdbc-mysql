package com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private final Integer id;
    private final String nombre;
    private List<Producto> productos;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void agregar(Producto producto) {
        if (productos == null) {
            productos = new ArrayList<>();
        }
        productos.add(producto);
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
