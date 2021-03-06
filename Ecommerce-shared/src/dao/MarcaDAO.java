package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Marca;
import util.ConnectionUtil;

/**
 *
 * @author Gabrielli Vianna
 */
public class MarcaDAO {

    private Connection connection;

    public MarcaDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Marca inserirMarca(String marca) throws Exception {
        try {
            Marca marc = new Marca();
            String SQL = "INSERT INTO MARCA (MARCA) VALUES (?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, marca);
            p.execute();
            p.close();
            return marc;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Marca atualizarMarca(int cmarca, String marca) throws Exception {
        try {
            Marca marc = new Marca();
            String SQL = "UPDATE MARCA SET MARCA=? WHERE CMARCA=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, marca);
            p.setInt(2, cmarca);
            p.execute();
            p.close();
            return marc;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Marca deletarMarca(int cmarca) throws Exception {

        try {
            Marca marc = new Marca();
            String SQL = "DELETE FROM MARCA WHERE CMARCA = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cmarca);
            p.execute();
            p.close();
            return marc;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Marca findById() throws Exception {
        Marca marca = new Marca();

        try {
            String SQL = "SELECT MARCA.CMARCA, MARCA.MARCA"
                    + " FROM MARCA";

            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();

            marca.setCmarca(rs.getInt("cproduto"));
            marca.setMarca(rs.getString("produto"));
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marca;
    }

    public List<Marca> findAll() throws Exception {
        List<Marca> lista = new ArrayList<>();
        Marca marca;
        String SQL = "SELECT MARCA.CMARCA, MARCA.MARCA"
                + " FROM MARCA";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                marca = new Marca();
                marca.setCmarca(rs.getInt("cmarca"));
                marca.setMarca(rs.getString("marca"));

                lista.add(marca);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
