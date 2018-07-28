package com.mknieszner.basics.algorythms.simulatedAnnealing.travelingSalesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingleTour {
    private List<City> tour = new ArrayList<>();
    private double distance = 0;

    public SingleTour() {
        for (int i = 0; i < Repository.getNumberOfCities(); ++i) {
            tour.add(null);
        }
    }

    public SingleTour(List<City> tour) {
        List<City> currentTour = new ArrayList<>();

        for (int i = 0; i < tour.size(); ++i) {
            currentTour.add(null);
        }

        for (int i = 0; i < tour.size(); ++i) {
            currentTour.set(i, tour.get(i));
        }
        this.tour = currentTour;
    }

    public double getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            for (int cityIndex = 0; cityIndex < getTourSize(); ++cityIndex) {
                City from = getCity(cityIndex);
                City destination = cityIndex + 1 < getTourSize() ///check 13.11
                        ? getCity(cityIndex + 1)
                        : getCity(0);
                tourDistance += from.distanceTo(destination);
            }
            this.distance = tourDistance;
        }
        return distance;
    }

    public List<City> getTour() {
        return tour;
    }

    public void setTour(List<City> tour) {
        this.tour = tour;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    public int getTourSize() {
        return tour.size();
    }

    public void generateTour() {
        for (int cityIndex = 0; cityIndex < Repository.getNumberOfCities(); ++cityIndex) {
            setCity(cityIndex, Repository.getCity(cityIndex));
        }
        Collections.shuffle(tour);
    }

    public void setCity(int cityIndex, City city) {
        tour.set(cityIndex, city);
        distance = 0;
    }

    @Override
    public String toString() {
        return "SingleTour{" +
                "tour=" + tour +
                ", distance=" + distance +
                '}';
    }
}
