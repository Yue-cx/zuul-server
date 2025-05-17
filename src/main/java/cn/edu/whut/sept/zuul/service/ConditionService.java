package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.model.Item;
import cn.edu.whut.sept.zuul.model.Room;

import java.util.List;

public interface ConditionService {
    /**
     * 获取特定用户的背包信息
     *
     * @param userId 用户ID
     * @return 用户背包中的物品列表
     */
    List<Item> getUserBackpackItems(Integer userId);

    int saveUserBackpackItems(int userId, List<Item> bagItems);

    int saveUserRoomItems(int userId, List<Room> rooms);
}
