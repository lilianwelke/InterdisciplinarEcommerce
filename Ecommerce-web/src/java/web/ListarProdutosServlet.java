/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import beans.ProdutoBeanRemote;
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

        String retorno = null;
        try {
            retorno = bean.getProduto().toString();
//bean.getMarca().toString() + bean.getProduto().toString();
        } catch (Exception ex) {
            Logger.getLogger(RegistrarMarcaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida.write(retorno);
    }
}
