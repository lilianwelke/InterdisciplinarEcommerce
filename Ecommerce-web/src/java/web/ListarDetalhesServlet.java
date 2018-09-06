package web;

import beans.ProdutoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabrielli Vianna
 */
public class ListarDetalhesServlet extends HttpServlet {

    @EJB
    private ProdutoBeanRemote bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("aplicarion/json");
        PrintWriter saida = resp.getWriter();

        int cproduto = parseInt(req.getParameter("cproduto"));

        String retornoDetalhes = "";
        try {

            ObjectMapper mapper = new ObjectMapper();
            retornoDetalhes = mapper.writeValueAsString(bean.getProdutoById(cproduto));

        } catch (Exception ex) {
            Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retornoDetalhes);
    }

}
