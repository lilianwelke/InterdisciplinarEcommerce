package web;

import beans.ClienteBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarClientesServlet extends HttpServlet {

    @EJB
    private ClienteBeanRemote bean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter saida = resp.getWriter();

        String cpf = "";
        try (BufferedReader leitor = req.getReader()) {
            cpf = leitor.lines().collect(Collectors.joining());
        }

        JsonReader reader = Json.createReader(new StringReader(cpf));
        JsonObject cliente = reader.readObject();

        String cpfCliente = cliente.getJsonString("cpf").getString();

        String retornoCliente = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            retornoCliente = mapper.writeValueAsString(bean.getProdutoById(cpfCliente));
        } catch (Exception ex) {
            Logger.getLogger(RegistrarClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida.write(retornoCliente);
    }
}
