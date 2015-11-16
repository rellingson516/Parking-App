package edu.elon.cs.elonparking;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by rellingson on 11/3/2015.
 */
public  class ParkingDB {
    private HashMap<String, GPSLocation> buildings;
    private HashMap<String, GPSLocation> lots;
    private HashMap<String, String[]> passes;

    Context context;

    public ParkingDB(Context context) {
        this.context = context;

        buildings = new HashMap<>();
        lots = new HashMap<>();
        passes = new HashMap<>();

        fillBuildings();
        fillLots();
        fillPasses();
    }

    private void fillBuildings() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("buildings.txt"), "UTF-8"));

            String line = reader.readLine();
            while(line != null) {
                int i = line.indexOf(',');
                String building = line.substring(0, i);
                int j = line.indexOf(',', i + 1);
                double lat = Double.parseDouble(line.substring(i + 1, j));
                double longi = Double.parseDouble(line.substring(j + 1));
                buildings.put(building, new GPSLocation(lat, longi));
                line = reader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void fillLots() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("lots.txt"), "UTF-8"));

            String line = reader.readLine();
            while(line != null) {
                int i = line.indexOf(',');
                String lot = line.substring(0, i);
                int j = line.indexOf(',', i + 1);
                double lat = Double.parseDouble(line.substring(i + 1, j));
                double longi = Double.parseDouble(line.substring(j + 1));
                lots.put(lot, new GPSLocation(lat, longi));
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void fillPasses() {
        passes.put("CS", new String[] {"Colonnades Lot", "Danieley Center Lot", "Softball Lot", "Health Center Lot"});
        passes.put("EE", new String[] {"Colonnades Lot", "Danieley Center Lot"});
        passes.put("EW", new String[] {});
        passes.put("GF", new String[] {"Oaks Lot", "Colonnades Lot"});
        passes.put("GN", new String[] {"Softball Lot", "Global Lot"});
        passes.put("GR", new String[] {"McMichael Lot", "Colonnades Lot", "Oaks Lot"});
        passes.put("HN", new String[] {"East Gym Lot"});
        passes.put("KD", new String[] {"Colonnades Lot", "Danieley Center Lot"});
        passes.put("LG", new String[] {"McMichael Lot"});
        passes.put("LS", new String[] {"McMichael Lot"});
        passes.put("MP", new String[] {"Mill Point Lot"});
        passes.put("OK", new String[] {"Oaks Lot"});
        passes.put("Visitor", new String[] {"Oaks Lot", "McMichael Lot", "Admissions Lot"});

    }

    public String findClosestLot(String building, String pass) {
        Calendar calendar = Calendar.getInstance();
        GPSLocation loc1 = buildings.get(building);
        double lat1 = loc1.getLatitude();
        double long1 = loc1.getLongitude();
        String closest = "";
        double shortest = Double.MAX_VALUE;
        if(pass.equals("Faculty/Staff") || calendar.get(Calendar.HOUR_OF_DAY) >= 18 ||
                (calendar.get(Calendar.HOUR_OF_DAY) <= 7 && calendar.get(Calendar.MINUTE) <= 30) ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            for(String key : lots.keySet()) {
                GPSLocation loc2 = lots.get(key);
                double lat2 = loc2.getLatitude();
                double long2 = loc2.getLongitude();

                double dist = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(long2 - long1, 2));
                if(dist < shortest) {
                    shortest = dist;
                    closest = key;
                }
            }
        } else {
            String[] availableLots = passes.get(pass);
            for(int i = 0; i < availableLots.length; i++) {
                GPSLocation loc2 = lots.get(availableLots[i]);
                double lat2 = loc2.getLatitude();
                double long2 = loc2.getLongitude();

                double dist = Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(long2 - long1, 2));
                if(dist < shortest) {
                    shortest = dist;
                    closest = availableLots[i];
                }


            }

        }



        return closest;
    }

    public GPSLocation getLotLocation(String name) {
        return lots.get(name);
    }


}
