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
import model.Marca;
import model.Produto;
import util.ConnectionUtil;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Produto inserirProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, byte fotoProduto, double precoProduto, double promocao, int qtdeEstoque) throws Exception {
        try {
            Produto prod = new Produto();
            String SQL = "INSERT INTO PRODUTO (CPRODUTO, CCATEGORIA, CMARCA, PRODUTO, DESCPRODUTO, FOTOPRODUTO, PRECOPRODUTO, PROMOCAO, QTDEESTOQUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cproduto);
            p.setInt(2, ccategoria);
            p.setInt(3, cmarca);
            p.setString(4, produto);
            p.setString(5, descProduto);
            p.setByte(6, fotoProduto);
            p.setDouble(7, precoProduto);
            p.setDouble(8, promocao);
            p.setInt(9, qtdeEstoque);
            p.execute();
            p.close();
            return prod;
        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir produto!", ex);
        }
    }

    public Produto atualizarProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, byte fotoProduto, double precoProduto, double promocao, int qtdeEstoque) throws Exception {

        try {
            Produto prod = new Produto();
            String SQL = "UPDATE PRODUTO SET CCATEGORIA=?, CMARCA=?, PRODUTO=?, DESCPRODUTO=?, FOTOPRODUTO=?,"
                    + " PRECOPRODUTO=?, PROMOCAO=?, QTDEESTOQUE=? WHERE CPRODUTO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccategoria);
            p.setInt(2, cmarca);
            p.setString(3, produto);
            p.setString(4, descProduto);
            p.setByte(5, fotoProduto);
            p.setDouble(6, precoProduto);
            p.setDouble(7, promocao);
            p.setInt(8, qtdeEstoque);
            p.setInt(9, cproduto);
            p.execute();
            p.close();
            return prod;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Produto deletarProduto(int cproduto) throws Exception {

        try {
            Produto prod = new Produto();
            String SQL = "DELETE FROM PRODUTO WHERE CPRODUTO = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cproduto);
            p.execute();
            return prod;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Produto findById(int cproduto) throws Exception {
        Produto prod = new Produto();

        try {
            String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                    + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                    + " FROM PRODUTO"
                    + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                    + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)";
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getBytes("fotoproduto"));
                prod.setPrecoProduto(rs.getDouble("precoproduto"));
                prod.setPromocao(rs.getDouble("promocao"));
                prod.setQtdeEstoque(rs.getInt("qtdestoque"));

                categ.setCcategoria(rs.getInt("ccategoria"));
                categ.setCategoria(rs.getString("categoria"));

                marc.setCmarca(rs.getInt("cproduto"));
                marc.setMarca(rs.getString("produto"));

                prod.setCcategoria(categ);
                prod.setCmarca(marc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public List<Produto> findAll() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        Produto prod = new Produto();

        try {
            String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                    + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                    + " FROM PRODUTO"
                    + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                    + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)";
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getBytes("fotoproduto"));
                prod.setPrecoProduto(rs.getDouble("precoproduto"));
                prod.setPromocao(rs.getDouble("promocao"));
                prod.setQtdeEstoque(rs.getInt("qtdestoque"));

                categ.setCcategoria(rs.getInt("ccategoria"));
                categ.setCategoria(rs.getString("categoria"));

                marc.setCmarca(rs.getInt("cproduto"));
                marc.setMarca(rs.getString("produto"));

                prod.setCcategoria(categ);
                prod.setCmarca(marc);

                lista.add(prod);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Produto> pesquisaProduto(int produto, int marca, int categoria) throws SQLException {
        List<Produto> lista = new ArrayList<>();
        Produto prod = new Produto();

        String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                + " FROM PRODUTO"
                + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)";

        if (produto > 0) {
            SQL = SQL + "WHERE PRODUTO.CPRODUTO = " + produto;
        }

        if (marca > 0) {
            SQL = SQL + "WHERE CATEGORIA.CCATEGORIA = " + marca;
        }

        if (categoria > 0) {
            SQL = SQL + "WHERE MARCA.CMARCA = " + marca;
        }

        PreparedStatement p = connection.prepareStatement(SQL);
        ResultSet rs = p.executeQuery();

        return lista;
    }

}
