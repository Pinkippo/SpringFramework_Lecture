package inhatc.spring.shop.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("어드민 페이지 권한 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 어드민 페이지 권한 테스트")
    @WithMockUser(username = "user", roles = "USER")
    void userTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/test"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


}