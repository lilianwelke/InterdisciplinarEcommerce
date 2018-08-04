package beans;

import javax.ejb.Remote;

@Remote
public interface ProdutoBeanRemote {

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

    public double[] calcularPromocao(int cproduto, double promocao);
//
//    public List<Produto> getPromocao(int cproduto, double promocao);
//
//    public List<Produto> getPreco(int precoInicial, int precoFinal);
//
//    public List<Produto> getTipo(int cproduto, int ccategoria, int cmarca);
//
//    public List<Produto> getConsulta(int cproduto, int ccategoria, int cmarca, String produto);
//
//    public List<Categoria> getCategoria();
//
//    public List<Marca> getMarca();
}
