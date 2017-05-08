<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import=" forms.Memurlar,forms.Sabitler,forms.Zimmet"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import="java.sql.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Demirbaş Uygulaması</title>
<link href="<c:url value="/resources/resim/simge.ico" />"
	rel="shortcut icon" type="image/vnd.microsoft.icon" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value="/resources/jquery-1.9.1.js" />"></script>
<script src="<c:url value="/resources/genel.js" />"></script>
<script src="<c:url value="/resources/tarih.js" />"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script src="js/ajax.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/ala.jsp" />

	<c:if test="${error == '1'}">
		<script>
			alert("LÜTFEN GİRDİĞİNİZ VERİLERİ KONTROL EDİNİZ!" + "\n\n"
					+ "Bunlardan birini hatalı girmiş olabilirsizin:" + "\n"
					+ "1.Başlama tarihi yanlış seçili.." + "\n"
					+ "2.Bitiş tarihi yanlış seçili.." + "\n"
					+ "3.Personel yada Şube seçili değil..." + "\n\n\n");
		</script>
	</c:if>
	<c:if test="${error == '2'}">
		<script>
			alert("ZİMMET BAŞLAMA TARİHİ BUGÜNDEN İLERİ OLAMAZ!!!");
		</script>
	</c:if>



	<%-- <c:if test="${!empty m}"> --%>
	<form action="./zimmetonay">
		<table border="1" width="50%">
			<tr>
				<th width="15px">ID</th>
				<th>Alttip</th>
				<th>Marka</th>
				<th>Model</th>
				<th>Adet</th>
				<th>Düzenle</th>

			</tr>
			<c:forEach items="${m}" var="m">
				<tr>
					<td><input type="text" value="${m.id}" width="5px"/></td>
					<td><input type="text" value="${m.alttip.isim}" /></td>
					<td><input type="text" value=" ${m.marka.isim}" /></td>
					<td><input type="text" value=" ${m.model.isim}" /></td>
					<td><input type="text" value="${m.aded}" name="aded"/></td>
					<td><a href="./zimmetonay">Onay</a></td>
				</tr>
			</c:forEach>
			<c:forEach items="${zimmetListesi1}" var="z">
				<tr>
					<td>${z.id}</td>
					<td>${z.alttip.isim}</td>
					<td>${z.marka.isim}</td>
					<td>${z.model.isim}</td>
					<td>${z.aded}</td>
					<td><a href="/demirbas/zimmetEdit?id=${z.id}">Düzenle</a></td>
			</c:forEach>
		</table>

		<table width="40%">
			<tr>
				<td align="right"><input type="submit" value="Formu Temizle"></td>
			</tr>
		</table>
	</form>
</body>
</html>




