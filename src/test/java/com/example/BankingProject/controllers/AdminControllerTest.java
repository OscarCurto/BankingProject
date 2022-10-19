package com.example.BankingProject.controllers;

import com.example.BankingProject.dtos.*;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.models.users.Admin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    //System.out.println(mvcResult.getResponse().getContentAsString());
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
     * showAllAccounts_works()
     * createAccount_works()
     * createThirdPartyUser_works()
     * createAdmin_works()
     * deleteAccountById_works()
     * checkAllBalanceById_works()
     * modifyBalanceById_works()
     * modifyStatusById_works()
     * */

    @Test
    @DisplayName("Show all accounts in the app")
    void showAllAccounts_works() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin")).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }

    @Test
    @DisplayName("Create a new account")
    void createAccount_works() throws Exception {
        AccountHolder primaryAccountHolder = new AccountHolder();
        AccountHolder secondaryAccountHolder = new AccountHolder();
        CreateAccountDTO createAccountDTO = new CreateAccountDTO(2L, new Money(BigDecimal.valueOf(1000.00)), primaryAccountHolder, secondaryAccountHolder, Status.FROZEN,
                new Money(BigDecimal.valueOf(1000)), BigDecimal.valueOf(0.0025), LocalDate.of(2021, 04, 10), "Saving");
        String body = objectMapper.writeValueAsString(createAccountDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/admin/createAccount").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("40.00"));
    }

    @Test
    @DisplayName("Create a ThirdPartyUser")
    void createThirdPartyUser_works() throws Exception {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO("Quim", "1234");
        String body = objectMapper.writeValueAsString(thirdPartyDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/admin/createThirdPartyUser").
                content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1234"));
    }

    @Test
    @DisplayName("Create an Admin")
    void createAdmin_works() throws Exception {
        AdminDTO adminDTO = new AdminDTO("Oscar", "1234");
        String body = objectMapper.writeValueAsString(adminDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/admin/createAdminUser").
                content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Oscar"));
    }

    @Test
    @DisplayName("Delete an existing account")
    void deleteAccountById_works() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/admin/delete/3")).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().isEmpty());
    }

    @Test
    @DisplayName("Check balance of all the accounts in the app")
    void checkAllBalanceById_works() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkAdminBalance/2")).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.00"));
    }

    @Test
    @DisplayName("Modify balance by id")
    void modifyBalanceById_works() throws Exception {
        ModifyBalanceDTO modifyBalanceDTO = new ModifyBalanceDTO(1L, new BigDecimal(1000.00), "saving");
        String body = objectMapper.writeValueAsString(modifyBalanceDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(patch("/modifyAdminBalance").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.00"));
    }

    @Test
    @DisplayName("Modify status by id")
    void modifyStatusById_works() throws Exception {
        StatusDTO statusDTO = new StatusDTO(1L, Status.FROZEN);
        String body = objectMapper.writeValueAsString(statusDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(patch("/modifyAdminStatus").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FROZEN"));
    }

    @Test
    @DisplayName("Show all users in the app")
    void showAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/users")).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Quim"));
    }
}