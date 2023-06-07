package com.trantin.simpleweb.http.dao;

import com.trantin.simpleweb.http.entity.TableColumn;
import org.hibernate.SessionFactory;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableColumnsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private Connection connection;

    @Transactional
    public List<TableColumn> getByTable(String tableName){
        List<TableColumn> columns = new ArrayList<>();
        try {
            ResultSet rs = connection.createStatement().executeQuery(
                    "SELECT column_name, data_type, is_nullable FROM information_schema.columns WHERE table_name = '"+ tableName +"' ORDER BY ordinal_position"
            );

            while (rs.next()){
                columns.add(new TableColumn(rs.getString("column_name"), rs.getString("data_type"), rs.getString("is_nullable")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return columns;
    }
}
