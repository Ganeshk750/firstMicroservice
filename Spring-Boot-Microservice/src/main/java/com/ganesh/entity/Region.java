package com.ganesh.entity;

public enum Region {
	
	Central_Bangalore("Bangalore Central"), Southern_Bangalore("Southern Bangalore"), Northern_Bangalore("Northern Bangalore"), Varies("Varies");
    private String label;
    private Region(String label) {
        this.label = label;
    }
    public static Region findByLabel(String byLabel) {
        for(Region r: Region.values()) {
            if (r.label.equalsIgnoreCase(byLabel))
                return r;
        }
        return null;
    }

}
