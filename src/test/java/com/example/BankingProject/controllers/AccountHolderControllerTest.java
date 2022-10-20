package com.example.BankingProject.controllers;

import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.embedables.Address;
import com.example.BankingProject.models.users.AccountHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
     * show_HoldersAccountsById_works()
     * create_HolderAccount_works()
     * check_HolderBalance_works()
     * transferMoney_works()
     * */

    @Test
    @DisplayName("Testing if show holder accounts by id works")
    void show_HoldersAccountsById_works() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/holderAccount/showAccounts/2")).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
    }

    @Test
    @DisplayName("Testing if create Holder Accounts works")
    void create_HolderAccount_works() throws Exception {
        AccountHolder accountHolder1 = new AccountHolder("Antonio", passwordEncoder.encode("quimPassword"), "quim@gmail.com", LocalDate.of(2008, 05, 10),
                "123456789", new Address("Calle falsa123", "Cambrils", "43850", "Tarragona", "Spain"));
        String body = objectMapper.writeValueAsString(accountHolder1);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/holderAccount/createUser").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Antonio"));
    }

    @Test
    @DisplayName("Testing if check Holder own balance")
    void check_HolderBalance_works() throws Exception {
        CheckOwnBalanceDTO checkOwnBalanceDTO = new CheckOwnBalanceDTO(2L, 1L);
        String body = objectMapper.writeValueAsString(checkOwnBalanceDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(get("/holderAccount/checkBalance").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.00"));
    }

    @Test
    @DisplayName("Testing if Holder transfer money works")
    void transferMoney_works() throws Exception {
        TransferMoneyDTO transferMoneyDTO = new TransferMoneyDTO(1L, BigDecimal.valueOf(150), 3L);
        String body = objectMapper.writeValueAsString(transferMoneyDTO);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(patch("/holderAccount/transfer").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000.00"));
    }

}
