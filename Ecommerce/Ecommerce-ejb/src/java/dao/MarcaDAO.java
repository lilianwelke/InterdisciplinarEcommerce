package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public Marca inserirMarca(int cmarca, String marca) throws Exception {
        try {
            Marca marc = new Marca();
            String SQL = "INSERT INTO PEDIDO (CMARCA, MARCA) VALUES (?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, cmarca);
            p.setString(2, marca);
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

    public Marca deletarPedido(int cmarca) throws Exception {

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
}
