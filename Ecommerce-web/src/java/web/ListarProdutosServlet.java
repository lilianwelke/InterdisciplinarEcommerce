/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.ProdutoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class ListarProdutosServlet extends HttpServlet {

    @EJB
    private ProdutoBeanRemote bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("aplicarion/json");
        PrintWriter saida = resp.getWriter();

        // Mudei isso
        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(req.getInputStream(), "UTF-8"));
        // AT

        String retornoProduto = "", retornoCategoria = "", retornoMarca = "", retornoConsulta = "", retornoConsultaCategoria = "",
                retornoConsultaMarca = "", produto = "";
        int ccategoria = 0, cmarca = 0;
        try {

            ObjectMapper mapper = new ObjectMapper();

            retornoProduto = mapper.writeValueAsString(bean.getProduto());
            retornoCategoria = mapper.writeValueAsString(bean.getCategoria());
            retornoMarca = mapper.writeValueAsString(bean.getMarca());

            produto = req.getParameter("produto");
            retornoConsulta = mapper.writeValueAsString(bean.getConsulta(produto));

            ccategoria = parseInt(req.getParameter("ccategoria"));
            retornoConsultaCategoria = mapper.writeValueAsString(bean.getCategoriaById(ccategoria));

            cmarca = parseInt(req.getParameter("cmarca"));
            retornoConsultaMarca = mapper.writeValueAsString(bean.getMarcaById(ccategoria, cmarca));

        } catch (Exception ex) {
            Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida.write(retornoProduto + "#GL#" + retornoCategoria + "#GL#" + retornoMarca + "#GL#" + retornoConsulta + "#GL#"
                + retornoConsultaCategoria + "#GL#" + retornoConsultaMarca);
    }
}
