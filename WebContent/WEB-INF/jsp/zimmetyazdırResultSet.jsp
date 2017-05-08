<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="forms.Zimmet"%>
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
	${zimmet}


	<%
		ArrayList<Zimmet> zimmetListesi = new ArrayList<Zimmet>();

		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/demirbasdb", "postgres",
				"1234");

		Statement statement = conn.createStatement();

		//String sorgu = "select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid ,z.unvan,u.isim from zimmet z full join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id where z.id= "+ rs0."order by eklemezamani desc ";
		String memurid = request.getParameter("memurid");
		ResultSet rs0 = statement
				.executeQuery("select  z.id,s1.isim ,s2.isim ,s3.isim,z.demirbasno,z.baslamatarihi,z.bitistarihi,z.eklemezamani, k.isim ,z.memurid,z.kazaid ,z.unvan,u.isim,z.serino from zimmet z full join unvanlar u on z.unvan=u.id inner join sabittips s1 on z.alttipid = s1.id inner join sabittips s2 on z.markaid = s2.id  inner join sabittips s3 on z.modelid = s3.id inner join kullanici k on z.kaydeden = k.id where z.memurid= "
						+ memurid + "order by eklemezamani desc ");

		// ResultSet rs2 = statement.executeQuery(sorgu);
		String zimmetMemurid = request.getParameter("memurid");
		int zimmetKazaid;
		zimmetListesi.clear();
		int siraNo = 1;
		while (rs0.next()) {
			//zimmetMemurid = rs0.getInt("memurid");
			zimmetKazaid = rs0.getInt("kazaid");
			
			String deneme= rs0.getString(3);

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
	%>









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



			<tr id="satirno<%=rs0.getString("id")%>">
			<tr>


				<td align=left colspan=2>DEMİRBAŞ NO:<%=rs0.getString(5)%></td>
				<td align=right colspan=3 style="font-weight: bold;">TARİH:
					${islemtarihi} <%-- ${z.baslamatarihi} --%>
				</td>

			</tr>


			<%
				while (rs3.next()) {
			%>
			<tr>

				<td colspan=2
					style="border: 1px solid #000000; font-weight:; text-align: left;">NEREYE
					VERİLDİĞİ</td>
				<td colspan=3
					style="border: 1px solid #000000; font-weight: bold; text-align: left;"><%=rs3.getString("IlceIsmi")%></td>
				<%
					}
				%>
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


			<tr>
				<td
					style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 80px;"><%=siraNo%></td>

				<td
					style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;"><%=deneme%>&nbsp;<%=rs0.getString(4)%></td>
				<td
					style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;"><%=rs0.getString(2)%></td>
				<td
					style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 200px;"></td>
				<td
					style="border: 1px solid #000000; font-weight: bold; text-align: center; width: 100px;"><%=rs0.getString(14)%></td>

			</tr>




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


						<%
							while (rs2.next()) {
						%>
						<tr>
							<td align=center height=30><b>Adı
									Soyadı:${cookie.isim.value.replace("%C4%B1", "ı").replace("+", " ").replace("%C3%87", "ç")}</b></td>
							<td align=center height=30><b>Adı Soyadı:<%=rs2.getString("Isim")%></b></td>
						</tr>

						<tr>
							<td align=center height=30><b>Unvanı:${cookie.unvan.value}</b></td>

							<td align=center height=30><b>Unvanı:<%=rs0.getString(13)%></b></td>

						</tr>

						<%
							}
									siraNo++;
								}
							
						%>
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


