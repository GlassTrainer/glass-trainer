package com.glasstrainer.utils;

import com.glasstrainer.entity.Gps;
import com.glasstrainer.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhat CAN on 21.03.2015.
 */
public class GpsDataParser {
    
    public static List<Gps> parse(User user, BufferedReader br) {
        List<Gps> gpsList = new ArrayList<Gps>();

        double latitude;
        double longitude;

        List<String> ggpaList = new ArrayList<String>();

        try {
            String temp = br.readLine();

            while (temp != null) {
                if (temp.contains("GPGGA")) {
                    ggpaList.add(temp);
                }
                temp = br.readLine();
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        String separator = "[,]";
        // int i=1;
        for (String ggpa : ggpaList) {
            try {
                String[] tokens = ggpa.split(separator);

                //03 + (02.78469/60) = 3.046412

                if ((tokens[4].length()) > 1 && (tokens[2].length() > 1)) {
                    longitude = Integer.parseInt(tokens[4].substring(0, 3)) + Double.parseDouble(tokens[4].substring(3)) / 60;
                    latitude = Integer.parseInt(tokens[2].substring(0, 2)) + Double.parseDouble(tokens[2].substring(2)) / 60;
                    // actual data set
                    // TODO: Training shoulnot be null
                    gpsList.add(new Gps( null, latitude, longitude));
                }

            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }catch (StringIndexOutOfBoundsException e) {
                //e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                //e.printStackTrace();
            }
        }

        return gpsList;
    }

}
