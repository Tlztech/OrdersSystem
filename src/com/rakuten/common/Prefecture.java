package com.rakuten.common;

public enum Prefecture {

	HOKKAIDO("北海道", 1),
	AOMORI("青森県", 2),
	IWATE("岩手県",3),
	MIYAGI("宮城県",4),
	AKITA("秋田県",5),
	YAMAGATA("山形県",6),
	FUKUSHIMA("福島県",7),
	IBARAKI("茨城県",8),
	TOCHIGI("栃木県",9),
	GUNMA("群馬県",10),
	SAITAMA("埼玉県",11),
	CHIBA("千葉県",12),
	TOKYO("東京都",13),
	KANAGAWA("神奈川県",14),
	NIIGATA("新潟県",15),
	TOYAMA("富山県",16),
	ISHIKAWA("石川県",17),
	FUKUI("福井県",18),
	YAMANASHI("山梨県",19),
	NAGANO("長野県",20),
	GIFU("岐阜県",21),
	SHIZUOKA("静岡県",22),
	AICHI("愛知県",23),
	MIE("三重県",24),
	SHIGA("滋賀県",25),
	KYOTO("京都府",26),
	OSAKA("大阪府",27),
	HYOGO("兵庫県",28),
	NARA("奈良県",29),
	WAKAYAMA("和歌山県",30),
	TOTTORI("鳥取県",31),
	SHIMANE("島根県",32),
	OKAYAMA("岡山県",33),
	HIROSHIMA("広島県",34),
	YAMAGUCHI("山口県",35),
	TOKUSHIMA("徳島県",36),
	KAGAWA("香川県",37),
	EHIME("愛媛県",38),
	KOCHI("高知県",39),
	FUKUOKA("福岡県",40),
	SAGA("佐賀県",41),
	NAGASAKI("長崎県",42),
	KUMAMOTO("熊本県",43),
	OITA("大分県",44),
	MIYAZAKI("宮崎県",45),
	KAGOSHIMA("鹿児島県",46),
	OKINAWA("沖縄県",47),
	OTHER("その他",48)
	;
	
	private String name;
    private int index;
    
    Prefecture(String name, int index) {
        this.name = name;  
        this.index = index;  
    }
 
    public static String getName(int index) {  
        for (Prefecture c : Prefecture.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }
    
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }
    
    public static Prefecture getPrefectureByName(String name) {
        for (Prefecture prefecture : values()) {
            if (prefecture.getName().equals(name)) {
                return prefecture;
            }
        }
        return null;
    }
}
