package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Categoria;
import util.ConnectionUtil;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Categoria inserirCategoria(int ccategoria, String categoria) throws Exception {

        try {
            Categoria categ = new Categoria();
            String SQL = "INSERT INTO CATEGORIA (CCATEGORIA, CATEGORIA) VALUES (?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccategoria);
            p.setString(2, categoria);
            p.execute();
            p.close();
            return categ;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Categoria atualizarCategoria(int ccategoria, String categoria) throws Exception {

        try {
            Categoria categ = new Categoria();
            String SQL = "UPDATE CATEGORIA SET CATEGORIA=? WHERE CCATEGORIA=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, categoria);
            p.setInt(2, ccategoria);
            p.execute();
            p.close();
            return categ;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Categoria deletarCategoria(int ccategoria) throws Exception {

        try {
            Categoria categ = new Categoria();
            String SQL = "DELETE FROM CATEGORIA WHERE CCATEGORIA = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccategoria);
            p.execute();
            p.close();
            return categ;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
}
