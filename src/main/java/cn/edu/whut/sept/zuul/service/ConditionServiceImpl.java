package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.exception.ValidationException;
import cn.edu.whut.sept.zuul.mapper.ConditionMapper;
import cn.edu.whut.sept.zuul.model.Item;
import cn.edu.whut.sept.zuul.model.Room;
import cn.edu.whut.sept.zuul.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ConditionMapper conditionMapper;

    @Override
    public List<Item> getUserBackpackItems(Integer userId) {
        // 使用mapper查询用户背包中的物品(room_id为null的记录)
        return conditionMapper.findItemsByUserIdWithNullRoomId(userId);
    }

    @Override
    public int saveUserBackpackItems(int userId,List<Item> bagItems) {
        if(bagItems==null)
            throw new ValidationException("传入数组不能为null!");
        int records=0;
        for(Item item:bagItems){
            records+=conditionMapper.insertOrUpdateItemInstance(userId,item.getId(),null);
        }
        return records;
    }

    @Override
    public int saveUserRoomItems(int userId,List<Room> rooms) {
        if(rooms==null)
            throw new ValidationException("传入数组不能为null!");
        int records=0;
        for(Room room:rooms){
            for(Item item:room.getItems()){
                records+=conditionMapper.insertOrUpdateItemInstance(userId,item.getId(),room.getId());
            }
        }
        return records;
    }
}