<%@page import="com.tecurti.model.utils.ModelUtils"%>
<%@page import="com.blogrelacionamentos.model.entidade.SiteParaRedirecionar"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>

<%
List<SiteParaRedirecionar> sites = (List<SiteParaRedirecionar>) request.getAttribute("sites");
SiteParaRedirecionar siteParaEditar = (SiteParaRedirecionar) request.getAttribute("siteParaEditar");
String msg = (String) request.getAttribute("msg");
%>

<body>
<form name="formAdmin" action="<%= request.getContextPath() %>/admin">
<input type="hidden" name="acao">
<input type="hidden" name="idSelecionado">

<% if (ModelUtils.isNotEmptyTrim(msg)) { %>
	<div style="font-size: 24px; margin-bottom: 10px; text-align: center; background-color: #EEE; padding: 8px;">
		<%= msg %>
	</div>
<% } %>

<div style="margin-bottom: 15px;">
	<b>Exemplo:</b> https://blogrelacionamentos.appspot.com?tipo=VALOR
</div>

<table border="0">
	<tr>
		<td>
			Tipo
		</td>
		<td>
			<input name="tipo" type="text" style="width: 99%;" value="<%=siteParaEditar!=null?siteParaEditar.getTipo():""%>">
		</td>
	</tr>
	<tr>
		<td>
			Url
		</td>
		<td>
			<input name="url" type="text" style="width: 99%;" value="<%=siteParaEditar!=null?siteParaEditar.getUrlParaRedirecionar():""%>">
		</td>
	</tr>
	<tr>
		<td>
			Ativado
		</td>
		<td>
			<input type="checkbox" name="ativado" <%=siteParaEditar!=null&&siteParaEditar.isAtivado()?"checked=\"checked\"":""%>>
		</td>
	</tr>
	<tr>
		<td>
			Descricao
		</td>
		<td>
			<textarea name="descricao" style="width: 300px; height: 70px;"><%=siteParaEditar!=null?siteParaEditar.getDescricao():""%></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center" style="padding-top: 4px;">
			<input type="button" value="Limpar" onclick="limpar()">
			<% if (siteParaEditar != null) { %>
					<input type="button" value="Editar" onclick="confirmarEditar('<%=siteParaEditar.getId()%>')">
			<% } else { %>
					<input type="button" value="Novo" onclick="confirmarNovo()">
			<% } %>
		</td>
	</tr>
</table>
<BR/>
<hr>
<BR/>
<table border="1">
	<tr>
		<td>
			Data
		</td>
		<td>
			Tipo
		</td>
		<td>
			Url
		</td>
		<td>
			Descricao
		</td>
		<td >
			
		</td>
	</tr>
	
	<% for (SiteParaRedirecionar s : sites) { %>
			<tr style="background-color: <%=s.isAtivado()?"#8bf099":"#CCC"%>;">
				<td>
					<%= ModelUtils.formatarDDMMYYYY(s.getDataAlteracao()) %>
				</td>
				<td>
					<%= s.getTipo() %>
				</td>
				<td>
					<%= s.getUrlParaRedirecionar() %>
				</td>
				<td>
					<%= s.getDescricao() %>
				</td>
				<td>
					<a href="javascript:iniciarEditar('<%=s.getId()%>')">Editar</a> - <a href="javascript:remover('<%=s.getId()%>')">Remover</a>
				</td>
			</tr>
	<% } %>
</table>
</form>
</body>

<script type="text/javascript">
	function remover(idSite) {
		if (confirm('Confirmar Remover?')) {
			with (document.formAdmin) {
				idSelecionado.value=idSite;
				acao.value='remover';
				submit();
			}
		}
	}
	function iniciarEditar(idSite) {
		with (document.formAdmin) {
			idSelecionado.value=idSite;
			acao.value='iniciarEditar';
			submit();
		}
	}
	function confirmarEditar(idSite) {
		with (document.formAdmin) {
			idSelecionado.value=idSite;
			acao.value='confirmarEditar';
			submit();
		}
	}
	function limpar() {
		with (document.formAdmin) {
			acao.value='limpar';
			submit();
		}
	}
	function confirmarNovo() {
		with (document.formAdmin) {
			acao.value='confirmarNovo';
			submit();
		}
	}
</script>

</html>





