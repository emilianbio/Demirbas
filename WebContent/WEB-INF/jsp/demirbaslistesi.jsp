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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value="/resources/jquery-1.9.1.js" />"></script>
<script src="<c:url value="/resources/genel.js" />"></script>
<script src="<c:url value="/resources/tarih.js" />"></script>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"> </script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
</head>
${memurid}
<body>
	<jsp:include page="/WEB-INF/jsp/ala.jsp" />

	<c:if test="${error == '1'}">
		<script>
 
alert("LÜTFEN GİRDİĞİNİZ VERİLERİ KONTROL EDİNİZ!"
		);
</script>

	</c:if>
	<input type="hidden" name="sayfano" id="sayfano" value="${sayfaNo}" />
	<!--kayıt arama için yazıldı  -->
	<form:form method="post" action="demirbasonay" commandName="demirbas">
		<form:hidden path="demirbasID" />
		<table>
			<tr>

				<td>Tipi</td>
				<td>Alt Tipi</td>
				<td>Markası</td>
				<td>Modeli</td>
			</tr>
			<tr>
				<td><form:select path="tip.id" id="slcttipid"
						onchange="altTipleriListele(this.value)">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${tipListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
				<td><form:select path="alttip.id" id="slctalttipid"
						onchange="markalariListele(this.value)">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${altTipListesi}" itemValue="id"
							itemLabel="isim" />

					</form:select></td>
				<td><form:select path="marka.id" id="slctmarkaid"
						onchange="modelleriListele1(this.value)">
						<form:option value="0" id="0">Seçiniz</form:option>
						<form:options items="${markaListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
				<td><form:select path="model.id" id="slctmodelid">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${modelListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
			</tr>
			<tr>
				<td>DemirBaş No</td>
				<td>Seri No</td>
				<td>Aded</td>
				<td>Tutar TL</td>
				<td>İzahat</td>
				<td>Durum</td>
			</tr>
			<tr>
				<td><form:input id="dmrbsno" path="demirbasno"></form:input></td>
				<td><form:input id="serino" path="serino" maxlength="16"
						size="20" title="16 karakter giriniz"></form:input><label
					title="16  karakter giriniz"></label></td>
				<td><form:input path="aded"></form:input></td>
				<td><form:input path="tutar"></form:input></td>
				<td><form:input path="izahat"></form:input></td>
				<td><form:select path="durum" id="durum">
						<form:option value="1">Aktif</form:option>
						<form:option value="0">Pasif</form:option>
					</form:select></td>
			</tr>
		</table>
		<input type="submit" id="btn1" value="${tusYazisi}" />

		<c:if test="${tusYazisi == 'Güncelle'}">
			<!--   <input type="button" value="Güncelle" onclick="javascript:location.href='./demirbasGuncelle/{id}'" /> -->
			<input type="button" value="Vazgeç"
				onclick="javascript:location.href='./demirbasVazgec'" />
		</c:if>
	</form:form>
	${sayfalar}
	<br />
	<br />
	<table>
		<tr>
			<th>Sil</th>
			<th>Edit</th>
			<th>id</th>
			<th>Demirbaş No</th>
			<th>Seri No</th>
			<th>Tip</th>
			<th>Alt Tip</th>
			<th>Marka</th>
			<th>Model</th>
			<th>Aded</th>
			<th>Tutar</th>
			<th>İzahat</th>
			<th>Ekleme Zamanı</th>
			<th>Durum</th>
			<th>Kaydeden</th>
		</tr>

		<c:forEach items="${demirbasListesi1}" var="demirbasi" varStatus="no">
			<tr id="satirno${demirbasi.demirbasID}">
				
				
				
				<td><input type="image"
					src="<c:url value="/resources/resim/Delete-32.png" />" width="21px"
					onclick="demirbassil(${demirbasi.demirbasID})"
					title="Silmek İçin Tıklayınız.." /></td>
					
					
					
					
					
				<td><a href="/demirbas/demirbasEdit/${demirbasi.demirbasID}">Edit</a></td>
				<td class="td">${demirbasi.demirbasID}</td>
				<td class="td">${demirbasi.demirbasno}</td>
				<td class="td">${demirbasi.serino}</td>
				<td class="td">${demirbasi.tip.isim}</td>
				<td class="td">${demirbasi.alttip.isim}</td>
				<td class="td">${demirbasi.marka.isim}</td>
				<td class="td">${demirbasi.model.isim}</td>

				<c:if test="${ demirbasi.aded!=0}">
					<td class="td">${demirbasi.aded}</td>
				</c:if>
				<c:if test="${demirbasi.aded==0 or demirbasi.aded==null}">
					<td class="td" bgcolor="red">Ambarda Yeterli Demirbas Yok!</td>
				</c:if>
				<td class="td"><fmt:setLocale value="tr_TR" /> <fmt:formatNumber
						value="${demirbasi.tutar}" type="currency" /></td>
				<td class="td">${demirbasi.izahat}</td>
				<td class="td"><fmt:formatDate
						value="${demirbasi.eklemezamani}" pattern="dd.MM.yyyy HH:mm:ss" />
				</td>
				<td class="td"><c:if test="${demirbasi.durum}">Aktif</c:if> <c:if
						test="${!demirbasi.durum}">Pasif</c:if></td>
				<td class="td">${demirbasi.kaydeden}</td>
				<c:if test="${demirbasi.aded !=0}">
					<td><a
						href="/demirbas/zimmetİcinDemirbasGetir/${demirbasi.demirbasID}">Zimmetle</a></td>
				</c:if>

				<c:if test="${demirbasi.aded == 0}">
					<td bgcolor="red">Zimmet Yapılamaz...</td>
				</c:if>


			</tr>
		</c:forEach>

	</table>
	<br /> ${sayfalar}
	<br />



</body>

</html>