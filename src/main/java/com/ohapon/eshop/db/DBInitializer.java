package com.ohapon.eshop.db;

import com.ohapon.eshop.dao.jdbc.ConnectionFactory;

import java.sql.*;

public class DBInitializer {

    private ConnectionFactory connectionFactory;

    public DBInitializer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void init() {
        try {
            Connection con = getConnection();
            createDB(con);
            populateDB(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createDB(Connection con) throws SQLException {
        Statement stm = con.createStatement();
        stm.execute("CREATE TABLE product (id int, name varchar(100), price float, created_date date)");
        stm.close();
    }

    private void populateDB(Connection con) throws SQLException {
        PreparedStatement pstm = con.prepareStatement("INSERT INTO product (id, name, price, created_date) VALUES (?, ?, ?, ?)");
        for (int i = 1; i < 20; i++) {

            pstm.setInt(1, i);
            pstm.setString(2, "Product " + i);
            pstm.setFloat(3, 100 + (i * 10));
            pstm.setDate(4, new java.sql.Date(2021, 7, 7));

            pstm.execute();
        }

        pstm.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionFactory.getConnection();
    }

}
