package com.comparateur.esiea.comparateurprix.Entity;
import java.util.ArrayList;
import java.util.List;

public class CityTrajet {


    private List<CityStop> cityStopDepart;
    private List<CityStop> cityStopArrive;

    public List<CityStop> getCityStopDepart() {
        return cityStopDepart;
    }


    public List<CityStop> getCityStopArrive() {
        return cityStopArrive;
    }



    public CityTrajet() {
        this.cityStopDepart = new ArrayList<>();
        this.cityStopArrive = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CityTrajet{" +
                "cityStopDepart=" + cityStopDepart +
                ", cityStopArrive=" + cityStopArrive +
                '}';
    }
}
