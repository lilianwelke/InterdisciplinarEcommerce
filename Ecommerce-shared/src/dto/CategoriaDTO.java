package dto;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private int ccategoria;
    private String categoria;

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
