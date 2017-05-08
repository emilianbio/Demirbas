<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/ala.jsp" />

	<form:form method="post" action="sabitonay" commandName="tips">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><label> tip</label></td>
				<td><form:select path="tip.tip.tip.id"
						onChange="ikisibirada(this.value)" id="slctTipler">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${tipListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="id">Alt tip</form:label></td>
				<td><form:select path="tip.tip.id" id="slctAltTip"
						onChange="ikisibiradamarkalar(this.value)">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${altTipListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
			</tr>
			<tr>
				<td><label>Marka</label></td>
				<td><form:select path="tip.id" id="slctMarka"
						onChange="modelGetir(this.value)">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${markaListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
			</tr>
			<tr>
				<td><label>Durum</label></td>
				<td><form:select path="durum" id="durum"
						onChange="modelGetir1(this.value)">
						<form:option value="1">Aktif</form:option>
						<form:option value="0">Pasif</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="isim">Model</form:label></td>
				<td><form:input path="isim"></form:input></td>
			</tr>
			<tr>
				<td colspan=2><input type="submit" value="${tusYazisi}" /></td>
				<td><c:if test="${tusYazisi == 'Güncelle'}">&nbsp;
				<input type="button" value="Vazgeç"
							onclick="javascript:location.href='./vazgec'" />
					</c:if></td>
			</tr>
		</table>
	</form:form>

	<table id="models" class="kutu">
		<tr>
			<th>Sil</th>
			<th>edit</th>
			<th>id</th>
			<th>isim</th>
			<th>ekleme tarihi</th>
			<th>durum</th>
			<th>kaydeden</th>
		</tr>

		<c:forEach items="${modelListesi}" var="sabitTips" varStatus="no">
			<tr id="satirno${sabitTips.id}">
				<td><input type="image"
					src="<c:url value="/resources/resim/Delete-32.png" />" width="21px"
					onclick="tipsil(${sabitTips.id})" title="Silmek İçin Tıklayın" /></td>
				<td><a href="/demirbas/edit/${sabitTips.id}"><input
						type="image" src="<c:url value="/resources/resim/duzenle.png" />"
						width="21px" title="Değiştirmek İçin Tıklayın" /></a></td>
				<td class="td">${sabitTips.id}</td>
				<td class="td">${sabitTips.isim}</td>
				<td class="td"><fmt:formatDate
						value="${sabitTips.eklemezamani}" pattern="dd.MM.yyyy HH:mm:ss" />
				</td>
				<td class="td">${sabitTips.durum}</td>
				<td class="td">${sabitTips.memurs.isim}</td>
			</tr>
		</c:forEach>
	</table>


</body>

</html>