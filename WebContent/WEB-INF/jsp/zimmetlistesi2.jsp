<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Demirbaş Uygulaması</title>
<link href="<c:url value="/resources/resim/simge.ico" />"
	rel="shortcut icon" type="image/vnd.microsoft.icon" />
<script src="<c:url value="/resources/jquery-1.9.1.js" />"></script>
<script src="<c:url value="/resources/genel.js" />"></script>
<script src="<c:url value="/resources/tarih.js" />"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/ala.jsp" />
	${sayfalar}
	<br />
	<c:if test="${error == '1'}">
		<script>
			alert("LÜTFEN GİRDİĞİNİZ VERİLERİ KONTROL EDİNİZ!");
		</script>

	</c:if>
	<table class="kutu">
		<tr>
			<td>ALTTİP</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				style="border-style: hidden;" size="8" name="alttipid" /></td>

		</tr>
		<tr>
			<td>MARKA</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				style="border-style: hidden;" name="markaid" readonly="true" /></td>

		</tr>
		<tr>
			<td>MODEL</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				style="border-style: hidden;" type="text" name="modelid"
				readonly="true" /></td>
		</tr>
		<tr>
			<td>DEMİRBAS<br> NO
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
				name="demirbasno" readonly="true" /></td>
		</tr>
	</table>
	<table class="kutu">
		<tr>
			<td>BAŞLAMA<br> TARİHİ
			</td>
			<td><input class="datepicker3" name="baslamatarihi"
				value="${baslamatarihi}" id="datepicker" type="text"
				data-role="date" data-inline="true" /></td>

		</tr>

		<tr>
			<td>BİTİŞ<br> TARİHİ
			</td>
			<td><input class="date" name="bitistarihi" id="datepicker2"
				type="text" data-role="date" data-inline="true" value="" /></td>
		</tr>
		<tr>

			<td>ŞUBE</td>
			<td><select name="kazaid" id="userSelect">
					<option value="0">Seçiniz</option>

					<options items="${sabitlerListesi}" itemValue="KazaID"
						itemLabel="ilceIsmi"></options>
			</select></td>
		</tr>

		<tr>

			<td>PERSONEL</td>
			<td><select name="memurid" id="userSelect">
					<option value="0">Seçiniz</option>
					<c:forEach items="${personelListesi}" var="p">
						<option value="${p.memurID}">${p.isim}</option>
					</c:forEach>
			</select></td>

			<td>ÜNVAN</td>
			<td><select name="unvan" id="userSelect">
					<option value="0">Seçiniz</option>
					<%-- 	<c:forEach  items="${unvanListesi}"   var= "u"></c:forEach> --%>
					<options items="${unvanListesi}" itemValue="id" itemLabel="isim" />
			</select></td>
		</tr>



	</table>


	<br /> ${sayfalar}
</body>

</html>




