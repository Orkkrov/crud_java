package br.com.kayke.crud;

import java.sql.SQLException;

public interface crud {
    public void insert() throws SQLException;
    public void select() throws SQLException;
    public void update(int id , String author, String taskName, String description) throws SQLException;
    public void delete(int id) throws SQLException;
}
