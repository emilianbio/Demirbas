<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--USE[Database]
SELECT TABLE_NAME,TABLE_SCHEMA,[Column_Name],[Data_type]
FROM  INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA='dbo'  -->



<HTML>
<HEAD>
<TITLE>Selecting Publishers From a Database</TITLE>
</HEAD>

<BODY>
	<H1>Selecting Publishers From a Database</H1>

	<%
		String conStr = "jdbc:sqlserver://10.1.0.10:1433;databaseName=MemurSQL;user=sa;password=saricam%tarim+2010";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(conStr);

		Statement statement = conn.createStatement();
		ResultSet resultset = statement
				.executeQuery("SELECT * FROM Memurlar ");//("SELECT distinct Column_Name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME= 'vezaif'");	//
	%>

	<TABLE BORDER="1">
		<tr>

			<td>MemurID</td>
			<td>PersonelKod</td>
			<td>Kurum</td>
			<td>Isim</td>
			<td>AnaIsmi</td>
			<td>BabaIsmi</td>
			<td>DogumYeri</td>
			<td>DogumTarihi</td>
			<td>TCNo</td>
			<td>Vazifesi</td>
			<td>Emekli_Sicili</td>
			<td>K_D</td>
			<td>Kurum_Sicili</td>
			<td>Parola</td>
			<td>Sinifi</td>
			<td>EPosta</td>
			<td>Email</td>
			<td>Adres</td>
			<td>Telefon</td>
			<td>OgrenimDurumu</td>
			<td>MemuriyeteBaslama</td>
			<td>BakanliktaBaslama</td>
			<td>IlceMdrlgndeBaslama</td>
			<td>VazifeyeBaslamaTarihi</td>
			<td>IlcedenAyrilma</td>
			<td>IzinLimit</td>
			<td>IzinGirisTarihi</td>
			<td>Denetci</td>
			<td>BesbinDortYuzGun</td>
			<td>Durumu</td>
			<td>VeriGirisci</td>
			<td>VeriGirisTur</td>
			<td>SonVeriGirisGorev</td>
			<td>VeriGirisAdet</td>
			<td>VeriGirisDurum</td>
			<td>ipsiz</td>
			<td>IdariVazife</td>
			<td>Vilayet</td>
			<td>TaramIlce</td>
			<td>VazifeYeri_Kod</td>
			<td>VazifeYeriTakbis_ID</td>
			<td>Subelers</td>
			<td>KonuUzman</td>
			<td>TSHHIslemAdedi</td>
			<td>VakifIslemAdedi</td>
			<td>MaliyeIslemAdedi</td>
			<td>GSSIslemAdedi</td>
			<td>0</td>
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>4</td>
			<td>6</td>
			<td>7</td>
			<td>8</td>
			<td>9</td>
			<td>10</td>
			<td>11</td>
			<td>12</td>
			<td>13</td>
			<td>14</td>
			<td>15</td>
			<td>16</td>
			<td>17</td>
			<td>18</td>
			<td>19</td>
			<td>20</td>
			<td>21</td>
			<td>22</td>
			<td>23</td>
			<td>24</td>
			<td>25</td>
			<td>26</td>
			<td>27</td>
			<td>28</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>

			<td>kaza_id</td>
			<td>VazifeYeri</td>
			<td>_VazifeYeriIsim</td>
			<td>mac2</td>
			<td>RH</td>
			<td>Kgrub</td>
			<td>mac</td>
			<td>sendika</td>
			<td>targel</td>
			<td>vekil</td>
			<td>bankahesabno</td>
			<td>kadrounvani</td>
			<td>ekibs</td>
			<td>hizmeteeklenecek</td>

		</tr>
		<%
			while (resultset.next()) {
		%>
		<TR>

			<td><%=resultset.getString(1)%></td>
			<TD><%=resultset.getString(2)%></TD>
			<TD><%=resultset.getString(3)%></TD>
			<TD><%=resultset.getString(4)%></TD>
			<TD><%=resultset.getString(5)%></TD>
			<TD><%=resultset.getString(6)%></TD>
			<TD><%=resultset.getString(7)%></TD>
			<TD><%=resultset.getString(8)%></TD>
			<%--<TD><%=resultset.getString(9)%></TD>
			 <TD><%=resultset.getString(10)%></TD>
			<TD><%=resultset.getString(11)%></TD>
			<TD><%=resultset.getString(12)%></TD>
			<TD><%=resultset.getString(13)%></TD>
			<TD><%=resultset.getString(14)%></TD>
			<TD><%=resultset.getString(15)%></TD>
			<TD><%=resultset.getString(16)%></TD>
			<TD><%=resultset.getString(17)%></TD>
			<TD><%=resultset.getString(18)%></TD>
			<TD><%=resultset.getString(19)%></TD>
			<TD><%=resultset.getString(20)%></TD>
			<TD><%=resultset.getString(21)%></TD>
			<TD><%=resultset.getString(22)%></TD>
			<TD><%=resultset.getString(23)%></TD>
			<TD><%=resultset.getString(24)%></TD>
			<TD><%=resultset.getString(25)%></TD>
			<TD><%=resultset.getString(26)%></TD>
			<TD><%=resultset.getString(27)%></TD>
			<TD><%=resultset.getString(28)%></TD>
			<TD><%=resultset.getString(29)%></TD>
			<TD><%=resultset.getString(30)%></TD>
			<TD><%=resultset.getString(31)%></TD>
			<TD><%=resultset.getString(32)%></TD>
			<TD><%=resultset.getString(34)%></TD>
			<TD><%=resultset.getString(35)%></TD>
			<TD><%=resultset.getString(36)%></TD>
			<TD><%=resultset.getString(37)%></TD>
			<TD><%=resultset.getString(38)%></TD>
			<TD><%=resultset.getString(39)%></TD>
			<TD><%=resultset.getString(40)%></TD>
			<TD><%=resultset.getString(41)%></TD>
			<TD><%=resultset.getString(42)%></TD>
			<TD><%=resultset.getString(43)%></TD>
			<TD><%=resultset.getString(44)%></TD>
			<TD><%=resultset.getString(45)%></TD>
			<TD><%=resultset.getString(46)%></TD>
			<TD><%=resultset.getString(47)%></TD>
			<TD><%=resultset.getInt(48)%></TD>
			<TD><%=resultset.getString(49)%></TD>
			<TD><%=resultset.getString(50)%></TD>
			<TD><%=resultset.getString(51)%></TD>
			<TD><%=resultset.getString(52)%></TD>
			<TD><%=resultset.getString(53)%></TD>
			<TD><%=resultset.getString(54)%></TD>
			<TD><%=resultset.getString(55)%></TD>
			<TD><%=resultset.getString(56)%></TD>
			<TD><%=resultset.getString(57)%></TD>
			<TD><%=resultset.getString(58)%></TD>
			<TD><%=resultset.getString(59)%></TD>
			<TD><%=resultset.getString(60)%></TD>
			<TD><%=resultset.getString(61)%></TD>
			<TD><%=resultset.getString(62)%></TD>
			<TD><%=resultset.getString(63)%></TD>
			<TD><%=resultset.getString(64)%></TD>
			<TD><%=resultset.getString(65)%></TD>
			<TD><%=resultset.getString(66)%></TD>
			<TD><%=resultset.getString(67)%></TD>
			<TD><%=resultset.getString(68)%></TD>
			<TD><%=resultset.getString(69)%></TD>
			<TD><%=resultset.getString(70)%></TD>
			<TD><%=resultset.getString(71)%></TD>
			<TD><%=resultset.getString(72)%></TD>
			<TD><%=resultset.getString(73)%></TD>
			<TD><%=resultset.getString(74)%></TD>
			<TD><%=resultset.getString(75)%></TD>
			<TD><%=resultset.getString(76)%></TD>
			<TD><%=resultset.getString(77)%></TD>
			<TD><%=resultset.getString(78)%></TD>
			<TD><%=resultset.getString(79)%></TD>
			<TD><%=resultset.getString(80)%></TD>
			<TD><%=resultset.getString(81)%></TD>
			<TD><%=resultset.getString(82)%></TD>
			<TD><%=resultset.getString(83)%></TD>
			<TD><%=resultset.getString(84)%></TD>
			<TD><%=resultset.getString(85)%></TD>
			<TD><%=resultset.getString(86)%></TD>
			<TD><%=resultset.getString(87)%></TD>
			<TD><%=resultset.getString(88)%></TD>
			<TD><%=resultset.getString(89)%></TD>
			<TD><%=resultset.getString(90)%></TD>
			<TD><%=resultset.getString(91)%></TD>
			<TD><%=resultset.getString(92)%></TD>
			<TD><%=resultset.getString(93)%></TD>
			<TD><%=resultset.getString(94)%></TD>
			<TD><%=resultset.getString(95)%></TD>
			<TD><%=resultset.getString(96)%></TD>
			<TD><%=resultset.getString(97)%></TD>
			<TD><%=resultset.getString(98)%></TD>
			<TD><%=resultset.getString(99)%></TD>
			<TD><%=resultset.getString(100)%></TD>
			<TD><%=resultset.getString(101)%></TD>
			<TD><%=resultset.getString(102)%></TD>
			<TD><%=resultset.getString(103)%></TD>
			<TD><%=resultset.getString(104)%></TD>
			<TD><%=resultset.getInt(105)%></TD>
			<TD><%=resultset.getString(106)%></TD>
			<TD><%=resultset.getString(107)%></TD>
			<TD><%=resultset.getString(108)%></TD>
			<TD><%=resultset.getString(109)%></TD>
			<TD><%=resultset.getString(110)%></TD>
			<TD><%=resultset.getString(111)%></TD>
			<TD><%=resultset.getString(112)%></TD>
			<TD><%=resultset.getString(113)%></TD>
			<TD><%=resultset.getString(114)%></TD>
			<TD><%=resultset.getString(115)%></TD>
			<TD><%=resultset.getString(116)%></TD>
			<TD><%=resultset.getString(117)%></TD>
			<TD><%=resultset.getString(118)%></TD>
			<TD><%=resultset.getString(119)%></TD>
			<TD><%=resultset.getString(120)%></TD>
			<TD><%=resultset.getString(121)%></TD>
			<TD><%=resultset.getString(122)%></TD>
			<TD><%=resultset.getString(123)%></TD>
			<TD><%=resultset.getString(124)%></TD>
			<TD><%=resultset.getString(125)%></TD>
			<TD><%=resultset.getString(126)%></TD>
			<TD><%=resultset.getString(127)%></TD>
			<TD><%=resultset.getString(127)%></TD>
			<TD><%=resultset.getString(129)%></TD>
			<TD><%=resultset.getString(130)%></TD>
			<TD><%=resultset.getString(131)%></TD>
			<TD><%=resultset.getString(132)%></TD>
			<TD><%=resultset.getString(133)%></TD>
			<TD><%=resultset.getString(134)%></TD>
			<TD><%=resultset.getString(135)%></TD>
			<TD><%=resultset.getString(136)%></TD>
			<TD><%=resultset.getString(137)%></TD>
			<TD><%=resultset.getString(138)%></TD>
			<TD><%=resultset.getString(139)%></TD>
			<TD><%=resultset.getString(140)%></TD>
			<TD><%=resultset.getString(141)%></TD>
			<TD><%=resultset.getString(142)%></TD>
			<TD><%=resultset.getString(143)%></TD>
			<TD><%=resultset.getString(144)%></TD>
			<TD><%=resultset.getString(145)%></TD>
			<TD><%=resultset.getString(146)%></TD>
			<TD><%=resultset.getString(147)%></TD>
			<TD><%=resultset.getString(148)%></TD>
			<TD><%=resultset.getString(149)%></TD>
			<TD><%=resultset.getString(150)%></TD>
			<TD><%=resultset.getString(151)%></TD>
			<TD><%=resultset.getString(152)%></TD> --%>




		</TR>
		<%
			}
		%>
	</TABLE>
</BODY>
</HTML>

<form action="./personelarama" method="get">

	Aranacak Kelime: <select id="memurid" name="id">
		<option value="">------</option>
		<c:forEach var="pl" items="${personellistesi}">
			<option value="${pl.memurid}">${pl.memurid}//
				${pl.demirbasno}</option>
		</c:forEach>
	</select> <input type="submit" value="OK" tabindex="2" />
</form>
<table border="1" width="90%" cellspacing="0">
	<tr>
	<tr>

		<th>Id&nbsp;</th>
		<th>Şube&nbsp;</th>
		<th>Personel&nbsp;</th>
		<th>Alttip&nbsp;</th>
		<th>Marka&nbsp;</th>
		<th>Model&nbsp;</th>
		<th>Demirbaş No&nbsp;</th>
		<th>Zimmet<br />Başlama<br />Tarihi&nbsp;
		</th>
		<th>Zimmet<br />Bitiş<br />Tarihi&nbsp;
		</th>
		<th>Ekleme<br />Zamanı&nbsp;
		</th>
		<th>Kaydeden&nbsp;</th>
	</tr>


	<c:forEach items="${zimmetlistesi}" var="m">
		<tr>
			<td>${m.id}</td>
			<td align="center">${m.kazaid}</td>
			<td align="center"><h3>${m.memurid}</h3></td>
			<td align="center">${m.alttip.isim}</td>
			<td align="center">${m.marka.isim}</td>
			<td align="center">${m.model.isim}</td>
			<td align="center">${m.demirbasno}</td>
			<td><fmt:formatDate value="${m.baslamatarihi}"
					pattern="dd/MM/yyyy" /></td>
			<td><fmt:formatDate value="${m.bitistarihi}"
					pattern="dd/MM/yyyy" /></td>
			<td><fmt:formatDate value="${m.eklemezamani}"
					pattern="dd/MM/yyyy E HH:mm" /></td>
			<td>${m.memurs.isim}</td>


		</tr>
	</c:forEach>

</table>





