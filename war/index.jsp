<% 
	RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/redirecionar");
	dispatcher.forward(request, response);
%>