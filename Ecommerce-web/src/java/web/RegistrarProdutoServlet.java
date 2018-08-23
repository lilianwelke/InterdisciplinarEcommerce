package web;

import beans.ProdutoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ProdutoDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Base64;
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
    private byte fotoProduto;

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

        int cproduto = produtoS.getJsonNumber("cproduto").intValue();
        int ccategoria = produtoS.getJsonNumber("ccategoria").intValue();
        int cmarca = produtoS.getJsonNumber("cmarca").intValue();
        String produto = produtoS.getJsonString("produto").getString();
        String descProduto = produtoS.getJsonString("descProduto").getString();

        ObjectMapper mapper = new ObjectMapper();
        ProdutoDTO produt = mapper.readValue(content, ProdutoDTO.class);

        // Array de strings a partir do conteudo recebido
        String imagemB64[] = produt.getUpload().split(",");

        // Converte a imagem em String base64 para byte[]
        byte[] image = Base64.getDecoder().decode(imagemB64[1]);

//        File file = new File("c:\\temp\\teste.jpg"); -- Essa do prof
        File file = new File("C:\\foto.jpg");
        FileOutputStream fis = new FileOutputStream(file);
        fis.write(image);
        fis.close();

//        String fotoProduto = produtoS.getJsonString("fotoProduto").getString();
        double precoProduto = produtoS.getJsonNumber("precoProduto").doubleValue();
        double promocao = produtoS.getJsonNumber("promocao").doubleValue();
        int qtdeEstoque = produtoS.getJsonNumber("qtdeEstoque").intValue();

        bean.cadastrarProduto(cproduto, ccategoria, cmarca, produto, descProduto, fotoProduto, precoProduto, promocao, qtdeEstoque);
        saida.print("{\"msg\":\"Certo\"}");
    }
}
