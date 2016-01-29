package com.bank.server.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

/**
 * Collaborative Applied Research and Development between Morgan Stanley and University
 */
public class MysqlTest {

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "sdsd");
        conn.setAutoCommit(true);
        Statement statement = conn.createStatement();
        int i = 0;
        PreparedStatement updateSales = conn.prepareStatement(
                "insert into account (account_id, username, currency, balance, activated, revision) values(?, ?, ?, ?, ?, ?);"
        );
        while(true) {
            /*String sql = "insert into account (account_id, username, currency, balance, activated, revision) values(\"" +
                    UUID.randomUUID().toString() + "\", \"" +
                    "Test" + "\", \"" +
                    "RMB" + "\", \"" +
                    "1500" + "\", " +
                    true + ", " +
                    1 + ");";
                    */


            updateSales.setString(1, UUID.randomUUID().toString());
            updateSales.setString(2, "Test");
            updateSales.setString(3, "RMB");
            updateSales.setString(4, "1500");
            updateSales.setBoolean(5, true);
            updateSales.setInt(6, 1);

            updateSales.addBatch();
            i++;
            if(i == 300){
                updateSales.executeBatch();
                i = 0;
                break;
            }

            //updateSales.executeUpdate();

            //statement.executeUpdate(sql);
        }
        System.out.println("finish");
    }
}