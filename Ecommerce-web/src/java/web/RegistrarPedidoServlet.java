package web;

import beans.PedidoBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RegistrarPedidoServlet extends HttpServlet {

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
        JsonObject regitroPedido = reader.readObject();

        int cpedido = regitroPedido.getJsonNumber("cpedido").intValue();
        int ccliente = regitroPedido.getJsonNumber("ccliente").intValue();
        String dataCompra = regitroPedido.getJsonString("dataCompra").getString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formatter.parse(dataCompra);
        } catch (ParseException ex) {
            throw new ServletException(ex);
        }
        double totalCompra = regitroPedido.getJsonNumber("totalCompra").doubleValue();
        String pagamento = regitroPedido.getJsonString("pagamento").getString();
        String concluida = regitroPedido.getJsonString("concluida").getString();
        double frete = regitroPedido.getJsonNumber("frete").doubleValue();

        bean.cadastrarPedido(cpedido, ccliente, data, totalCompra, pagamento, concluida, frete);

        saida.print("{\"msg\":\"Certo\"}");
    }

}
