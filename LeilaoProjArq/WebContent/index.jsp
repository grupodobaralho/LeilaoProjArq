<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Leilão</title>

<script> 
    $(function(){
      $("#includedContent").load("produto.jsp"); 
    });
</script> 

</head>
<body>
	<h2>Menu Leilão</h2>
	
	
	<form action="http://localhost:8080/LeilaoProjArq/produto.jsp">
		<input type="submit" value="Cadastrar Produto">
	</form>
	
	<br>
	
	<form action="ListProdutoController" method="GET">
		<input type="submit" value="Listar Produtos">
	</form>
	


</body>
</html>