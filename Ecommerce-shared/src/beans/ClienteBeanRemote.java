package beans;

import javax.ejb.Remote;

@Remote
public interface ClienteBeanRemote {

    public void cadastrarCliente(int ccliente, String cliente, String cpf, String endereco, String cidade, int cep, String uf, int telefone);

    public void atualizarCliente(int ccliente, String cliente, String cpf, String endereco, String cidade, int cep, String uf, int telefone);

    public void deletarCliente(int ccliente);

}
