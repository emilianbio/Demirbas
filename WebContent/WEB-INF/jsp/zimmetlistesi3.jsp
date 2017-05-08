<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Zimmet Arama</title>
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

	${memurlar}
	<jsp:include page="/WEB-INF/jsp/ala.jsp" />
	<form action="./personelarama" method="get">
		<table>
			<tr>
				<td>Personele Göre Ara:</td>
				<td><select id="memurid" name="memurid">
						<option value="">------</option>
						<%-- <c:forEach var="pl" items="${personellistesi}"> --%>


						<%
							Class.forName("org.postgresql.Driver");
							Connection conn = DriverManager.getConnection(
									"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
									"1234");
							String sorgu = "select  distinct z.memurid from zimmet z order by memurid asc";
							Statement statement = conn.createStatement();
							ResultSet rs0 = statement.executeQuery(sorgu);
							// ResultSet rs2 = statement.executeQuery(sorgu);
							int zimmetMemurid;
							int zimmetKazaid;

							while (rs0.next()) {
								zimmetMemurid = rs0.getInt("memurid");

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
								String vericek = "select *  from Memurlar where  MemurID =   "
										+ " ' " + zimmetMemurid + " ' "
										+ "order by MemurID asc";

								rs2 = st.executeQuery(vericek);

								while (rs2.next()) {
						%><option value="<%=rs2.getString("MemurID")%>">
							<%=rs2.getString("Isim")%><br />
							<%
								}}
							%>
						</option>
						<%-- </c:forEach> --%>
				</select></td>
				<td><input type="submit" value="OK" tabindex="2" /></td>
			</tr>
		</table>
	</form>
	<form action="./demirbasnoarama" method="get">
		<table>
			<tr>
				<td>Demirbaş Numarasına Göre Ara:</td>
				<td><select id="demirbasno" name="demirbasno"
					style="min-width: 123px;">
						<option value="">------</option>
						<c:forEach var="dn" items="${demirbasnolistesi}">
							<option value="${dn.demirbasno}">${dn.demirbasno}</option>
						</c:forEach>
				</select></td>
				<td><input type="submit" value="OK" tabindex="2" /></td>
			</tr>
		</table>
	</form>
	<c:if test="${!empty m}">
		<form action="zimmetlistesi3">
			<table border="1" width="90%">
				<tr>
					<th width="15px">ID</th>
					<th>Birim</th>
					<th width="150px">Personel</th>
					<th>Demirbas No</th>
					<th>Seri No</th>
					<th>Adet</th>
					<th>Başlama Tarihi</th>
					<th>Bitiş Tarihi</th>
					<th>Kaydeden</th>
					<th width="250px">Ekleme Zamani</th>
				</tr>
				<c:forEach items="${m}" var="m">
					<tr>
						<td width="20px">${m.id}</td>
						<td>${m.kazaid}</td>
						<td width="150px">${m.memurid} </td>
						<td>${m.demirbasno}</td>
						<td>${m.serino}</td>
						<td><input value="${m.aded}"/></td>
						<td>${m.baslamatarihi}</td>
						<td>${m.bitistarihi}</td>
						<td>${m.memurs.isim}</td>
						<td width="250px">${m.eklemezamani}</td>
				</c:forEach>
			</table>
			<br />
			<table width="90%">
				<tr>
					<td align="right"><input type="submit" value="Formu Temizle"></td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>




