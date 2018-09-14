/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import model.ItemPedido;
import model.Pedido;

/**
 *
 * @author Gabrielli Vianna
 */
@Remote
public interface PedidoBeanRemote {

    public void cadastrarItem(int cpedido, int cproduto, String qtdeProduto, double precoProduto);

    public void atualizarItem(int citemPedido, int cpedido, int cproduto, String qtdeProduto, double precoProduto);

    public void deletarItem(int citemPedido);

    public Pedido cadastrarPedido(int ccliente, Date dataCompra, double totalCompra, String pagamento, String concluida, double frete);

    public void atualizarPedido(int cpedido, int ccliente, Date dataCompra, double totalCompra, String pagamento, String concluida, double frete);

    public void deletarPedido(int cpedido);

    public boolean concluido(String concluida);

    public List<ItemPedido> carrinho(int cpedido);

    public List<Pedido> vendas();

    public void totalCompra(int cpedido, int citempedido, double precoPedido, int qtde, double totalCompra);

    public List<Pedido> getPedido();
}
