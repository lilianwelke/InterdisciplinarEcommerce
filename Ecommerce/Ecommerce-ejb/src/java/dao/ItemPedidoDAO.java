package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import util.ConnectionUtil;

public class ItemPedidoDAO {

    private Connection connection;

    public ItemPedidoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public ItemPedido inserirItemPedido(int citemPedido, int cpedido, int cproduto, double qtdeProduto, double precoProduto) throws Exception {

        try {
            ItemPedido itemPedido = new ItemPedido();
            String SQL = "INSERT INTO ITEMPEDIDO (CITEMPEDIDO, CPEDIDO, CPRODUTO, QTDEPRODUTO, PRECOPRODUTO) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, citemPedido);
            p.setInt(2, cpedido);
            p.setInt(3, cproduto);
            p.setDouble(4, qtdeProduto);
            p.setDouble(5, precoProduto);
            p.execute();
            p.close();
            return itemPedido;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public ItemPedido atualizarItemPedido(int citemPedido, int cpedido, int cproduto, double qtdeProduto, double precoProduto) throws Exception {
        try {
            ItemPedido itemPedido = new ItemPedido();
            String SQL = "UPDATE ITEMPEDIDO SET CPEDIDO=?, CPRODUTO=?, QTDEPRODUTO=?, PRECOPRODUTO=? WHERE CITEMPEDIDO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cpedido);
            p.setInt(2, cproduto);
            p.setDouble(3, qtdeProduto);
            p.setDouble(4, precoProduto);
            p.setInt(5, citemPedido);
            p.execute();
            p.close();
            return itemPedido;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public ItemPedido deletarItemPedido(int citemPedido) throws Exception {
        try {
            ItemPedido itemPedido = new ItemPedido();
            String SQL = "DELETE FROM ITEMPEDIDO WHERE CITEMPEDIDO = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, citemPedido);
            p.execute();
            p.close();
            return itemPedido;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public ItemPedido findById(int cItemPedido) {
        ItemPedido itemPedido = new ItemPedido();

        try {
            String SQL = "SELECT ITEMPEDIDO.CITEMPEDIDO, PEDIDO.CPEDIDO, PRODUTO.CPRODUTO, ITEMPEDIDO.QTDEPRODUTO, "
                    + " ITEMPEDIDO.PRECOPRODUTO"
                    + " FROM ITEMPEDIDO"
                    + " INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = ITEMPEDIDO.CPEDIDO)"
                    + " INNER JOIN PRODUTO ON (PRODUTO.CPRODUTO = ITEMPEDIDO.CPRODUTO)";

            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Pedido pedido = new Pedido();
            Produto produto = new Produto();

            while (rs.next()) {
                itemPedido.setCitemPedido(rs.getInt("citempedido"));
                itemPedido.setQtdeProduto(rs.getDouble("qtdeproduto"));
                itemPedido.setPrecoProduto(rs.getDouble("precoproduto"));

                pedido.setCpedido(rs.getInt("cpedido"));

                produto.setCproduto(rs.getInt("cproduto"));

                itemPedido.setCpedido(pedido);
                itemPedido.setCproduto(produto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return itemPedido;

    }

    public List<ItemPedido> listAll() {
        List<ItemPedido> lista = new ArrayList<>();
        ItemPedido itemPedido = new ItemPedido();

        try {
            String SQL = "SELECT ITEMPEDIDO.CITEMPEDIDO, PEDIDO.CPEDIDO, PRODUTO.CPRODUTO, ITEMPEDIDO.QTDEPRODUTO, "
                    + " ITEMPEDIDO.PRECOPRODUTO"
                    + " FROM ITEMPEDIDO"
                    + " INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = ITEMPEDIDO.CPEDIDO)"
                    + " INNER JOIN PRODUTO ON (PRODUTO.CPRODUTO = ITEMPEDIDO.CPRODUTO)";

            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Pedido pedido = new Pedido();
            Produto produto = new Produto();

            while (rs.next()) {
                itemPedido.setCitemPedido(rs.getInt("citempedido"));
                itemPedido.setQtdeProduto(rs.getDouble("qtdeproduto"));
                itemPedido.setPrecoProduto(rs.getDouble("precoproduto"));

                pedido.setCpedido(rs.getInt("cpedido"));

                produto.setCproduto(rs.getInt("cproduto"));

                itemPedido.setCpedido(pedido);
                itemPedido.setCproduto(produto);

                lista.add(itemPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<ItemPedido> getCarrinho(int cPedido) {
        List<ItemPedido> lista = new ArrayList<>();
        ItemPedido itemPedido = new ItemPedido();

        try {
            String SQL = "SELECT ITEMPEDIDO.CITEMPEDIDO, PEDIDO.CPEDIDO, PRODUTO.CPRODUTO, ITEMPEDIDO.QTDEPRODUTO, "
                    + " ITEMPEDIDO.PRECOPRODUTO"
                    + " FROM ITEMPEDIDO"
                    + " INNER JOIN PEDIDO ON (PEDIDO.CPEDIDO = ITEMPEDIDO.CPEDIDO)"
                    + " INNER JOIN PRODUTO ON (PRODUTO.CPRODUTO = ITEMPEDIDO.CPRODUTO)"
                    + " WHERE PEDIDO.CPEDIDO = " + cPedido;

            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Pedido pedido = new Pedido();
            Produto produto = new Produto();

            while (rs.next()) {
                itemPedido.setCitemPedido(rs.getInt("citempedido"));
                itemPedido.setQtdeProduto(rs.getDouble("qtdeproduto"));
                itemPedido.setPrecoProduto(rs.getDouble("precoproduto"));

                pedido.setCpedido(rs.getInt("cpedido"));

                produto.setCproduto(rs.getInt("cproduto"));

                itemPedido.setCpedido(pedido);
                itemPedido.setCproduto(produto);

                lista.add(itemPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
}
