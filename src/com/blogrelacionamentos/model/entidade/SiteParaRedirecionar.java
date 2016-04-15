package com.blogrelacionamentos.model.entidade;

import java.util.Calendar;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.tecurti.model.persistencia.dao.gae.entidades.EntityGae;
import com.tecurti.model.persistencia.dao.gae.utils.UtilsGaeDAO;

public class SiteParaRedirecionar implements EntityGae {

    Long id;
    String tipo;
    String urlParaRedirecionar;
    String descricao;
    boolean ativado;
    Calendar dataAlteracao;

    public static class Attr {
	public static String KIND = UtilsGaeDAO.getKIND(SiteParaRedirecionar.class);
	
	public static String tipo = "tipo";
	public static String urlParaRedirecionar = "urlParaRedirecionar";
	public static String descricao = "descricao";
	public static String ativado = "ativado";
	public static String dataAlteracao = "dataAlteracao";
    }

    @Override
    public Key getParentKey() {
	return null;
    }
    
    @Override
    public Key getKey() {
	return UtilsGaeDAO.createKey(getParentKey(), SiteParaRedirecionar.Attr.KIND, id);
    }

    @Override
    public void setKey(Key key) {
	id = key.getId();
    }

    @Override
    public Entity toEntityGae() {
	Entity entity = UtilsGaeDAO.instanciarEntity(this);

	entity.setProperty(Attr.tipo, tipo);
	entity.setProperty(Attr.urlParaRedirecionar, urlParaRedirecionar);
	entity.setProperty(Attr.descricao, descricao);
	entity.setProperty(Attr.ativado, ativado);
	entity.setProperty(Attr.dataAlteracao, dataAlteracao.getTime());
	return entity;
    }

    @Override
    public void setClassFields(Entity entity) {
	setKey(entity.getKey());
	setTipo((String)entity.getProperty(Attr.tipo));
	setUrlParaRedirecionar((String)entity.getProperty(Attr.urlParaRedirecionar));
	setDescricao((String)entity.getProperty(Attr.descricao));
	setAtivado((Boolean)entity.getProperty(Attr.ativado));
	setDataAlteracao(UtilsGaeDAO.getCalendarProperty(entity, Attr.dataAlteracao));
    }
    
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTipo() {
	return tipo;
    }

    public void setTipo(String tipo) {
	this.tipo = tipo;
    }

    public String getUrlParaRedirecionar() {
	return urlParaRedirecionar;
    }

    public void setUrlParaRedirecionar(String urlParaRedirecionar) {
	this.urlParaRedirecionar = urlParaRedirecionar;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public boolean isAtivado() {
	return ativado;
    }

    public void setAtivado(boolean ativado) {
	this.ativado = ativado;
    }

    public Calendar getDataAlteracao() {
	return dataAlteracao;
    }

    public void setDataAlteracao(Calendar dataAlteracao) {
	this.dataAlteracao = dataAlteracao;
    }

}
