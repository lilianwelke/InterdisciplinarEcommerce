/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.ProdutoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
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

        String retornoProduto = "", retornoCategoria = "", retornoMarca = "", retornoConsulta = "", produto = "";
        try {

            ObjectMapper mapper = new ObjectMapper();

            retornoProduto = mapper.writeValueAsString(bean.getProduto());
            retornoCategoria = mapper.writeValueAsString(bean.getCategoria());
            retornoMarca = mapper.writeValueAsString(bean.getMarca());

            produto = req.getParameter("produto");
            retornoConsulta = mapper.writeValueAsString(bean.getConsulta(produto));

        } catch (Exception ex) {
            Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida.write(retornoProduto + "#GL#" + retornoCategoria + "#GL#" + retornoMarca + "#GL#" + retornoConsulta);
    }
}
