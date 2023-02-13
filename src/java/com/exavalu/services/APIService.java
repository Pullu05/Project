/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.APIUser;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author RISHAV DUTTA
 */
public class APIService {

    public static ArrayList consumeDataFromAPI() throws ParseException {
        // String result = null;
        ArrayList<APIUser> apiUsers = new ArrayList();
        try {

            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONArray jsonArray = (JSONArray) parse.parse(inline);

                for (int i = 0; i < jsonArray.size(); i++) {

                    APIUser apiUser = new APIUser();
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    String id = obj.get("id").toString();
                    String userid = obj.get("userId").toString();
                    String title = obj.get("title").toString();
                    String body = obj.get("body").toString();

                    apiUser.setId(id);
                    apiUser.setUserId(userid);
                    apiUser.setTitle(title);
                    apiUser.setBody(body);

                    apiUsers.add(apiUser);

                }

                System.out.println("Size of User List" + apiUsers.size());
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return apiUsers;
    }

    public static boolean insertDataInDB(ArrayList apiUsers) {
        boolean result = false;
        try {

            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO apiusers(id,userId,body,title)" + "VALUES(?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);

            Iterator itr = apiUsers.iterator();
            while (itr.hasNext()) {

                APIUser apiUser = (APIUser) itr.next();

                preparedStatement.setInt(1, Integer.parseInt(apiUser.getId()));
                preparedStatement.setInt(2, Integer.parseInt(apiUser.getUserId()));
                preparedStatement.setString(3, apiUser.getBody());
                preparedStatement.setString(4, apiUser.getTitle());

                preparedStatement.addBatch();

            }
            int[] count = preparedStatement.executeBatch();
            con.commit();

            if (count.length >= 1) {
                result = true;
                System.out.println(count.length);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }
}
