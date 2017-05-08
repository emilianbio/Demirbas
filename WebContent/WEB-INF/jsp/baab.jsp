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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/genel.css" />" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<title>GİRİŞ</title>
</head>

<body>

	<div id="bg"
		style="display: none; background-color: #b0c4de; cursor: default; opacity: 0.8; filter: progid:DXImageTransform.Microsoft.Alpha(opacity=10); position: fixed; z-index: 15000; left: 0; top: 0; width: 100%; height: 100%; border: none; zoom: 1;"></div>
	<div id='uyari' class="uyari"
		style='z-index: 300000; display: none; padding: 5px; align: center; border-radius: 5px; text-align: center; position: fixed; top: 50%; box-sizing: border-box; left: 50%; width: auto; height: auto; background-color: white; border: 2px solid #2DAAE4; -webkit-transform: translate(-50%, -50%); opacity: 0.9; filter: alpha(opacity = 90); font-size: 20px;'>

		Lütfen Kullanıcı Bilgilerini Giriniz!!! <br> <br> <input
			type='button' value='Kapat' class="btn btn-success" onclick='Kapat()'>

	</div>
	<form:form method="post" action="./kullanicionay"
		commandName="kullanici" class="form-inline" role="form">
		<table bgcolor="#7892c2" align=center border=0 cellspacing=2
			cellpadding=2>
			<tr>
				<td bgcolor="#7892c2"><span class="label-primary">Kullanıcı
						İsmi </span></td>
				<td>:</td>
				<td><form:input path="isim" class="form-control" type="text"
						tabindex="1"></form:input></td>
			</tr>
			<tr>
				<td><span>Şifre </span></td>
				<td>:</td>
				<td><form:input path="sifre" type="password" tabindex="2"></form:input></td>
			</tr>



			<tr>
				<td colspan=3 align=center><input type="button" value="Giriş"
					id="btngir" name="btngir" onclick="giris()" class="myButton"
					tabindex="3" /></td>
			</tr>
		</table>


		<script language=javascript>
			function giris(texstring) {

				if (kullanici.isim.value == "") {
					document.all("uyari").style.display = "inline";
					document.all("bg").style.display = "inline";

				} else {
					kullanici.submit();
				}
			}

			function Kapat() {

				document.all("uyari").style.display = "none";
				document.all("bg").style.display = "none";
			}
		</script>


	</form:form>






</body>
</html>