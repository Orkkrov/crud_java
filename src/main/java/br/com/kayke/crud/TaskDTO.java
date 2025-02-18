package br.com.kayke.crud;

public class TaskDTO {
    private  int id;
    private  String author;
    private  String taskName;
    private  String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Tasks = \n{" +
                "\nid=" + id +
                ", \nauthor='" + author + '\'' +
                ", \ntaskName='" + taskName + '\'' +
                ", \ndescription='" + description + '\'' +
                "\n"+'}';
    }
}
