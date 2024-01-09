package com.comparateur.esiea.comparateurprix.Entity;

public class CityStop {


    private String shortName;
    private int cityCode;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "CityStop{" +
                "shortName='" + shortName + '\'' +
                ", cityCode=" + cityCode +
                '}';
    }

}
