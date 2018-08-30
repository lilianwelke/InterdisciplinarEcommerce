package model;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int ccategoria;
    private String categoria;

    public String toString() {
        return "\"" + this.categoria + "\"";
    }

    public int getCcategoria() {
        return ccategoria;
    }

    public void setCcategoria(int ccategoria) {
        this.ccategoria = ccategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
