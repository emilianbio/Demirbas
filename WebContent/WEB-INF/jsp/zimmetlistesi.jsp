<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import=" forms.Memurlar,forms.Sabitler,forms.Zimmet"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import="java.sql.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-type"
	content="application/vnd.ms-excel; charset=UTF-8">

<title>Demirbaş Uygulaması</title>
<link href="<c:url value="/resources/resim/simge.ico" />"
	rel="shortcut icon" type="image/vnd.microsoft.icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/jquery-1.9.1.js" />"></script>
<script src="<c:url value="/resources/genel.js" />"></script>
<script src="<c:url value="/resources/tarih.js" />"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
</head>
<jsp:include page="/WEB-INF/jsp/ala.jsp" />
<c:if test="${error == '1'}">
	<script>
		alert("ZİMMET BAŞLAMA TARİHİ BUGÜNDEN İLERİ OLAMAZ!!!");
	</script>
</c:if>

<body>

	<a href="/demirbas/zimmetlistesi?memurid=2"> Bas </a>

	<c:forEach items="${zimmet}" var="z"> 
${z.memurID}
${z.isim}
${z.TCNo}
${z.memurID}
${z.memurID}
${z.memurID}

</c:forEach>



	<table id=testTable class="kutu" width="%90">
		<tr>


			<th>Id&nbsp;</th>
			<th>Şube&nbsp;</th>
			<th>Personel&nbsp;</th>
			<th>Personel ID&nbsp;</th>
			<th>Ünvanı&nbsp;</th>
			<th>Alttip&nbsp;</th>
			<th>Marka&nbsp;</th>
			<th>Model&nbsp;</th>
			<th>Demirbaş No&nbsp;</th>
			<th>Zimmet<br />Başlama<br />Tarihi&nbsp;
			</th>
			<th>Zimmet<br />Bitiş<br />Tarihi&nbsp;
			</th>
			<th>Ekleme<br />Zamanı&nbsp;
			</th>
			<th>Kaydeden&nbsp;</th>
		</tr>
	</table>
</body>
</html>

