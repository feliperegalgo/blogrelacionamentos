package com.blogrelacionamentos.model.dao;

import java.util.List;

import com.blogrelacionamentos.model.entidade.SiteParaRedirecionar;
import com.google.appengine.api.datastore.Key;
import com.tecurti.model.persistencia.dao.gae.DAOGenericoGAE;
import com.tecurti.model.persistencia.dao.gae.QueryGae;
import com.tecurti.model.persistencia.dao.gae.utils.UtilsGaeDAO;
import com.tecurti.model.utils.ModelUtils;

public class SiteParaRedirecionarDAO extends DAOGenericoGAE<SiteParaRedirecionar, SiteParaRedirecionar.Attr> {

    public SiteParaRedirecionar findByTipo(String tipo) throws Exception {
	if (ModelUtils.isEmptyTrim(tipo)) {
	    return null;
	}
	
	QueryGae queryGae = new QueryGae(SiteParaRedirecionar.class, null);
	queryGae.addFilter(attr.tipo, tipo);
	return findBy(queryGae);
    }

    @Override
    public List<SiteParaRedirecionar> findAll() throws Exception {
	QueryGae queryGae = new QueryGae(SiteParaRedirecionar.class, null);
	queryGae.addSortDesc(attr.dataAlteracao);
	return findListBy(queryGae);
    }

    public SiteParaRedirecionar findById(long id) throws Exception {
	return findById(SiteParaRedirecionar.createKey(id));
    }

    public void delete(Long id) throws Exception {
	Key key = SiteParaRedirecionar.createKey(id);
	delete(key);
	UtilsGaeDAO.waitForRemovingOf(key);
    }
    
    
}
