<%@page
	import="org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import=" java.net.InetAddress"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ALA</title>
<link href="<c:url value="/resources/resim/simge.ico" />"
	rel="shortcut icon" type="image/vnd.microsoft.icon" />
<script src="<c:url value="/resources/jquery-1.9.1.js" />"></script>
<script src="<c:url value="/resources/genel.js" />"></script>
<script src="<c:url value="/resources/tarih.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
</head>
<body>





	Kullanıcı :
	<b> ${cookie.isim.value.replace("+", " ").replace("%C4%B1", "ı").replace("%C3%BC", "ü").replace("%C3%B6","ö").replace("%C3%A7", "ç").replace("%C4%9F", "ğ").replace("%C5%9F", "ş").replace("%C3%87", "Ç").replace("%C3%96", "Ö").replace("%C3%9C", "Ü").replace("%C5%9E", "Ş").replace("%C4%9E", "Ğ").replace("%C4%B0", "İ")}
	</b>
	
	<br> ID :
	<b> ${cookie.memurid.value} </b>
	<br> UNVAN :
	<b>${cookie.unvan.value.replace("+", " ").replace("%C4%B1", "ı").replace("%C3%BC", "ü").replace("%C3%B6","ö").replace("%C3%A7", "ç").replace("%C4%9F", "ğ").replace("%C5%9F", "ş").replace("%C3%87", "Ç").replace("%C3%96", "Ö").replace("%C3%9C", "Ü").replace("%C5%9E", "Ş").replace("%C4%9E", "Ğ").replace("%C4%B0", "İ")}</b>
	<br> IP:
	<b> <%
			out.write(InetAddress.getLocalHost().getHostAddress());
		%>
	</b>
	<br />
	<a href="./sabitler"><input type="image"
		src="<c:url value="/resources/resim/duzenle.png" />" width="21px"
		title="Sabitler Sahifesine Git" /></a>
	<a href="./demirbaslistesi"><input type="image"
		src="<c:url value="/resources/resim/duzenle.png" />" width="21px"
		title="Demirbaş Listesine Git" /></a>

	<a href="./zimmetlistesi5"><input type="image"
		src="<c:url value="/resources/resim/duzenle.png" />" width="21px"
		title="Zimmet Listesine Git" /></a>

	<a href="./zimmetlistesi3"><input type="image"
		src="<c:url value="/resources/resim/duzenle.png" />" width="21px"
		title="Zimmet Listesine Git" /></a>
	<a href="./cikis"><input type="image"
		src="<c:url value="/resources/resim/Stop.png" />" width="21px"
		title="Çıkmak İçin Tıklayınız!" /></a>
	<br>



</body>
</html>