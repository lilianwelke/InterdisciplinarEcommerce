package beans;

import dao.ClienteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Cliente;

@Stateless
public class ConsultaClienteBean implements ConsultaClienteBeanRemote {

    public void cadastrarCliente(int ccliente, String cliente, String cpf, String endereco,
            String cidade, int cep, String uf, int telefone) {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente client = clienteDAO.inserirCliente(ccliente, cliente, cpf, endereco, cidade, cep, uf, telefone);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarCliente(int ccliente, String cliente, String cpf, String endereco,
            String cidade, int cep, String uf, int telefone) {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente client = clienteDAO.atualizarCliente(ccliente, cliente, cpf, endereco, cidade, cep, uf, telefone);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarCliente(int ccliente) {

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente client = clienteDAO.deletarCliente(ccliente);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
