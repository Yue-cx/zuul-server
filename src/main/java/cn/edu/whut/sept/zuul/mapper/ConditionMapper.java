package cn.edu.whut.sept.zuul.mapper;

import cn.edu.whut.sept.zuul.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConditionMapper {
    /**
     * 查找user_id为传入值且room_id为空的记录对应的item列表,即背包物品
     *
     * @param userId 用户ID
     * @return 物品列表
     */
    List<Item> findItemsByUserIdWithNullRoomId(Integer userId);

    /**
     * 查找user_id和room_id为特定值的记录对应的item列表,即用户房间状况
     *
     * @param userId 用户ID
     * @param roomId 房间ID
     * @return 物品列表
     */
    List<Item> findItemsByUserIdAndRoomId(Integer userId, Integer roomId);

    /**
     * 插入或更新物品实例记录
     * 当(user_id, item_id)存在时更新room_id，不存在时插入新记录
     *
     * @param userId 用户ID
     * @param itemId 物品ID
     * @param roomId 房间ID
     * @return 影响的行数
     */
    int insertOrUpdateItemInstance(@Param("userId") Integer userId,
                                   @Param("itemId") Integer itemId,
                                   @Param("roomId") Integer roomId);
}
