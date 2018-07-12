package beans;

import javax.ejb.Remote;

@Remote
public interface ConsultaProdutoBeanRemote {

    public void cadastrarProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, byte fotoProduto, double precoProduto, double promocao, int qtdeEstoque);

    public void atualizarProduto(int cproduto, int ccategoria, int cmarca, String produto, String descProduto, byte fotoProduto, double precoProduto, double promocao, int qtdeEstoque);

    public void deletarProduto(int cproduto);

    public void cadastrarMarca(int cmarca, String marca);

    public void atualizarMarca(int cmarca, String marca);

    public void deletarMarca(int cmarca);

    public void cadastrarCategoria(int ccategoria, String categoria);

    public void atualizarCategoria(int ccategoria, String categoria);

    public void deletarCategoria(int ccategoria);
}
