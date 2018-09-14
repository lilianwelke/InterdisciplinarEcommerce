package web;

import beans.ProdutoBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabrielli Vianna
 */
public class RegistrarProdutoServlet extends HttpServlet {

    @EJB
    private ProdutoBeanRemote bean;
//    private byte fotoProduto;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String content = "";
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject produtoS = reader.readObject();

        int ccategoria = produtoS.getJsonNumber("ccategoria").intValue();
        int cmarca = produtoS.getJsonNumber("cmarca").intValue();
        String produto = produtoS.getJsonString("produto").getString();
        String descProduto = produtoS.getJsonString("descProduto").getString();
        String fotoProduto = produtoS.getJsonString("fotoProduto").getString();
        double precoProduto = produtoS.getJsonNumber("precoProduto").doubleValue();
        double promocao = produtoS.getJsonNumber("promocao").doubleValue();
        int qtdeEstoque = produtoS.getJsonNumber("qtdeEstoque").intValue();

        bean.cadastrarProduto(ccategoria, cmarca, produto, descProduto, fotoProduto, precoProduto, promocao, qtdeEstoque);
        saida.print("{\"msg\":\"Certo\"}");
    }
}
