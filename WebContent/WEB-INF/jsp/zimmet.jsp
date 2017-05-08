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

	<input type="hidden" name="sayfano" id="sayfano" value="${sayfaNo}" />
	<!--kayıt arama için yazıldı  -->
	<form:form action="./zimmetonay" method="post" commandName="demirbas">

		<table class="kutu">
			<tr>
				<td>ALTTİP</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input
						style="border-style:hidden;" size="8" name="alttip"
						path="alttip.isim" readonly="true" /></td>
						
						
						
						<td>Demirbas ID</td>
				<td><form:input
						style="border-style:hidden;" size="8" name="id"
						path="demirbasID" readonly="true" /></td>

			</tr>
			<tr>
				<td>MARKA</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input
						style="border-style:hidden;" name="marka" path="marka.isim"
						readonly="true" /></td>
			</tr>
			<tr>
				<td>MODEL</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input
						style="border-style:hidden;" type="text" name="model"
						path="model.isim" readonly="true" /></td>
			</tr>
			<tr>
				<td>DEMİRBAS<br> NO
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input type="text"
						name="demirbasno" path="demirbasno" readonly="true" /></td>
			</tr>
			<tr>
				<td>SERİ<br> NO
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input type="text"
						name="serino" path="serino" maxlength="16" size="20"
						title="16 karakter giriniz...." /> <label>16 karakter giriniz</label>
				</td>
				<td>ADED</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input type="text"
						name="aded" path="aded" maxlength="16" size="20" />
				</td>
			</tr>
		</table>
		<table class="kutu">
			<tr>
				<td>BAŞLAMA<br> TARİHİ
				</td>
				<td><form:input class="datepicker3" name="bastar"
						path="baslamatarihi" value="${baslamatarihi}" id="datepicker"
						type="text" data-role="date" data-inline="true" /></td>
			</tr>
			<tr>
				<td>BİTİŞ<br> TARİHİ
				</td>
				<td><form:input class="date" name="bittar" path="bitistarihi"
						id="datepicker2" type="text" data-role="date" data-inline="true"
						value="" /></td>
			</tr>
			<tr>
				<td>ŞUBE</td>
				<td><form:select path="kazaid" name="userSelect"
						id="userSelect">
						<form:option value="0">Seçiniz</form:option>
						<form:options items="${sabitlerListesi}" itemValue="KazaID"
							itemLabel="ilceIsmi" />
					</form:select></td>
			</tr>
			<tr>
				<td>PERSONEL</td>
				<td><form:select path="memurid" id="userSelect">
						<form:option value="0">Seçiniz</form:option>
						<c:forEach items="${personelListesi}" var="p">
							<form:option value="${p.memurID}">${p.isim}</form:option>
						</c:forEach>
					</form:select></td>
				<td>ÜNVAN</td>
				<td><form:select path="unvan" id="userSelect">
						<form:option value="0">Seçiniz</form:option>
						<%-- 	<c:forEach  items="${unvanListesi}"   var= "u"></c:forEach> --%>
						<form:options items="${unvanListesi}" itemValue="id"
							itemLabel="isim" />
					</form:select></td>
			</tr>
		</table>
		<input type="submit" name="submit" value="Zimmetle" />
		<!--onclick="return confirm ('Zimmetlemek istediğinize emin misiniz?')"  -->
		<input type="button" value="Demirbaş Listesine Dön"
			onclick="javascript:location.href='./zimmetVazgec'" />
	</form:form>
	<table id="models" class="kutu">
		<tr>
			<th>Id&nbsp;</th>
			<th>Şube&nbsp;</th>
			<th>Personel&nbsp;</th>
			<th>Ünvanı&nbsp;</th>
			<th>Alttip&nbsp;</th>
			<th>Marka&nbsp;</th>
			<th>Model&nbsp;</th>
			<th>Demirbaş No&nbsp;</th>
			<th>Seri No&nbsp;</th>
			<th>Zimmet<br />Başlama<br />Tarihi&nbsp;
			</th>
			<th>Zimmet<br />Bitiş<br />Tarihi&nbsp;
			</th>
			<th>Ekleme<br />Zamanı&nbsp;
			</th>
			<th>Kaydeden&nbsp;</th>


		</tr>


		<%
			ArrayList<Zimmet> zimmetListesi = new ArrayList<Zimmet>();

			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
					"1234");
			String sorgu = "select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid, u.isim,z.serino from zimmet z inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id full join unvanlar u on z.unvan = u.id order by eklemezamani desc";
			Statement statement = conn.createStatement();
			ResultSet rs0 = statement.executeQuery(sorgu);
			// ResultSet rs2 = statement.executeQuery(sorgu);
			int zimmetMemurid;
			int zimmetKazaid;
			zimmetListesi.clear();
			while (rs0.next()) {
				zimmetMemurid = rs0.getInt("memurid");
				zimmetKazaid = rs0.getInt("kazaid");

				// veri tabani islemleri
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//String url = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
				String url = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
				String kullaniciad = "sa";
				//String sifre = "saricam%tarim+2010";
				String sifre = "1234";
				Connection con = null;
				Statement st = null;
				ResultSet rs2 = null;
				con = DriverManager.getConnection(url, kullaniciad, sifre);
				st = con.createStatement();

				// verileri siraladigimiz bölüm
				//		 String vericek =
				//		 "select MemurID,Isim,TCNo,kaza_id,Vazifesi from Memurlar order by Isim ASC";
				String vericek = "select *  from Memurlar where MemurID =   "
						+ " ' " + zimmetMemurid + " ' " + "order by Isim ASC";

				rs2 = st.executeQuery(vericek);

				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//String url2 = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL";
				String url2 = "jdbc:sqlserver://localhost:1433;databaseName=java_db";
				String kullaniciad2 = "sa";
				//String sifre2 = "saricam%tarim+2010";
				String sifre2 = "1234";
				Connection con2 = null;
				Statement st2 = null;
				ResultSet rs3 = null;
				con2 = DriverManager.getConnection(url2, kullaniciad2, sifre2);
				st2 = con2.createStatement();

				// verileri siraladigimiz bölüm
				//		 String vericek =
				//		 "select MemurID,Isim,TCNo,kaza_id,Vazifesi from Memurlar order by Isim ASC";
				String vericek2 = "select *  from Sabitler where KazaID =   "
						+ " ' " + zimmetKazaid + " ' ";

				rs3 = st2.executeQuery(vericek2);

				while (rs3.next()) {
		%>


		<%
			while (rs2.next()) {
		%>
		<tr id="satirno<%=rs0.getString("id")%>">

			<td class="td"><%=rs0.getString("id")%></td>
			<td class="td"><%=rs3.getString("IlceIsmi")%></td>
			<TD class="td"><%=rs2.getString("Isim")%></TD>
			<TD class="td"><%=rs0.getString(12)%></TD>
			<TD class="td"><%=rs0.getString(2)%></TD>
			<TD class="td"><%=rs0.getString(3)%></TD>
			<TD class="td"><%=rs0.getString(4)%></TD>
			<TD class="td"><%=rs0.getString(5)%></TD>
			<TD class="td"><%=rs0.getString(13)%></TD>
			<TD class="td"><%=rs0.getString(6)%></TD>
			<TD class="td"><%=rs0.getString(7)%></TD>
			<TD class="td"><%=rs0.getString(8)%></TD>
			<TD class="td"><%=rs0.getString(9)%></TD>
			<td><a href="/demirbas/update/<%=rs0.getString("id")%>">Edit</a>
			</td>
			<td><input type="image"
				src="<c:url value="/resources/resim/Delete-32.png" />" width="21px"
				onclick="zimmetSil(<%=rs0.getString("id")%>)"
				title="Silmek İçin Tıklayınız.." /></td>
			<td><a href="/demirbas/zimmetSil?id=<%=rs0.getString("id")%>">Sil</a></td>

		</TR>
		<%
			}
				}
			}
		%>
	</table>




	<br />
</body>
</html>




