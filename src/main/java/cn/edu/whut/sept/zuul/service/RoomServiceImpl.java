package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.exception.NotFoundException;
import cn.edu.whut.sept.zuul.mapper.RoomMapper;
import cn.edu.whut.sept.zuul.model.Room;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Room getRoomById(Integer id) {
        Room room = roomMapper.getRoomById(id);
        if (room != null) {
            room.setExits(roomMapper.getExitsByRoomId(id));
            room.setItems(roomMapper.getItemsByRoomId(id));
        } else {
            throw new NotFoundException("id为 " + id.toString() + " 的房间不存在");
        }
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = roomMapper.getAllRooms();
        for (Room room : rooms) {
            if(room==null){
                throw new NotFoundException("房间不存在异常! 请检查数据库");
            }
            room.setExits(roomMapper.getExitsByRoomId(room.getId()));
            room.setItems(roomMapper.getItemsByRoomId(room.getId()));
        }
        return rooms;
    }
}
