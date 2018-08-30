package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Categoria findById() throws Exception {
        Categoria categoria = new Categoria();

        try {
            String SQL = "SELECT CATEGORIA.CCATEGORIA, CATEGORIA.CATEGORIA"
                    + " FROM CATEGORIA";

            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();

            categoria.setCcategoria(rs.getInt("ccategoria"));
            categoria.setCategoria(rs.getString("categoria"));
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }

    public List<Categoria> findAll() throws Exception {
        List<Categoria> lista = new ArrayList();
        Categoria categoria;
        String SQL = "SELECT CATEGORIA.CCATEGORIA, CATEGORIA.CATEGORIA"
                + " FROM CATEGORIA";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                categoria = new Categoria();
                categoria.setCcategoria(rs.getInt("ccategoria"));
                categoria.setCategoria(rs.getString("categoria"));

                lista.add(categoria);

            }

            rs.close();
            p.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
