package beans;

import dao.CategoriaDAO;
import dao.MarcaDAO;
import dao.ProdutoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Categoria;
import model.Marca;
import model.Produto;

@Stateless
public class ConsultaProdutoBean implements ConsultaProdutoBeanRemote {

    @Override
    public void cadastrarProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, byte fotoProduto, double precoProduto, double promocao, int qtdeEstoque) {

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = produtoDAO.inserirProduto(cproduto, ccategoria, cmarca, produto,
                    descProduto, fotoProduto, precoProduto, promocao, qtdeEstoque);

        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, byte fotoProduto, double precoProduto, double promocao, int qtdeEstoque) {

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = produtoDAO.atualizarProduto(cproduto, ccategoria, cmarca, produto,
                    descProduto, fotoProduto, precoProduto, promocao, qtdeEstoque);

        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarProduto(int cproduto) {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = produtoDAO.deletarProduto(cproduto);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cadastrarMarca(int cmarca, String marca) {

        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            Marca marc = marcaDAO.inserirMarca(cmarca, marca);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarMarca(int cmarca, String marca) {
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            Marca marc = marcaDAO.atualizarMarca(cmarca, marca);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletarMarca(int cmarca) {
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            Marca marca = marcaDAO.deletarPedido(cmarca);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cadastrarCategoria(int ccategoria, String categoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categ = categoriaDAO.inserirCategoria(ccategoria, categoria);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarCategoria(int ccategoria, String categoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categ = categoriaDAO.atualizarCategoria(ccategoria, categoria);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarCategoria(int ccategoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categ = categoriaDAO.deletarCategoria(ccategoria);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
