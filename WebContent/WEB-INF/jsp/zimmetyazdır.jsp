<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head marginheight="0">


<meta charset="UTF-8">
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
<style type="text/css">
div#box2 {
	top: 50%;
	left: 50%;
	margin: auto;
}
</style>
</head>
<body>

	<script type="text/javascript">
		function printDiv(box2) {
			//Get the HTML of div
			var divElements = document.getElementById(box2).innerHTML;
			//Get the HTML of whole page
			var oldPage = document.body.innerHTML;

			//Reset the page's HTML with div's HTML only
			document.body.innerHTML = "<html><head><title></title></head><body>"
					+ divElements + "</body>";

			//Print Page
			window.print();

			//Restore orignal HTML
			document.body.innerHTML = oldPage;
		}
	</script>
	<c:if test="${error == '1'}">
		<script>
 
alert("ZİMMET BAŞLAMA TARİHİ BUGÜNDEN İLERİ OLAMAZ!!!"
		);

</script>
	</c:if>

	<div id="box2">
		<!--       
		style='z-index: 300000; display: none; padding: 5px; align: center; 
		border-radius: 5px; text-align: center; position: fixed; top: 50%; box-sizing: border-box; 
		left: 50%; width: auto; height: auto; background-color: white; border: 2px solid #2DAAE4; 
		-webkit-transform: translate(-50%, -50%); opacity: 0.9; filter: alpha(opacity = 90); font-size: 20px;' -->

		<table width=800 border=0 cellspacing=0 cellpadding=0>
			<tr>
				<td align=center height=100></td>
			</tr>
			<tr>
				<td colspan="5"
					style="border: 0px solid #000000; font-weight: bold; text-align: center;">
					T.C.<br /> GIDA, TARIM VE HAYVANCILIK BAKALNLIĞI<br /> ADANA İL
					MÜDÜRLÜĞÜ<br /> DEMİRBAŞ SENETİ<br /> (Demirbaş, Makine ve Cihaz
					İçin)
				</td>
			</tr>


			<c:forEach items="${zimmetyapılanpersonel}" var="z" begin="0" end="0">
				<tr id="satirno${z.id}">
				<tr>


					<td align=left colspan=2>DEMİRBAŞ NO:${z.demirbasno}</td>
					<td align=right colspan=3 style="font-weight: bold;">TARİH:
						${islemtarihi} <%-- ${z.baslamatarihi} --%>
					</td>

				</tr>
				<tr>


					<td colspan=2
						style="border: 1px solid #000000; font-weight:; text-align: left;">NEREYE
						VERİLDİĞİ</td>
					<td colspan=3
						style="border: 1px solid #000000; font-weight: bold; text-align: left;">${z.kazaid}</td>

				</tr>
				<tr>
					<td colspan=5 align=center
						style="border: 1px solid #000000; font-weight:; text-align: center; width: 80px;">DEMİRBAŞ,
						MAKİNE VE CİHAZIN</td>
				</tr>

				<tr>


					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 80px;">Sıra
						No</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;">Özellikleri</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;">Adı</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;">Fiyatı</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 100px;">Seri
						no</td>

				</tr>

			</c:forEach>




			<c:forEach items="${zimmetyapılanpersonel}" var="z">


				<tr>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 80px;">${rowIndex}</td>

					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;">${z.marka.isim}&nbsp;${z.model.isim}</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;">${z.alttip.isim}</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;">
						<%-- ${z.tutar} --%>
					</td>
					<td
						style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 100px;">${z.demirbasno}</td>

				</tr>
			</c:forEach>


			<c:forEach items="${zimmetyapılanpersonel}" var="z" begin="0" end="0">
				<tr>
					<td style="border: 1px solid #000000; height: 150px;" valign=top
						colspan=5>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan=2>Yukarıda adı, özellikeri, nereye veya kime
									verildiği yazılı olan demirbaş, makine ve cihaz teslim
									edilmiştir.Kullancı kendi kusuru sonucuda doğacak zararları
									tanzim etmeyi kabul eder.</td>
							</tr>
							<tr>
								<td colspan=2 height=50></td>
							</tr>

							<tr>
								<td align=center height=30>Teslim Eden</td>
								<td align=center height=30>Teslim Alan</td>

							</tr>
							<tr>
								<td align=center height=30><b>Adı
										Soyadı:${z.memurs.isim}</b></td>
								<td align=center height=30><b>Adı Soyadı:${z.memurid}</b></td>
							</tr>
							<tr>
								<td align=center height=30><b>Unvanı:${ünvan}</b></td>
								<td align=center height=30><b>Unvanı:${ünvan2}</b></td>
							</tr>
							<tr></tr>
							<tr>
								<td align=center height=30><b>İmzası:.........</b></td>
								<td align=center height=30><b>İmzası:.........</b></td>
							</tr>
							<tr>
								<td align=center height=100><b></b></td>
							</tr>
						</table>

					</td>
				</tr>
			</c:forEach>

		</table>

		<font size=2 color='#000000'><strong>T.M.Y Örnek
				No:6/A</strong></font>
	</div>
	<table width=800 border=0 cellspacing=0 cellpadding=0>
		<tr>
			<td align="right"><input type="image"
				src="<c:url value="/resources/resim/printer-icon.png" />"
				width="100px" onclick="javascript:printDiv('box2')">
				..YAZDIR</td>
		</tr>

	</table>
	<tr>
		<td><input type="reset" onclick="/demirbas/zimmetlistesi" /></td>
	</tr>
</body>
</html>


