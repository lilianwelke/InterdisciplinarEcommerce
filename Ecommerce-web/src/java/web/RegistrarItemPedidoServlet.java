package web;

import beans.PedidoBeanRemote;
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

public class RegistrarItemPedidoServlet extends HttpServlet {

    @EJB
    private PedidoBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String content = "";
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject regitroItemPedido = reader.readObject();

        int citemPedido = regitroItemPedido.getJsonNumber("citemPedido").intValue();
        int cpedido = regitroItemPedido.getJsonNumber("cpedido").intValue();
        int cproduto = regitroItemPedido.getJsonNumber("cproduto").intValue();
        double qtdeProduto = regitroItemPedido.getJsonNumber("qtdeProduto").doubleValue();
        double precoProduto = regitroItemPedido.getJsonNumber("precoProduto").doubleValue();

        bean.cadastrarItem(citemPedido, cpedido, cproduto, qtdeProduto, precoProduto);

        saida.print("{\"msg\":\"Certo\"}");
    }
}
