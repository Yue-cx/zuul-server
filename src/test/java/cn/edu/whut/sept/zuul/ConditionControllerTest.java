package cn.edu.whut.sept.zuul;

import cn.edu.whut.sept.zuul.controller.ConditionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ConditionController.class)
class ConditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserBackpack_ValidId_ReturnsItems() throws Exception {
        int userId = 1;
        mockMvc.perform(get("/api/condition/bag/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("剑"))
                .andExpect(jsonPath("$[1].name").value("药水"));
    }

    @Test
    void getUserBackpack_InvalidId_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/condition/bag/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveBackpack_ValidRequest_ReturnsSuccess() throws Exception {
        String requestBody = "{"
                + "\"userId\":5,"
                + "\"inventory\":["
                + "{\"id\":1,\"name\":\"剑\",\"description\":\"锋利的武器\",\"weight\":2.5,\"isUsable\":true}"
                + "]}";

        mockMvc.perform(post("/api/condition/save/backpack")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("成功保存1条背包物品数据"));
    }

    @Test
    void saveRooms_ValidRequest_ReturnsSuccess() throws Exception {
        String requestBody = "{"
                + "\"userId\":5,"
                + "\"rooms\":["
                + "{\"id\":1,\"name\":\"大厅\",\"description\":\"城堡的大厅\","
                + "\"items\":[{\"id\":3,\"name\":\"椅子\",\"description\":\"木制椅子\",\"weight\":5.0,\"isUsable\":false}]}"
                + "]}";

        mockMvc.perform(post("/api/condition/save/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("成功保存1条房间物品数据"));
    }
}

