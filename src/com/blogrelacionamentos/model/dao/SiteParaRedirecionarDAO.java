package com.blogrelacionamentos.model.dao;

import com.blogrelacionamentos.model.entidade.SiteParaRedirecionar;
import com.tecurti.model.persistencia.dao.gae.DAOGenericoGAE;
import com.tecurti.model.persistencia.dao.gae.QueryGae;
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
    
    
    
}
