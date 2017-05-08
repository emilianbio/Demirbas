package forms;

public enum SiraNumaralari {

    BİR("1"), İKİ("2"), ÜÇ("3"), DÖRT("4"), BEŞ("5"), ALTI("6"), YEDİ("7"), SEKİZ(
	    "8"), DOKUZ("9"), ON("10"), ONBİR("11"), ONİKİ("12"), ONÜÇ("13"), ONDÖRT(
	    "14"), ONBEŞ("15");

    private String siraNo;

    private SiraNumaralari(String siraNo) {
	this.siraNo = siraNo;
    }

    public String getSiraNo() {
	return siraNo;
    }

}
