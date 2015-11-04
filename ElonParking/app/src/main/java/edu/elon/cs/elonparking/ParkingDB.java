package edu.elon.cs.elonparking;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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

            for (String key: lots.keySet()) {
                System.out.println(lots.get(key).toString());
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

    }

    public String findClosestLot() {
        return "";
    }


}
