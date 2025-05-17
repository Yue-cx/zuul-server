package cn.edu.whut.sept.zuul.model;

import lombok.Data;

@Data
public class Item {
    private Integer id;
    private String name;
    private String description;
    private Double weight;
    private Boolean isUsable;
}