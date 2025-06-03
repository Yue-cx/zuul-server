package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.dto.SaveBackpackRequest;
import cn.edu.whut.sept.zuul.dto.SaveRoomsRequest;
import cn.edu.whut.sept.zuul.model.Item;
import cn.edu.whut.sept.zuul.model.Room;
import cn.edu.whut.sept.zuul.service.ConditionService;
import cn.edu.whut.sept.zuul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condition")
public class ConditionController {

    @Autowired
    private ConditionService conditionService;

    @GetMapping("/bag/{id}")
    public List<Item> getBagByUserId(@PathVariable Integer id) {
        return conditionService.getUserBackpackItems(id);
    }

    /**
     * 保存用户背包物品数据
     *
     * @param request 包含userId和inventory的请求体
     * @return 操作结果
     */

    @PostMapping("/save/backpack")
    public ResponseEntity<?> saveUserBackpackItems(@RequestBody SaveBackpackRequest request) {
        int affectedRows = conditionService.saveUserBackpackItems(request.getUserId(), request.getInventory());
        return ResponseEntity.ok().body("成功保存" + affectedRows + "条背包物品数据");
    }

    /**
     * 保存用户房间物品数据
     *
     * @param request 包含userId和rooms的请求体
     * @return 操作结果
     */
    @PostMapping("/save/rooms")
    public ResponseEntity<?> saveUserRoomItems(@RequestBody SaveRoomsRequest request) {
        int affectedRows = conditionService.saveUserRoomItems(request.getUserId(), request.getRooms());
        return ResponseEntity.ok().body("成功保存" + affectedRows + "条房间物品数据");
    }
}