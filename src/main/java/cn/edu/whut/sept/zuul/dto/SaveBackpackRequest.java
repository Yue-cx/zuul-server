package cn.edu.whut.sept.zuul.dto;

import cn.edu.whut.sept.zuul.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class SaveBackpackRequest {
    private Integer userId;
    private List<Item> inventory;

}