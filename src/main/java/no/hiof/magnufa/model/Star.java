package no.hiof.magnufa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Star extends CelestialBody {
    private double effectiveTemperature;

    public static final double KG_IN_MSUN = 1.98892E30;
    public static final double KM_IN_RSUN = 695700;


    public Star(String name, double mass, double radius, double effectiveTemperature) {
        super(name, mass, radius);
        this.effectiveTemperature = effectiveTemperature;
    }

    public Star(String name, double mass, double radius, double effectiveTemperature, String pictureUrl) {
        super(name, mass, radius, pictureUrl);
        this.effectiveTemperature = effectiveTemperature;
    }

    public Star() {
    }

    @JsonIgnore
    public double getMassInMsun() {
        return super.getMass() / KG_IN_MSUN;
    }
    @JsonIgnore
    public double getReadiusInRsun() {
        return getRadius() / KM_IN_RSUN;
    }

       public double getEffectiveTemperature() {
        return effectiveTemperature;
    }

    public void setEffectiveTemperature(double effectiveTemperature) {
        this.effectiveTemperature = effectiveTemperature;
    }

    @Override
    public String toString() {
        return String.format(getName(), getRadius(), getMass(), effectiveTemperature);
    }
}
