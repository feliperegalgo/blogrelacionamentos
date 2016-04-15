package com.blogrelacionamentos.view;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogrelacionamentos.model.dao.SiteParaRedirecionarDAO;
import com.blogrelacionamentos.model.entidade.SiteParaRedirecionar;

@SuppressWarnings("serial")
public class RedirecionarServlet extends HttpServlet {
    SiteParaRedirecionarDAO dao = new SiteParaRedirecionarDAO();
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String tipo = request.getParameter("tipo");
	if ("criar".equals(tipo)) {
	    SiteParaRedirecionar siteParaRedirecionar = new SiteParaRedirecionar();
	    siteParaRedirecionar.setTipo("1");
	    siteParaRedirecionar.setUrlParaRedirecionar("http://www.uol.com.br");
	    siteParaRedirecionar.setDescricao("descricao");
	    siteParaRedirecionar.setAtivado(true);
	    siteParaRedirecionar.setDataAlteracao(new GregorianCalendar());
	    dao.insert(siteParaRedirecionar);
	}
	
	SiteParaRedirecionar siteParaRedirecionar = dao.findByTipo(tipo);
	if (siteParaRedirecionar != null) {
	    response.sendRedirect(siteParaRedirecionar.getUrlParaRedirecionar());
	} else {
	    response.sendRedirect(request.getContextPath() + "/index.html");
	}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
	    execute(req, resp);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
	    execute(req, resp);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
}
