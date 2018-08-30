package model;

import java.io.Serializable;

public class Marca implements Serializable {

    private int cmarca;
    private String marca;

    public String toString() {
        return "\"" + this.marca + "\"";
    }

    public int getCmarca() {
        return cmarca;
    }

    public void setCmarca(int cmarca) {
        this.cmarca = cmarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
