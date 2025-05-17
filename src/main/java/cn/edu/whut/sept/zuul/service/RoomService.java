package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.model.Room;

import java.util.List;

public interface RoomService {
    Room getRoomById(Integer id);
    List<Room> getAllRooms();
}
