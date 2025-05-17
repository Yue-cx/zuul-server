package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.model.Room;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        return roomService.getRoomById(id);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/user/{id}")
    public List<Room> getAllRooms(@PathVariable Integer id) {
        return roomService.getAllRooms(id);
    }
}
