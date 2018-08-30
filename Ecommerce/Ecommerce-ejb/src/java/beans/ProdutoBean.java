package beans;

import dao.CategoriaDAO;
import dao.MarcaDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Categoria;
import model.Marca;
import model.Produto;

@Stateless
public class ProdutoBean implements ProdutoBeanRemote, ProdutoBeanLocal {

    @Override
    public void cadastrarProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, String fotoProduto, double precoProduto, double promocao, int qtdeEstoque) {

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = produtoDAO.inserirProduto(cproduto, ccategoria, cmarca, produto,
                    descProduto, fotoProduto, precoProduto, promocao, qtdeEstoque);

        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarProduto(int cproduto, int ccategoria, int cmarca, String produto,
            String descProduto, String fotoProduto, double precoProduto, double promocao, int qtdeEstoque) {

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = produtoDAO.atualizarProduto(cproduto, ccategoria, cmarca, produto,
                    descProduto, fotoProduto, precoProduto, promocao, qtdeEstoque);

        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarProduto(int cproduto) {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = produtoDAO.deletarProduto(cproduto);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cadastrarMarca(int cmarca, String marca) {

        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            Marca marc = marcaDAO.inserirMarca(cmarca, marca);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarMarca(int cmarca, String marca) {
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            Marca marc = marcaDAO.atualizarMarca(cmarca, marca);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletarMarca(int cmarca) {
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            Marca marca = marcaDAO.deletarMarca(cmarca);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cadastrarCategoria(int ccategoria, String categoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categ = categoriaDAO.inserirCategoria(ccategoria, categoria);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarCategoria(int ccategoria, String categoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categ = categoriaDAO.atualizarCategoria(ccategoria, categoria);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarCategoria(int ccategoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categ = categoriaDAO.deletarCategoria(ccategoria);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double[] calcularPromocao(int cproduto, double promocao) {

        int i = 0;
        double[] valor = {};
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ArrayList<Produto> prod = (ArrayList<Produto>) produtoDAO.promocaoProduto(cproduto, promocao);

            for (Produto p : prod) {
                valor[i] = cproduto * promocao / 100;
                i++;
            }

        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public List<Produto> getPromocao(int cproduto, double promocao) {
        ArrayList<Produto> prod = null;
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            prod = (ArrayList<Produto>) produtoDAO.promocaoProduto(cproduto, promocao);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public List<Produto> getPreco(int precoInicial, int precoFinal) {
        ArrayList<Produto> prod = null;
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            prod = (ArrayList<Produto>) produtoDAO.precoProduto(precoInicial, precoFinal);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;

    }

    public List<Produto> getTipo(int cproduto, int ccategoria, int cmarca) {
        ArrayList<Produto> prod = null;
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            prod = (ArrayList<Produto>) produtoDAO.tipoProduto(cproduto, ccategoria, cmarca);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public List<Produto> getConsulta(int cproduto, int ccategoria, int cmarca, String produto) {
        ArrayList<Produto> prod = null;
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            prod = (ArrayList<Produto>) produtoDAO.pesquisaProduto(cproduto, ccategoria, cmarca, produto);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public List<Categoria> getCategoria() {
        ArrayList<Categoria> categ = null;
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categ = (ArrayList<Categoria>) categoriaDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categ;
    }

    public List<Marca> getMarca() {
        ArrayList<Marca> marc = null;
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            marc = (ArrayList<Marca>) marcaDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return marc;
    }

    public List<Produto> getProduto() {
        ArrayList<Produto> prod = null;
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            prod = (ArrayList<Produto>) produtoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;
    }

    public void totalEstoque(int cproduto, int citempedido, int qtdeproduto, int qtdeestoque) {

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto prod = new Produto();

            qtdeestoque = qtdeestoque - qtdeproduto;
            prod = produtoDAO.atualizarEstoque(cproduto, citempedido, qtdeproduto, qtdeestoque);

        } catch (Exception ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
