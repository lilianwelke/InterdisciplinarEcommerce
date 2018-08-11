package web;

import beans.ClienteBeanRemote;
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

public class RegistrarClienteServlet extends HttpServlet {

    @EJB
    private ClienteBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String content = "";
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject regitroCliente = reader.readObject();

        int ccliente = regitroCliente.getJsonNumber("ccliente").intValue();
        String cliente = regitroCliente.getJsonString("cliente").getString();
        String cpf = regitroCliente.getJsonString("cpf").getString();
        String endereco = regitroCliente.getJsonString("endereco").getString();
        String cidade = regitroCliente.getJsonString("cidade").getString();
        String cep = regitroCliente.getJsonString("cep").getString();
        String uf = regitroCliente.getJsonString("uf").getString();
        String telefone = regitroCliente.getJsonString("telefone").getString();
        String senha = regitroCliente.getJsonString("senha").getString();

        bean.cadastrarCliente(ccliente, cliente, cpf, endereco, cidade, cep, uf, telefone, senha);

        saida.print("{\"msg\":\"Certo\"}");
    }
}
