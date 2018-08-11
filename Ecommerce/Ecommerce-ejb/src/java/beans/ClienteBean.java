package beans;

import dao.ClienteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Cliente;

@Stateless
public class ClienteBean implements ClienteBeanRemote, ClienteBeanLocal {

    @Override
    public void cadastrarCliente(int ccliente, String cliente, String cpf, String endereco,
            String cidade, String cep, String uf, String telefone, String senha) {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente client = clienteDAO.inserirCliente(ccliente, cliente, cpf, endereco, cidade, cep, uf, telefone, senha);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizarCliente(int ccliente, String cliente, String cpf, String endereco,
            String cidade, String cep, String uf, String telefone, String senha) {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente client = clienteDAO.atualizarCliente(ccliente, cliente, cpf, endereco, cidade, cep, uf, telefone, senha);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletarCliente(int ccliente) {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente client = clienteDAO.deletarCliente(ccliente);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
