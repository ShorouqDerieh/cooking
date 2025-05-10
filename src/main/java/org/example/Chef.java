package org.example;

public class Chef {
    private String name;
    private String expertise;
    private int taskCount;
    private String email;

    public Chef(String name, String expertise, int taskCount) {
        this.name = name;
        this.expertise = expertise;
        this.taskCount = taskCount;
    }
    public String getName() {
        return name;
    }
    public String getExpertise() {
        return expertise;
    }
    public int getTaskCount() {
        return taskCount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
    public void  incrementTask(){
        this.taskCount++;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
