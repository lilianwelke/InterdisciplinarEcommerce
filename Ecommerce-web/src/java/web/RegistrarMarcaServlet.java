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
public class RegistrarMarcaServlet extends HttpServlet {

    @EJB
    private ProdutoBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String content = "";
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject produtoMarca = reader.readObject();

        String marca = produtoMarca.getJsonString("marca").getString();

        bean.cadastrarMarca(marca);

        saida.print("{\"msg\":\"Certo\"}");
    }

}
