package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.exception.NotFoundException;
import cn.edu.whut.sept.zuul.mapper.ConditionMapper;
import cn.edu.whut.sept.zuul.mapper.RoomMapper;
import cn.edu.whut.sept.zuul.model.Room;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ConditionMapper conditionMapper;

    /**
     * 将List<Map>转化成单一List,对roomMapper的进一步封装
     *
     * @param roomId 房间id
     * @return 单个Map, 适用于room方位
     */
    private Map<String, Integer> getExits(Integer roomId) {
        return roomMapper.getExitsByRoomId(roomId).stream()
                .collect(Collectors.toMap(
                        map -> Objects.toString(map.get("direction"), ""),
                        map -> (Integer) map.get("roomId")
                ));
    }

    @Override
    public Room getRoomById(Integer id) {
        Room room = roomMapper.getRoomById(id);
        if (room != null) {
            room.setExits(getExits(id));
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
            if (room == null) {
                throw new NotFoundException("房间不存在异常! 请检查数据库");
            }
            room.setExits(getExits(room.getId()));
            room.setItems(roomMapper.getItemsByRoomId(room.getId()));
        }
        return rooms;
    }

    @Override
    public List<Room> getAllRooms(Integer userId) {
        if (!conditionMapper.isConditionHasBeenSaved(userId))
            return getAllRooms();
        else {
            List<Room> rooms = roomMapper.getAllRooms();
            for (Room room : rooms) {
                if (room == null) {
                    throw new NotFoundException("房间不存在异常! 请检查数据库");
                }
                room.setExits(getExits(room.getId()));
                room.setItems(conditionMapper.findItemsByUserIdAndRoomId(userId, room.getId()));
            }
            return rooms;
        }
    }
}
