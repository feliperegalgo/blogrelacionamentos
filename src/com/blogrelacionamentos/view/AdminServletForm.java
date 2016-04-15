package com.blogrelacionamentos.view;

public class AdminServletForm {

    public String idSelecionado;
    public String tipo;
    public String url;
    public String descricao;
    public String ativado;

    public Long getIdSelecionadoLong() {
	return Long.parseLong(idSelecionado);
    }
}
