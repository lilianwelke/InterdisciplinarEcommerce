package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.ItemPedido;
import util.ConnectionUtil;

public class ItemPedidoDAO {

    private Connection connection;

    public ItemPedidoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public void inserirItemPedido(ItemPedido itemPedido) throws Exception {
        String SQL = "INSERT INTO ITEMPEDIDO VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, itemPedido.getCitemPedido());
            p.setInt(2, itemPedido.getCpedido().getCpedido());
            p.setInt(3, itemPedido.getCproduto().getCproduto());
            p.setDouble(4, itemPedido.getQtdeProduto());
            p.setDouble(5, itemPedido.getPrecoProduto());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void atualizarItemPedido(ItemPedido itemPedido) throws Exception {
        String SQL = "UPDATE ITEMPEDIDO SET CPEDIDO=?, CPRODUTO=?, QTDEPRODUTO=?, PRECOPRODUTO=? WHERE CITEMPEDIDO=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, itemPedido.getCitemPedido());
            p.setInt(2, itemPedido.getCpedido().getCpedido());
            p.setInt(3, itemPedido.getCproduto().getCproduto());
            p.setDouble(4, itemPedido.getQtdeProduto());
            p.setDouble(5, itemPedido.getPrecoProduto());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void deletarItemPedido(ItemPedido itemPedido) throws Exception {
        String SQL = "DELETE FROM ITEMPEDIDO WHERE CITEMPEDIDO = ?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, itemPedido.getCitemPedido());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
}
