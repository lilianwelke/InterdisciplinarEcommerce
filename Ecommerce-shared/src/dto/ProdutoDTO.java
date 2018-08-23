package dto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private int cproduto;
    private CategoriaDTO ccategoria;
    private MarcaDTO cmarca;
    private String produto;
    private String descProduto;
    private byte[] fotoProduto;
    private double precoProduto;
    private double promocao;
    private int qtdeEstoque;
    private String upload;

    public int getCproduto() {
        return cproduto;
    }

    public void setCproduto(int cproduto) {
        this.cproduto = cproduto;
    }

    public CategoriaDTO getCcategoria() {
        return ccategoria;
    }

    public void setCcategoria(CategoriaDTO ccategoria) {
        this.ccategoria = ccategoria;
    }

    public MarcaDTO getCmarca() {
        return cmarca;
    }

    public void setCmarca(MarcaDTO cmarca) {
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

    public byte[] getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(byte[] fotoProduto) {
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

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

}
