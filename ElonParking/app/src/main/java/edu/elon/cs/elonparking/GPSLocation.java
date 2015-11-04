package edu.elon.cs.elonparking;

/**
 * Created by rellingson on 11/3/2015.
 */
public class GPSLocation {

    double latitude, longitude;

    public GPSLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {

        return latitude;
    }

    public String toString() {
        return "lat: " + latitude + " long: " + longitude;
    }
}
