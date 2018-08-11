package model;

public class ItemPedido {

    private int citemPedido;
    private Pedido cpedido;
    private Produto cproduto;
    private double qtdeProduto;
    private double precoProduto;

    public int getCitemPedido() {
        return citemPedido;
    }

    public void setCitemPedido(int citemPedido) {
        this.citemPedido = citemPedido;
    }

    public Pedido getCpedido() {
        return cpedido;
    }

    public void setCpedido(Pedido cpedido) {
        this.cpedido = cpedido;
    }

    public Produto getCproduto() {
        return cproduto;
    }

    public void setCproduto(Produto cproduto) {
        this.cproduto = cproduto;
    }

    public double getQtdeProduto() {
        return qtdeProduto;
    }

    public void setQtdeProduto(double qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
