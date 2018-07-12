package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import model.Pedido;
import util.ConnectionUtil;

public class PedidoDAO {

    private Connection connection;

    public PedidoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Pedido inserirPedido(int cpedido, int ccliente, Date dataCompra, Double totalCompra, String pagamento, String concluida, Double frete) throws Exception {

        try {
            Pedido pedid = new Pedido();
            String SQL = "INSERT INTO PEDIDO (CPEDIDO, CCLIENTE, DATACOMPRA, TOTALCOMPRA, PAGAMENTO, CONCLUIDA, FRETE) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cpedido);
            p.setInt(2, ccliente);
            p.setDate(3, (java.sql.Date) dataCompra);
            p.setDouble(4, totalCompra);
            p.setString(5, pagamento);
            p.setString(6, concluida);
            p.setDouble(7, frete);
            p.execute();
            p.close();
            return pedid;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void atualizarPedido(Pedido pedido) throws Exception {
        String SQL = "UPDATE PEDIDO SET CCPEDIDO=?, CCLIENTE=?, DATACOMPRA=?, TOTALCOMPRA=?, PAGAMENTO=?,"
                + " CONCLUIDA=?, FRETE=? WHERE CPEDIDO=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, pedido.getCpedido());
            p.setString(2, pedido.getCcliente().getCliente());
            p.setDate(3, new java.sql.Date(pedido.getDataCompra().getTime()));
            p.setDouble(4, pedido.getTotalCompra());
            p.setString(5, pedido.getPagamento());
            p.setString(6, pedido.getConcluida());
            p.setDouble(7, pedido.getFrete());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void deletarPedido(Pedido pedido) throws Exception {
        String SQL = "DELETE FROM PEDIDO WHERE CPEDIDO = ?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, pedido.getCpedido());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
}
