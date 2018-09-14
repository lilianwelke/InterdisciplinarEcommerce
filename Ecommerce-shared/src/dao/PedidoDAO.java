package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Pedido;
import util.ConnectionUtil;

public class PedidoDAO {

    private Connection connection;

    public PedidoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Pedido inserirPedido(int ccliente, Date dataCompra, double totalCompra,
            String pagamento, String concluida, double frete) throws Exception {

        try {
            Pedido pedid = new Pedido();
            String SQL = "INSERT INTO PEDIDO (CCLIENTE, DATACOMPRA, TOTALCOMPRA, PAGAMENTO, CONCLUIDA, FRETE) "
                    + " VALUES (?, ?, ?, ?, ?, ?) RETURNING cpedido";
            PreparedStatement p = connection.prepareStatement(SQL);

            p.setInt(1, ccliente);
            p.setDate(2, new java.sql.Date(dataCompra.getTime()));
            p.setDouble(3, totalCompra);
            p.setString(4, pagamento);
            p.setString(5, concluida);
            p.setDouble(6, frete);
//            p.execute();
            ResultSet rs = p.executeQuery();
            int ultimoInserido = 0;
            while (rs.next()) {
                ultimoInserido = rs.getInt("cpedido");
            }
            p.close();
            pedid.setCpedido(ultimoInserido);
            return pedid;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Pedido atualizarPedido(int cpedido, int ccliente, Date dataCompra,
            double totalCompra, String pagamento, String concluida, double frete) throws Exception {
        try {
            Pedido pedid = new Pedido();
            String SQL = "UPDATE PEDIDO SET CCLIENTE=?, DATACOMPRA=?, TOTALCOMPRA=?, PAGAMENTO=?,"
                    + " CONCLUIDA=?, FRETE=? WHERE CPEDIDO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccliente);
            p.setDate(2, (java.sql.Date) dataCompra);
            p.setDouble(3, totalCompra);
            p.setString(4, pagamento);
            p.setString(5, concluida);
            p.setDouble(6, frete);
            p.setInt(7, cpedido);
            p.execute();
            p.close();
            return pedid;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Pedido deletarPedido(int cpedido) throws Exception {
        try {
            Pedido pedid = new Pedido();
            String SQL = "DELETE FROM PEDIDO WHERE CPEDIDO = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cpedido);
            p.execute();
            return pedid;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Pedido findById(int cPedido) {
        Pedido pedido = new Pedido();

        try {
            String SQL = "SELECT PEDIDO.CPEDIDO, CLIENTE.CCLIENTE, PEDIDO.DATACOMPRA, PEDIDO.TOTALCOMPRA, PEDIDO.PAGAMENTO, PEDIDO.CONCLUIDA,"
                    + " PEDIDO.FRETE "
                    + " FROM PEDIDO"
                    + " INNER JOIN CLIENTE ON (PEDIDO.CCLIENTE = CLIENTE.CCLIENTE)";
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Cliente cliente = new Cliente();

            while (rs.next()) {
                pedido.setCpedido(rs.getInt("cpedido"));
                pedido.setDataCompra(rs.getDate("datacompra"));
                pedido.setTotalCompra(rs.getDouble("totalcompra"));
                pedido.setPagamento(rs.getString("pagamento"));
                pedido.setConcluida(rs.getString("concluida"));
                pedido.setFrete(rs.getDouble("frete"));

                cliente.setCcliente(rs.getInt("ccliente"));

                pedido.setCcliente(cliente);

            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedido;
    }

    public List<Pedido> findAll() {
        List<Pedido> lista = new ArrayList<>();
        Pedido pedido = new Pedido();

        try {
            String SQL = "SELECT PEDIDO.CPEDIDO, CLIENTE.CCLIENTE, PEDIDO.DATACOMPRA, PEDIDO.TOTALCOMPRA, PEDIDO.PAGAMENTO, PEDIDO.CONCLUIDA,"
                    + " PEDIDO.FRETE "
                    + " FROM PEDIDO"
                    + " INNER JOIN CLIENTE ON (PEDIDO.CCLIENTE = CLIENTE.CCLIENTE)";
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Cliente cliente = new Cliente();

            while (rs.next()) {
                pedido.setCpedido(rs.getInt("cpedido"));
                pedido.setDataCompra(rs.getDate("datacompra"));
                pedido.setTotalCompra(rs.getDouble("totalcompra"));
                pedido.setPagamento(rs.getString("pagamento"));
                pedido.setConcluida(rs.getString("concluida"));
                pedido.setFrete(rs.getDouble("frete"));

                cliente.setCcliente(rs.getInt("ccliente"));

                pedido.setCcliente(cliente);

                lista.add(pedido);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public Pedido atualizarTotal(int cpedido, int citempedido, double precoPedido, int qtde, double totalCompra) throws Exception {
        try {
            Pedido pedid = new Pedido();
            String SQL = "UPDATE PEDIDO SET TOTALCOMPRA=? WHERE CPEDIDO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setDouble(1, totalCompra);
            p.setInt(2, cpedido);
            p.execute();
            p.close();
            return pedid;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

}
