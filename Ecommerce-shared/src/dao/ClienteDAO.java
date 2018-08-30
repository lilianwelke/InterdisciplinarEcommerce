package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;
import util.ConnectionUtil;

/**
 *
 * @author Gabrielli Vianna
 */
public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }

    public Cliente inserirCliente(int ccliente, String cliente, String cpf, String endereco,
            String cidade, String cep, String uf, String telefone, String senha) throws Exception {
        try {
            Cliente client = new Cliente();
            String SQL = "INSERT INTO CLIENTE (CCLIENTE, CLIENTE, CPF, ENDERECO, CIDADE, CEP, UF, TELEFONE, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccliente);
            p.setString(2, cliente);
            p.setString(3, cpf);
            p.setString(4, endereco);
            p.setString(5, cidade);
            p.setString(6, cep);
            p.setString(7, uf);
            p.setString(8, telefone);
            p.setString(9, senha);
            p.execute();
            p.close();
            return client;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Cliente atualizarCliente(int ccliente, String cliente, String cpf, String endereco,
            String cidade, String cep, String uf, String telefone, String senha) throws Exception {

        try {
            Cliente client = new Cliente();
            String SQL = "UPDATE CLIENTE SET CLIENTE=?, CPF=?, ENDERECO=?, "
                    + "CIDADE=?, CEP=?, UF=?, TELEFONE=?, SENHA=? WHERE CCLIENTE=?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, cliente);
            p.setString(2, cpf);
            p.setString(3, endereco);
            p.setString(4, cidade);
            p.setString(5, cep);
            p.setString(6, uf);
            p.setString(7, telefone);
            p.setInt(8, ccliente);
            p.setString(9, senha);
            p.execute();
            p.close();
            return client;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Cliente deletarCliente(int ccliente) throws Exception {

        try {
            Cliente client = new Cliente();
            String SQL = "DELETE FROM CLIENTE WHERE CCLIENTE = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, ccliente);
            p.execute();
            p.close();
            return client;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

}