package cn.edu.whut.sept.zuul.dto;

import cn.edu.whut.sept.zuul.model.Room;
import lombok.Data;

import java.util.List;

@Data
public class SaveRoomsRequest {
    private Integer userId;
    private List<Room> rooms;
}
