package cn.edu.whut.sept.zuul;

import cn.edu.whut.sept.zuul.controller.RoomController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// RoomControllerTest.java
@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRoom_ValidId_ReturnsRoomDetails() throws Exception {
        mockMvc.perform(get("/api/room/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("大厅"))
                .andExpect(jsonPath("$.exits.north").value(2));
    }

    @Test
    void getRoom_InvalidId_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/room/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void getAllRooms_ReturnsBasicInfo() throws Exception {
        mockMvc.perform(get("/api/room/"))
                .andExpect(status().isOk())
                // 验证房间基础结构和关键字段
                .andExpect(jsonPath("$[0].name").value("大厅"))
                .andExpect(jsonPath("$[0].description").value("城堡的中央大厅"))
                // 验证exits存在性（不验证具体值）
                .andExpect(jsonPath("$[0].exits").exists())

                // 验证items数组结构和关键属性
                .andExpect(jsonPath("$[0].items").isArray())
                .andExpect(jsonPath("$[0].items[0].name").value("椅子"))
                .andExpect(jsonPath("$[0].items[1].name").value("烛台"))
                // 验证物品的可用性标志
                .andExpect(jsonPath("$[0].items[?(@.name=='椅子')].isUsable").value(false))
                .andExpect(jsonPath("$[0].items[?(@.name=='烛台')].isUsable").value(true));
    }

    @Test
    void getUserRooms_ExistingUser_ReturnsUserItems() throws Exception {
        mockMvc.perform(get("/api/room/user/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userItems[0].name").value("椅子"));
    }

    @Test
    void getUserRooms_NewUser_ReturnsInitialRoom() throws Exception {
        mockMvc.perform(get("/api/room/user/999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("初始房间"));
    }

    @Test
    void testErrorHandling_InvalidPath_ReturnsError() throws Exception {
        mockMvc.perform(get("/api/room/invalid"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").value("房间不存在"));
    }
}