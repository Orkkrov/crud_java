package br.com.kayke.crud;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Task implements crud{
    private  String author;
    private  String taskName;
    private  String description;
    private Date dataCreation;
    private boolean finalized;
    private List<Task> tasks = new ArrayList<>();

    //database data
     String url = "jdbc:mysql://127.0.0.1/crud";
     String root = "root";
     String password = "fr33domt3c";

    public Task() {
    }

    public Task(String author, String taskName, String description) {
        this.author = author;
        this.taskName = taskName;
        this.description = description;

        dataCreation = new Date(System.currentTimeMillis());
        this.finalized = false;


    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(Date dataCreation) {
        this.dataCreation = dataCreation;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }



    /*
    *
    *
    * insert
    *
    *
    * */

    @Override
    public  void insert() throws SQLException {
        String name = JOptionPane.showInputDialog("Whos the author: ");
        String task = JOptionPane.showInputDialog("task's name: ");
        String desc = JOptionPane.showInputDialog("write the task: ");

        Task taskOBJ = new Task(name, task, desc);


        String url = "jdbc:mysql://127.0.0.1/crud";
        String root = "root";
        String password = "fr33domt3c";
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sql = "insert into tasks(task_author, task_name, task_desc) values(?,?,?);";
        PreparedStatement ps = null;
        Connection c = null;
        try {



            c = DriverManager.getConnection(url, root, password);
            ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, task);
            ps.setString(3, desc);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage().toUpperCase());
        } finally {
            c.close();
        }

    }

    /*
    *
    *
    *
    *
    * select
    *
    *
    *
    * */
    @Override
    public  void select() throws SQLException {
        List<TaskDTO> taskss = new ArrayList<>();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            c = DriverManager.getConnection(url, root, password);
            st = c.createStatement();
            rs = st.executeQuery("select  * from tasks;");

            while (rs.next()) {
                TaskDTO ts = new TaskDTO();
                ts.setId(rs.getInt("id"));
                ts.setAuthor(rs.getString("task_author"));
                ts.setTaskName(rs.getString("task_name"));
                ts.setDescription(rs.getString("task_desc"));
                taskss.add(ts);
            }

            for (int i = 0; i < taskss.size(); i++) {
                System.out.println(taskss.get(i).toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            c.close();
        }

    }

    /*
    *
    *
    *
    * update
    *
    *
    *
    *
    *
    *
    * */
    @Override
    public void update(int id , String author, String taskName, String description) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        /*
        *
                ts.setId(rs.getInt("id"));
                ts.setAuthor(rs.getString("task_author"));
                ts.setTaskName(rs.getString("task_name"));
                ts.setDescription(rs.getString("task_desc"));
        * */
        try{
            ps = c.prepareStatement("update tasks set task_author = ?, task_name = ?, task_desk = ? where id = "+id+";" );
            ps.setString(1,author);
            ps.setString(2,taskName);
            ps.setString(3,description);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            c.close();
        }

    }


    /*
     *
     *
     *
     *
     * delete
     *
     *
     *
     *
     *
     * */
    @Override
    public void delete(int id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DriverManager.getConnection(url, root, password);
            ps = c.prepareStatement("delete  from tasks where id = " + id);
            ps.execute();
            JOptionPane.showMessageDialog(null,"deleted with sucess");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            c.close();
        }

    }
}
