package beans;

import dao.ItemPedidoDAO;
import dao.PedidoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.ItemPedido;
import model.Pedido;

@Stateless
public class PedidoBean implements PedidoBeanRemote, PedidoBeanLocal {

    @Override
    public void cadastrarItem(int cpedido, int cproduto, String qtdeProduto, double precoProduto) {

        try {
            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
            ItemPedido itemPedido = itemPedidoDAO.inserirItemPedido(cpedido, cproduto, qtdeProduto, precoProduto);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void atualizarItem(int citemPedido, int cpedido, int cproduto, String qtdeProduto, double precoProduto) {

        try {
            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
            ItemPedido itemPedido = itemPedidoDAO.atualizarItemPedido(citemPedido, cpedido, cproduto, qtdeProduto, precoProduto);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deletarItem(int citemPedido) {

        try {
            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
            ItemPedido itemPedido = itemPedidoDAO.deletarItemPedido(citemPedido);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Pedido cadastrarPedido(int ccliente, Date dataCompra, double totalCompra,
            String pagamento, String concluida, double frete) {
        Pedido pedido = null;
        try {
            //if (concluida.equals("N")) {
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedido = pedidoDAO.inserirPedido(ccliente, dataCompra, totalCompra, pagamento, concluida, frete);
            //}

        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedido;

    }

    @Override
    public void atualizarPedido(int cpedido, int ccliente, Date dataCompra, double totalCompra,
            String pagamento, String concluida, double frete) {

        try {
            if (concluida.equals("N")) {
                PedidoDAO pedidoDAO = new PedidoDAO();
                Pedido pedido = pedidoDAO.atualizarPedido(cpedido, ccliente, dataCompra, totalCompra, pagamento, concluida, frete);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deletarPedido(int cpedido) {

        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedido = pedidoDAO.deletarPedido(cpedido);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean concluido(String concluida) {
        if (concluida.equals("N")) {
            return true;
        } else {
            return false;
        }
    }

//    @Override
    public List<ItemPedido> carrinho(int cpedido) {
        ArrayList<ItemPedido> itemPed = null;

        try {
            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
            itemPed = (ArrayList<ItemPedido>) itemPedidoDAO.getCarrinho(cpedido);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itemPed;
    }

//    @Override
    public List<Pedido> vendas() {
        ArrayList<Pedido> ped = null;

        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            ped = (ArrayList<Pedido>) pedidoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ped;
    }

    @Override
    public void totalCompra(int cpedido, int citempedido, double precoPedido, int qtde, double totalCompra) {
        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            Pedido pedid = new Pedido();

            totalCompra = precoPedido * qtde;
            pedid = pedidoDAO.atualizarTotal(cpedido, citempedido, precoPedido, qtde, totalCompra);
        } catch (Exception ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Pedido> getPedido() {
        ArrayList<Pedido> ped = null;
        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            ped = (ArrayList<Pedido>) pedidoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ped;
    }

}
