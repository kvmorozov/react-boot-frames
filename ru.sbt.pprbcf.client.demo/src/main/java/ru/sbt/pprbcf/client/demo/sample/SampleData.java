package ru.sbt.pprbcf.client.demo.sample;

/**
 * Created by sbt-morozov-kv on 14.02.2017.
 */
public class SampleData {

    private int id;
    private String name;
    private String desc;

    public SampleData(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
