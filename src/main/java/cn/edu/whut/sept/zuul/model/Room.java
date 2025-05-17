package cn.edu.whut.sept.zuul.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class Room {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Map<String, Integer> exits;
    private List<Item> items;
}
