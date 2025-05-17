package cn.edu.whut.sept.zuul.mapper;

import cn.edu.whut.sept.zuul.model.Item;
import cn.edu.whut.sept.zuul.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {
    Room getRoomById(@Param("id") Integer id);
    List<Room> getAllRooms();
    List<Item> getItemsByRoomId(@Param("roomId") Integer roomId);
    List<Map<String, Integer>> getExitsByRoomId(@Param("roomId") Integer roomId);
}
