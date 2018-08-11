package beans;

import javax.ejb.Remote;

@Remote
public interface ClienteBeanRemote {

    public void cadastrarCliente(int ccliente, String cliente, String cpf, String endereco, String cidade, String cep, String uf, String telefone, String senha);

    public void atualizarCliente(int ccliente, String cliente, String cpf, String endereco, String cidade, String cep, String uf, String telefone, String senha);

    public void deletarCliente(int ccliente);

}
