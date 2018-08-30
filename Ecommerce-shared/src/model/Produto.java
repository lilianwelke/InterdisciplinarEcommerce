package model;

import java.io.Serializable;

public class Produto implements Serializable {

    private int cproduto;
    private Categoria ccategoria;
    private Marca cmarca;
    private String produto;
    private String descProduto;
    private String fotoProduto;
    private double precoProduto;
    private double promocao;
    private int qtdeEstoque;

    public String toString() {
        return "\"" + this.cproduto + this.produto + this.descProduto + this.fotoProduto + this.precoProduto + this.promocao + this.qtdeEstoque + "\"";
    }

    public int getCproduto() {
        return cproduto;
    }

    public void setCproduto(int cproduto) {
        this.cproduto = cproduto;
    }

    public Categoria getCcategoria() {
        return ccategoria;
    }

    public void setCcategoria(Categoria ccategoria) {
        this.ccategoria = ccategoria;
    }

    public Marca getCmarca() {
        return cmarca;
    }

    public void setCmarca(Marca cmarca) {
        this.cmarca = cmarca;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public String getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getPromocao() {
        return promocao;
    }

    public void setPromocao(double promocao) {
        this.promocao = promocao;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

}
