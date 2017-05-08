<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import=" forms.Memurlar,forms.Sabitler,forms.Zimmet"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<!--USE[Database]
SELECT TABLE_NAME,TABLE_SCHEMA,[Column_Name],[Data_type]
FROM  INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA='dbo'  -->
<HTML>
<HEAD>

<script src="<c:url value="/resources/genel.js" />"></script>
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
<title>Zimmet Listesi 5</title>

</HEAD>

<jsp:include page="/WEB-INF/jsp/ala.jsp" />
<BODY>
	<form action="./zimmetSil" method="get">
		<table id="models" class="kutu">
			<tr>

				<th>Arama&nbsp;</th>
				<th>Id&nbsp;</th>
				<th>Şube&nbsp;</th>
				<th>Personel&nbsp;</th>
				<th>Ünvan&nbsp;</th>
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
				String sorgu = "select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani,z.kaydeden, z.memurid,z.kazaid ,z.unvan,u.isim,z.serino from zimmet z inner join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id  order by eklemezamani desc";//
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

					String vericek2 = "select *  from Sabitler where KazaID =   "
							+ " ' " + zimmetKazaid + " ' ";

					rs3 = st2.executeQuery(vericek2);

					while (rs3.next()) {
			%>


			<%
				while (rs2.next()) {
			%>
			<tr id="satirno<%=rs0.getString("id")%>">
				<td><a
					href="/demirbas/zimmetlistesi?memurid=<%=rs0.getString("memurid")%>">
						Bas </a></td>
				<td class="td"><%=rs0.getString("id")%><input type="checkbox"
					name="id" value="<%=rs0.getString("id")%>"></td>
				<td class="td"><%=rs3.getString("IlceIsmi")%></td>
				<TD class="td"><a href=""
					onclick='javascript:window.open("http://localhost:8080/demirbas/deneme?memurid=<%=rs0.getString("memurid")%>", "_blank", "scrollbars=1,resizable=1,height=950,width=1000");'
					title="Personele Ait Tüm Zimmet Listesini Görmel İçin İsmin Üzerine Tıklayın..."><%=rs2.getString("Isim")%></a></TD>
				<TD class="td"><%=rs0.getString(13)%>444</TD>
				<TD class="td"><%=rs0.getString(2)%></TD>
				<TD class="td"><%=rs0.getString(3)%></TD>
				<TD class="td"><%=rs0.getString(4)%></TD>
				<TD class="td"><%=rs0.getString(5)%></TD>
				<TD class="td"><%=rs0.getString(14)%></TD>
				<TD class="td"><%=rs0.getString("baslamatarihi")%></TD>
				<TD class="td"><%=rs0.getString(7)%></TD>
				<TD class="td"><%=rs0.getString(8)%></TD>
				<TD class="td"><%=rs0.getString(9)%></TD>
				<td><a href="/demirbas/zimmetEdit/<%=rs0.getString("id")%>">Edit</a>
				</td>
				<td><input type="image"
					src="<c:url value="/resources/resim/Delete-32.png" />" width="21px"
					onclick="zimmetSil(<%=rs0.getString("id")%>)"
					title="Silmek İçin Tıklayınız.." /></td>
				<td><a href=""
					onclick='javascript:window.open("http://localhost:8080/demirbas/resultsetarama?id=<%=rs0.getString("id")%>", "_blank", "scrollbars=1,resizable=1,height=800,width=950,"titlebar=0");'>Yazdır</a></td>
				<td><a
					href="/demirbas/personelarama?memurid=<%=rs0.getString("memurid")%>">Arama</a>
			</TR>
			<%
				}
					}
				}
			%>
		</table>

		<input type="submit" value="İşaretli Olanları Sil...">
	</form>





</BODY>


</HTML>







