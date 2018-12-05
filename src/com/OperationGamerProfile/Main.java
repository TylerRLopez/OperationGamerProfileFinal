/*
    Author: Tyler Lopez
    Date Started: 10/10/18

    Description: To-Do
 */

package com.OperationGamerProfile;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);

        String clientId = "20cc257544584a2a9c8b8eb92d145aad";
        String clientSecret = "gynaGmDugi2lNSO8V8ORu8sapznNDWgp";

        System.out.println("Thank you for choosing Operation Gamer Profile!");
        System.out.println("Please read the manual that was provided with this tool so that you know how to use it...\nLets get started...\n");

        System.out.println("The client ID is: " + clientId);
        System.out.println("The client Secret is: " + clientSecret);

        System.out.print("Please enter your authorization token(This is CASE SENSITIVE): ");

        String token = userinput.next();

//        String token = "USQP3Rm2ExUILpQzZ8QlltzUD5fwMTn9Sb";

        //URL for wow chars
        String url = "https://us.api.blizzard.com/wow/user/characters?locale=en_US&access_token=" + token;


        try {
            //getting data (Still needs tokens)
            String result = htmlGetRequest(url);
            System.out.println("Result from blizzard: " + result);
            System.out.println();

            System.out.println("Starting JSONObjects");

            JSONObject jsonobject = new JSONObject(result);

            System.out.println("Built object");

            JSONArray jsonArray = jsonobject.getJSONArray("characters");

            System.out.println("Built Array");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                String gender;
                if (explrObject.get("gender").toString().equals("0")) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }

                String charClass = "";
                switch(explrObject.get("class").toString()) {
                    case "1": charClass =  "Warrior"; break;
                    case "2": charClass =  "Paladin"; break;
                    case "3": charClass =  "Hunter"; break;
                    case "4": charClass =  "Rogue"; break;
                    case "5": charClass =  "Priest"; break;
                    case "6": charClass =  "Death Knight"; break;
                    case "7": charClass =  "Shaman"; break;
                    case "9": charClass =  "Mage"; break;
                    case "8": charClass =  "Warlock"; break;
                    case "10": charClass =  "Monk"; break;
                    case "11": charClass =  "Druid"; break;
                    case "12": charClass =  "Demon Hunter"; break;
                }
                System.out.printf("Information of character %d:\n" +
                                "\t Name: %s\n" +
                                "\t Level: %s\n" +
                                "\t Gender: %s\n" +
                                "\t Class: %s\n"
                        , i+1, explrObject.get("name"), explrObject.get("level"), gender, charClass);
                System.out.println();
            }

            System.out.println("Done displaying JSON Data...\nExiting program...");

        } catch(Exception e) {
            e.printStackTrace();
        }




        exit(0);
    }//End Main

    private static String htmlGetRequest(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}