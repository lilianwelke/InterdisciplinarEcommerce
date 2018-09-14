package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Cliente inserirCliente(String cliente, String cpf, String endereco,
            String cidade, String cep, String uf, String telefone, String senha) throws Exception {
        try {
            Cliente client = new Cliente();
            String SQL = "INSERT INTO CLIENTE (CLIENTE, CPF, ENDERECO, CIDADE, CEP, UF, TELEFONE, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = connection.prepareStatement(SQL);

            p.setString(1, cliente);
            p.setString(2, cpf);
            p.setString(3, endereco);
            p.setString(4, cidade);
            p.setString(5, cep);
            p.setString(6, uf);
            p.setString(7, telefone);
            p.setString(8, senha);
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

    public Cliente findByCpf(String cpf) throws Exception {
        Cliente cli = null;

        try {
            String SQL = "SELECT CLIENTE.CCLIENTE, CLIENTE.CLIENTE, CLIENTE.CPF, CLIENTE.ENDERECO, CLIENTE.CIDADE, CLIENTE.CEP, "
                    + " CLIENTE.UF, CLIENTE.TELEFONE"
                    + " FROM CLIENTE"
                    + " WHERE CLIENTE.CPF = ?";
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, cpf);
            ResultSet rs = p.executeQuery();

            cli = new Cliente();

            if (rs.next()) {

                cli.setCcliente(rs.getInt("ccliente"));
                cli.setCliente(rs.getString("cliente"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setCidade(rs.getString("cidade"));
                cli.setCep(rs.getString("cep"));
                cli.setUf(rs.getString("uf"));
                cli.setTelefone(rs.getString("telefone"));

            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cli;
    }

}
