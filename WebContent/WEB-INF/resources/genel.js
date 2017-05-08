var jq = jQuery.noConflict();
function altTipleriGetir(ustTipId) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : ustTipId
		},
		success : function(gelen) {
			// modelleriListele(gelen);
			var select = jq('#slctAltTip');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			// alert(textStatus+"hata burdamı <br>___"+xhr.responseText);
		}

	});
}

function ikisibirada(id) {
	altTipleriGetir(id);
	modelGetir(id);
}

function ikisibiradamarkalar(id) {
	if (id == 0) {
		ikisibirada(jq("#slctTipler").val());
	} else {
		markaGetir(id);
		modelGetir(id);
	}
}

function modelGetir(altTipId) {
	// alert(altTipId);
	if (altTipId == 0 && jq("#slctTipler").val() > 0) {
		ikisibiradamarkalar(jq("#slctAltTip").val())
	} else {
		jq
				.ajax({
					type : "POST",
					url : "./modelgetir",
					dataType : "JSON",
					contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
					data : {
						altTipId : altTipId,
						durum : jq('#durum').val()
					},
					success : function(gelen) {
						modelleriListele(gelen)
					},
					error : function(xhr, textStatus, errorThrown) {
						// alert(textStatus+"hata burdamı
						// <br>___"+xhr.responseText);
					}
				});
	}
}

function modelleriListele(gelen) {
	// alert("modellerilistele");
	var trHTML = '<tr><th>Sil</th><th>edit</th><th>id</th><th>isim</th><th>ekleme tarihi</th><th>Durum</th><th>Kaydeden</th></tr>';
	jq
			.each(
					gelen,
					function(i, item) {

						var date = moment(); // Get the current date
						date.format("YYYY-MM-DD"); // 2014-07-10

						trHTML += '<tr id="satirno'
								+ item.id
								+ '"><td><input type="image" src="resources/resim/Delete-32.png" width="25px" onclick=tipsil('
								+ item.id
								+ ') title="Silmek İçin Tıklayın" />'
								+

								'</td><td>'
								+ '<a href="/demirbas/edit/'
								+ item.id
								+ '"><input type="image" src="resources/resim/duzenle.png" width="25px" title="Değiştirmek İçin Tıklayın" /></a>'
								+ '</td><td>' + item.id + '</td><td>'
								+ item.isim + '</td><td>' + item.ekleme
								+ '</td><td>' + item.durum + '</td><td>'
								+ item.kaydeden + '</td></tr>';
					});

	jq('#models').html(trHTML);
}

function tipsil(id) {
	if (confirm("Sileyim mi " + id)) {
		// jq('#satirno'+id).remove();
		jq.ajax({
			type : "POST",
			url : "./tipsil",
			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			success : function(gelen) { // vtden silmesi için ne yaptın
				// alert("buraya kadar hata olacaK mı?");
				jq('#satirno' + id).remove();
			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
}
function demirbassil(id) {//hem veritabanından hem de görüntülenen sayfadan silmek için gereklidir.
	if (confirm("Sileyim mi " + id))
		;{
		 jq('#satirno'+id).remove();//jsp sayfasından siler veritabanından silmez
		jq.ajax({
			type : "POST",
			url : "./demirbassil",//veritabanından siler jspden silmez
			
			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			success : function(gelen) { // vtden silmesi için ne yaptın
				// alert("buraya kadar hata olacaK mı?");
				jq('#satirno' + id).remove();
			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
}

function markaGetir(altTipId) {
	jq.ajax({
		type : "POST",
		url : "./markagetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			altTipId : altTipId
		},
		success : function(gelen) {
			var select = jq('#slctMarka');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {
				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
		}
	});
}

function tipsekle(id) {

	var isim = jq("#isim");
	var katid = jq("#slctMarka");
	// alert("isim : "+isim.val()+", Kategori id : "+katid.val());
	jq.ajax({
		type : "POST",
		url : "./tipsekle",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			isim : isim.val(),
			katid : katid.val()
		},
		success : function(gelen) {
			modelleriListele(gelen)
		},
		// alert("buraya kadar hata olacaK mı?");

		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "<br>___" + xhr.responseText);
		}

	});
}

function modelGetir1(durum) {
	var altTipId = jq('#slctMarka')
	jq.ajax({
		type : "POST",
		url : "./modelgetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			altTipId : altTipId.val(),
			durum : durum
		},
		success : function(gelen) {
			modelleriListele(gelen)
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "hata burdamı <br>___" + xhr.responseText);
		}
	});
}

function altTipleriListele(katid) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {
			// modelleriListele(gelen);
			var select = jq('#slctalttipid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "altTipleriListele hata burdamı <br>___"
					+ xhr.responseText);
		}

	});
}

function markalariListele(katid) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {
			// modelleriListele(gelen);
			var select = jq('#slctmarkaid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Bilinmiyor", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "<br>___" + xhr.responseText);
		}

	});
}

function modelleriListele1(katid) {
	jq.ajax({
		type : "POST",
		url : "./alttiplerigetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {

			// modelleriListele(gelen);
			var select = jq('#slctmodelid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Bilinmiyor", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "hata burdamı__***<br>___" + xhr.responseText);
		}

	});
	function demirbasnoEkle(id) {

		var dbno = jq("#demirbasno");

		alert("isim : " + demirbasno.val());
		jq.ajax({
			type : "POST",
			url : "./demirbasonay",
			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				demirbasno : demirbasno.val()
			},
			success : function(gelen) {
			},
			// alert("buraya kadar hata olacaK mı?");

			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus + "<br>___" + xhr.responseText);
			}

		});
	}
}
function sayfadakiKayitlariGetir(sayfa) { // kayıt arama için yazıldı
	jq('#sayfaNo').val(sayfa);
	jq('#' + jq('#formId').val()).submit();

}

function personelListele(katid) {
	jq.ajax({
		type : "POST",
		url : "./personelgetir",
		dataType : "JSON",
		contentType : "application/x-www-form-urlencoded;charset=iso-8859-9",
		data : {
			katid : katid
		},
		success : function(gelen) {
			alert("++++++++++++++")// modelleriListele(gelen);
			var select = jq('#slctmemurid');
			if (select.prop) {
				var options = select.prop('options');
			} else {
				var options = select.attr('options');
			}
			jq('option', select).remove();
			options[options.length] = new Option("Seçiniz", 0);
			jq.each(gelen, function(id, adi) {

				options[options.length] = new Option(adi, id);
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			alert(textStatus + "altTipleriListele hata burdamı <br>___"
					+ xhr.responseText);
		}

	});
}

function zimmetSil(id){//hem veritabanından hem de görüntülenen sayfadan silmek için gereklidir.
	if (confirm("Sileyim mi  " + id))
		;
	alert("buraya kadar hata olacaK mı?");{
		 jq('#satirno'+id).remove();//jsp sayfasından siler veritabanından silmez
		jq.ajax({
			type : "GET",
			url : "./zimmetSil",//veritabanından siler jspden silmez
			
			dataType : "JSON",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				id : id
			},
			success : function(gelen) { // vtden silmesi için ne yaptın
				alert("buraya kadar hata olacaK mı?");
				jq('#satirno' + id).remove();
			},
			error : function(xhr, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}
}






function checkAll(ele) {
	var checkboxes = document.getElementsByTagName('input');
	if (ele.checked) {
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].type == 'checkbox') {
				checkboxes[i].checked = true;
			}
		}
	} else {
		for (var i = 0; i < checkboxes.length; i++) {
			console.log(i)
			if (checkboxes[i].type == 'checkbox') {
				checkboxes[i].checked = false;
			}
		}
	}
}

//$(function() {
//
//	$("#datepicker")
//			.datepicker(
//					{
//
//						dateFormat : "dd/mm/yy",// tarih formatı yy=yıl mm=ay
//						// dd=gün
//
//						appendText : "",// inputun sonuna bu yazıyı yazar.
//
//						autoSize : true,// inputu otomatik boyutlandırır
//
//						changeMonth : true,// ayı elle seçmeyi aktif eder
//
//						changeYear : true,// yılı elee seçime izin verir
//
//						dayNames : [ "pazar", "pazartesi", "salı", "çarşamba",
//								"perşembe", "cuma", "cumartesi" ],// günlerin
//						// adı
//
//						dayNamesMin : [ "pa", "pzt", "sa", "çar", "per", "cum",
//								"cmt" ],// kısaltmalar
//
//						defaultDate : +5,// takvim açılınca seçili olanı bu
//						// günden 10 gün sonra olsun dedik
//
//						// isRTL : true,// takvimi ters çevirir garip bi özellik
//						// :D */
//
//						maxDate : "", // "+2y+1m +2w",//ileri göre bilme
//						// zamanını 2 yıl 1 ay 2 hafta yaptık
//
//						minDate : "",// "-1y-1m -2w",//geriyi göre bilme
//						// alanını 1 yıl 1 ay 2 hafta
//						// yaptık.bunu istediğiniz gibi ayarlaya
//						// bilirsiniz
//
//						monthNamesShort : [ "Ocak", "Şubat", "Mart", "Nisan",
//								"Mayıs", "Haziran", "Temmuz", "Ağustos",
//								"Eylül", "Ekim", "Kasım", "Aralık" ],// ay
//						// seçim
//						// alanın
//						// düzenledik
//
//						nextText : "ileri",// ileri butonun türkçeleştirdik
//
//						prevText : "geri",// buda geri butonu için
//
//						showAnim : "blind",// takvim açılım animasyonu alta tüm
//						// animasyon isimleri yazdım
//
//						/* fold-blind-bounce-clip-drop-explode-fade-highlight-puff-pulsate-scale-shake-slide-size-transfer */
//
//						showOn : "both",// inputun yanına ... butonu koyuyor
//
//					});
//
//});
//
//$(function() {
//
//	$("#datepicker2")
//			.datepicker(
//					{
//
//						dateFormat : "dd/mm/yy",// tarih formatı yy=yıl mm=ay
//						// dd=gün
//
//						appendText : "",// inputun sonuna bu yazıyı yazar.
//
//						autoSize : true,// inputu otomatik boyutlandırır
//
//						changeMonth : true,// ayı elle seçmeyi aktif eder
//
//						changeYear : true,// yılı elee seçime izin verir
//
//						dayNames : [ "pazar", "pazartesi", "salı", "çarşamba",
//								"perşembe", "cuma", "cumartesi" ],// günlerin
//						// adı
//
//						dayNamesMin : [ "pa", "pzt", "sa", "çar", "per", "cum",
//								"cmt" ],// kısaltmalar
//
//						defaultDate : +5,// takvim açılınca seçili olanı bu
//						// günden 10 gün sonra olsun dedik
//
//						/* isRTL: true//takvimi ters çevirir garip bi özellik :D */
//
//						maxDate : "", // "+2y+1m +2w",//ileri göre bilme
//						// zamanını 2 yıl 1 ay 2 hafta yaptık
//
//						minDate : "",// "-1y-1m -2w",//geriyi göre bilme
//						// alanını 1 yıl 1 ay 2 hafta
//						// yaptık.bunu istediğiniz gibi ayarlaya
//						// bilirsiniz
//
//						monthNamesShort : [ "Ocak", "Şubat", "Mart", "Nisan",
//								"Mayıs", "Haziran", "Temmuz", "Ağustos",
//								"Eylül", "Ekim", "Kasım", "Aralık" ],// ay
//						// seçim
//						// alanın
//						// düzenledik
//
//						nextText : "ileri",// ileri butonun türkçeleştirdik
//
//						prevText : "geri",// buda geri butonu için
//
//						showAnim : "blind",// takvim açılım animasyonu alta tüm
//						// animasyon isimleri yazdım
//
//						/* fold-blind-bounce-clip-drop-explode-fade-highlight-puff-pulsate-scale-shake-slide-size-transfer */
//
//						showOn : "both",// inputun yanına ... butonu koyuyor
//
//					});
//
//});

var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) };
	  return function(table, name) {
		  
	    if (!table.nodeType) {

	        table = document.getElementById(table)}
	    
	    var ctx = {worksheet: name || '', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
	})()
	
//POP-UP Script for zimmetyazdır	<div id="box1"/>
	window.addEventListener('mouseup', function(event) {
		var box = document.getElementById('box1');
		if (event.target != box && event.target.parentNode != box) {
			box.style.display = 'none';
		}
	});

////PRINT DIV CONTAINER <div id="box2"/>
	function printDiv(box2) {
        //Get the HTML of div
        var divElements = document.getElementById(box2).innerHTML;
        //Get the HTML of whole page
        var oldPage = document.body.innerHTML;

        //Reset the page's HTML with div's HTML only
        document.body.innerHTML = 
          "<html><head><title></title></head><body>" + 
          divElements + "</body>";

        //Print Page
        window.print();

        //Restore orignal HTML
        document.body.innerHTML = oldPage;      
    }
	
//PRINT DIV CONTAINER <div id="dvContainer"/>
	 $("#btnPrint").live("click", function () {
         var divContents = $("#dvContainer").html();
         var printWindow = window.open('', '', 'height=400,width=800');
         printWindow.document.write('<html><head><title>DIV Contents</title>');
         printWindow.document.write('</head><body >');
         printWindow.document.write(divContents);
         printWindow.document.write('</body></html>');
         printWindow.document.close();
         printWindow.print();
     });
