package beans;

import javax.ejb.Remote;
import model.Cliente;

@Remote
public interface ClienteBeanRemote {

    public void cadastrarCliente(String cliente, String cpf, String endereco, String cidade, String cep, String uf, String telefone, String senha);

    public void atualizarCliente(int ccliente, String cliente, String cpf, String endereco, String cidade, String cep, String uf, String telefone, String senha);

    public void deletarCliente(int ccliente);

    public Cliente getProdutoById(String cliente);

}
