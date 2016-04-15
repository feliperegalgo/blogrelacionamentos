package com.blogrelacionamentos.view;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogrelacionamentos.model.dao.SiteParaRedirecionarDAO;
import com.blogrelacionamentos.model.entidade.SiteParaRedirecionar;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
    SiteParaRedirecionarDAO dao = new SiteParaRedirecionarDAO();
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	AdminServletForm form = new AdminServletForm();
	form.idSelecionado = request.getParameter("idSelecionado");
	form.tipo = request.getParameter("tipo");
	form.url = request.getParameter("url");
	form.descricao = request.getParameter("descricao");
	form.ativado = request.getParameter("ativado");
	
	String acao = request.getParameter("acao");
	if ("iniciarEditar".equals(acao)) {
	    SiteParaRedirecionar site = dao.findById(form.getIdSelecionadoLong());
	    request.setAttribute("siteParaEditar", site);
	} else if ("remover".equals(acao)) {
	    dao.delete(form.getIdSelecionadoLong());
	    request.setAttribute("msg", "Anuncio Removido");
	} else if ("confirmarEditar".equals(acao)) {
	    
	    SiteParaRedirecionar site = dao.findById(form.getIdSelecionadoLong());
	    site.setTipo(form.tipo);
	    site.setUrlParaRedirecionar(form.url);
	    site.setDescricao(form.descricao);
	    site.setAtivado("on".equals(form.ativado));
	    site.setDataAlteracao(new GregorianCalendar());
	    dao.update(site);
	    
	    request.setAttribute("msg", "Anuncio Alterado");
	    
	    request.setAttribute("siteParaEditar", site);
	    
	    
	} else if ("confirmarNovo".equals(acao)) {

	    SiteParaRedirecionar siteComMesmoTipo = dao.findByTipo(form.tipo);
	    if (siteComMesmoTipo != null) {
		request.setAttribute("msg", "Ja existe tipo '"+form.tipo+"' cadastrado");
	    } else {
		SiteParaRedirecionar siteParaRedirecionar = new SiteParaRedirecionar();
		siteParaRedirecionar.setTipo(form.tipo);
		siteParaRedirecionar.setUrlParaRedirecionar(form.url);
		siteParaRedirecionar.setDescricao(form.descricao);
		siteParaRedirecionar.setAtivado("on".equals(form.ativado));
		siteParaRedirecionar.setDataAlteracao(new GregorianCalendar());
		dao.insert(siteParaRedirecionar);
		
		request.setAttribute("msg", "Novo criado");
	    }
	} else if ("limpar".equals(acao)) {
	    // nao faz nada
	}
	
	// ----------------
	request.setAttribute("sites", dao.findAll());
	
	RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath()+"/WEB-INF/admin/admin.jsp");
	dispatcher.forward(request, response);
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
