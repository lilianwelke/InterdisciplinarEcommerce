package model;

import java.util.Date;

public class Pedido {
    private int cpedido;
    private Cliente ccliente;
    private Date dataCompra;
    private double totalCompra;
    private String pagamento;
    private String concluida;
    private double frete;

    public int getCpedido() {
        return cpedido;
    }

    public void setCpedido(int cpedido) {
        this.cpedido = cpedido;
    }

    public Cliente getCcliente() {
        return ccliente;
    }

    public void setCcliente(Cliente ccliente) {
        this.ccliente = ccliente;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getConcluida() {
        return concluida;
    }

    public void setConcluida(String concluida) {
        this.concluida = concluida;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }
}
