package no.hiof.magnufa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Star.class, name = "star")
})

public class CelestialBody implements Comparable<CelestialBody>  {
    private String name, pictureUrl;
    private double mass, radius;

    public CelestialBody(String name, double mass, double radius) {
        this(name, mass, radius, "");
    }

    public CelestialBody(String name, double mass, double radius, String pictureUrl) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.pictureUrl = pictureUrl;
    }

    public CelestialBody() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    @JsonIgnore
    @Override
    public String toString() {
        return String.format(name, mass, radius);
    }

    @Override
    public int compareTo(CelestialBody otherCelestialBody) {
        return this.name.compareTo(otherCelestialBody.getName());
    }
}
