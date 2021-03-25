package com.rakuten.common;

public enum PackageSize {

	SIZE20("20", 1),
	SIZE40("40", 2),
	SIZE60("60", 3),
	SIZE80("80", 4),
	SIZE100("100", 5),
	SIZE120("120", 6),
	SIZE140("140", 7),
	SIZE160("160", 8),
	SIZE180("180", 9),
	SIZE200("200", 10);
	
	private String size;
    private int index;
    
    PackageSize(String size, int index) {
        this.size = size;  
        this.index = index;  
    }
    
    public static String getSize(int index) {  
        for (PackageSize c : PackageSize.values()) {  
            if (c.getIndex() == index) {  
                return c.size;  
            }  
        }  
        return null;  
    }
    
    public String getSize() {  
        return size;  
    }  
    public void setSize(String size) {  
        this.size = size;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }
    
    public static PackageSize getPackageSizeBySize(String size) {
        for (PackageSize packageSize : values()) {
            if (packageSize.getSize().equals(size)) {
                return packageSize;
            }
        }
        return null;
    }
}
