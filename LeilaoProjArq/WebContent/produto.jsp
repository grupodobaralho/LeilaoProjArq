<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<input type="submit" value="HOME" onclick="window.location='http://localhost:8080/LeilaoProjArq/';" /> 
    <br>
	<h1>Cadastro de Produto</h1>
	<form action="ProdutoController" method="GET">
		<table>
			<tr>
				<td>Id do Produto:</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>Nome do Produto:</td>
				<td><input type="text" name="nome"></td>
			</tr>
			<tr>
				<td>Descri��o:</td>
				<td><input type="text" name="descricao"></td>
			</tr>
			<tr>
				<td>Valor Inicial:</td>
				<td><input type="text" name="valorInicial"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Cadastrar"></td>
			</tr>
		</table>

	</form>
</body>
</html>