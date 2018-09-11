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
            String descProduto, String fotoProduto, double precoProduto, double promocao, int qtdeEstoque) throws Exception {
        try {
            Produto prod = new Produto();
            String SQL = "INSERT INTO PRODUTO (CPRODUTO, CCATEGORIA, CMARCA, PRODUTO, DESCPRODUTO, FOTOPRODUTO, PRECOPRODUTO, PROMOCAO, QTDEESTOQUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cproduto);
            p.setInt(2, ccategoria);
            p.setInt(3, cmarca);
            p.setString(4, produto);
            p.setString(5, descProduto);
            p.setString(6, fotoProduto);
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
            String descProduto, String fotoProduto, double precoProduto, double promocao, int qtdeEstoque) throws Exception {

        try {
            Produto prod = new Produto();
            String SQL = "UPDATE PRODUTO SET CCATEGORIA=?, CMARCA=?, PRODUTO=?, DESCPRODUTO=?, FOTOPRODUTO=?,"
                    + " PRECOPRODUTO=?, PROMOCAO=?, QTDEESTOQUE=? WHERE CPRODUTO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccategoria);
            p.setInt(2, cmarca);
            p.setString(3, produto);
            p.setString(4, descProduto);
            p.setString(5, fotoProduto);
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
        Produto prod = null;

        try {
            String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                    + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                    + " FROM PRODUTO"
                    + " INNER JOIN CATEGORIA ON (PRODUTO.CCATEGORIA = CATEGORIA.CCATEGORIA)"
                    + " INNER JOIN MARCA ON (MARCA.CMARCA = PRODUTO.CMARCA)"
                    + " WHERE PRODUTO.CPRODUTO = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cproduto);
            ResultSet rs = p.executeQuery();

            Categoria categ = new Categoria();
            Marca marc = new Marca();
            prod = new Produto();

            if (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getString("fotoproduto"));
                prod.setPrecoProduto(rs.getDouble("precoproduto"));
                prod.setPromocao(rs.getDouble("promocao"));
                prod.setQtdeEstoque(rs.getInt("qtdeestoque"));

                categ.setCcategoria(rs.getInt("ccategoria"));
                categ.setCategoria(rs.getString("categoria"));

                marc.setCmarca(rs.getInt("cproduto"));
                marc.setMarca(rs.getString("produto"));

                prod.setCcategoria(categ);
                prod.setCmarca(marc);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public List<Produto> findAll() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        Produto prod;

        try {
            String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                    + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                    + " FROM PRODUTO"
                    + " INNER JOIN CATEGORIA ON (PRODUTO.CCATEGORIA = CATEGORIA.CCATEGORIA)"
                    + " INNER JOIN MARCA ON (PRODUTO.CMARCA = MARCA.CMARCA)";
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {
                prod = new Produto();
                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getString("fotoproduto"));
                prod.setPrecoProduto(rs.getDouble("precoproduto"));
                prod.setPromocao(rs.getDouble("promocao"));
                prod.setQtdeEstoque(rs.getInt("qtdeestoque"));

                categ.setCcategoria(rs.getInt("ccategoria"));
                categ.setCategoria(rs.getString("categoria"));

                marc.setCmarca(rs.getInt("cmarca"));
                marc.setMarca(rs.getString("marca"));

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

    public List<Produto> promocaoProduto(int cproduto, double promocao) {
        List<Produto> lista = new ArrayList<>();
        Produto prod = new Produto();

        String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                + " FROM PRODUTO"
                + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)"
                + " WHERE " + promocao + " IS NOT NULL"
                + " AND PRODUTO.CPRODUTO = " + cproduto;

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getString("fotoproduto"));
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

    public List<Produto> precoProduto(double precoInicial, double precoFinal) {
        List<Produto> lista = new ArrayList<>();
        Produto prod = new Produto();

        String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                + " FROM PRODUTO"
                + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)"
                + " WHERE PRECOPRODUTO BETWEEN " + precoInicial + "AND " + precoFinal;
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getString("fotoproduto"));
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

    public List<Produto> tipoProduto(int cproduto, int cmarca, int ccategoria) throws SQLException {
        List<Produto> lista = new ArrayList<>();
        Produto prod = new Produto();

        String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                + " FROM PRODUTO"
                + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)";

        if (cproduto > 0) {
            SQL = SQL + "WHERE PRODUTO.CPRODUTO = " + cproduto;
        }

        if (ccategoria > 0) {
            SQL = SQL + "AND CATEGORIA.CCATEGORIA = " + ccategoria;
        }

        if (cmarca > 0) {
            SQL = SQL + "AND MARCA.CMARCA = " + cmarca;
        }

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getString("fotoproduto"));
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

    public List<Produto> pesquisaProduto(int cproduto, int cmarca, int ccategoria, String produto) throws SQLException {
        List<Produto> lista = new ArrayList<>();
        Produto prod = new Produto();

        String SQL = "SELECT PRODUTO.CPRODUTO, CATEGORIA.CCATEGORIA, MARCA.CMARCA, PRODUTO.PRODUTO, PRODUTO.DESCPRODUTO, PRODUTO.FOTOPRODUTO, "
                + " PRODUTO.PRECOPRODUTO, PRODUTO.PROMOCAO, PRODUTO.QTDEESTOQUE, CATEGORIA.CATEGORIA, MARCA.MARCA"
                + " FROM PRODUTO"
                + " INNER JOIN CATEGORIA ON (PRODUTO.CPRODUTO = CATEGORIA.CPRODUTO)"
                + " INNER JOIN MARCA ON (MARCA.CPRODUTO = MARCA.CPRODUTO)";

        if (cproduto > 0) {
            SQL = SQL + "WHERE PRODUTO.CPRODUTO = " + cproduto;
        }

        if (ccategoria > 0) {
            SQL = SQL + "WHERE CATEGORIA.CCATEGORIA = " + ccategoria;
        }

        if (cmarca > 0) {
            SQL = SQL + "WHERE MARCA.CMARCA = " + cmarca;
        }

        if (!produto.isEmpty()) {
            SQL = SQL + "WHERE MARCA.CMARCA = " + produto;
        }

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            Categoria categ = new Categoria();
            Marca marc = new Marca();

            while (rs.next()) {

                prod.setCproduto(rs.getInt("cproduto"));
                prod.setProduto(rs.getString("produto"));
                prod.setDescProduto(rs.getString("descproduto"));
                prod.setFotoProduto(rs.getString("fotoproduto"));
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

    public Produto atualizarEstoque(int cproduto, int citempedido, int qtdeproduto, int qtdeestoque) throws Exception {
        try {
            Produto prod = new Produto();
            String SQL = "UPDATE PRODUTO SET QTDEESTOQUE=? WHERE CPRODUTO=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, qtdeestoque);
            p.setInt(2, cproduto);
            p.execute();
            p.close();
            return prod;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

}
