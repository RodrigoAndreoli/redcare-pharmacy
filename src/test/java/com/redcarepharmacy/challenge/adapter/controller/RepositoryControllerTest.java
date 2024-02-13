package com.redcarepharmacy.challenge.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redcarepharmacy.challenge.adapter.controller.model.RepositoryResponse;
import com.redcarepharmacy.challenge.application.GetRepositoriesUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;

import static com.redcarepharmacy.challenge.mock.MockFactory.buildRepositoryList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RepositoryController.class)
@Import({RepositoryController.class})
class RepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetRepositoriesUseCase getRepositoriesUseCase;

    private final static String REPOSITORY_ENDPOINT = "/api/v1/repository";


    @Test
    @DisplayName("GIVEN no parameters " +
            "WHEN invoking /repository endpoint " +
            "THEN GetRepositoriesUseCase should be called, the response status should be OK and content should be a JSON.")
    void getRepositoryListOk() throws Exception {
        var repositories = buildRepositoryList(10);
        var expected = repositories.stream().map(RepositoryResponse::fromDomain).collect(Collectors.toList());

        when(getRepositoriesUseCase.execute(null, null, null)).thenReturn(repositories);

        mockMvc.perform(get(REPOSITORY_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));

        verify(getRepositoriesUseCase, times(1)).execute(null, null, null);
    }

    @Test
    @DisplayName("GIVEN a non-admitted size parameter " +
            "WHEN invoking /repository endpoint " +
            "THEN GetRepositoriesUseCase should not be called, the response status should be BadRequest and without content.")
    void getRepositoryListBadRequest() throws Exception {
        Integer size = 15;

        mockMvc.perform(get(REPOSITORY_ENDPOINT + "?size={size}", size).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(getRepositoriesUseCase, times(0)).execute(any(), any(), any());
    }

    @Test
    @DisplayName("GIVEN a correct set of parameters " +
            "WHEN invoking /repository endpoint " +
            "THEN GetRepositoriesUseCase should be called, the response status should be OK and content should be a JSON.")
    void getRepositoryListWithParametersOk() throws Exception {
        String dateFrom = "2020-02-23";
        String language = "java";
        Integer size = 50;
        var repositories = buildRepositoryList(size);
        var expected = repositories.stream().map(RepositoryResponse::fromDomain).collect(Collectors.toList());

        when(getRepositoriesUseCase.execute(dateFrom, language, size)).thenReturn(repositories);

        mockMvc.perform(get(REPOSITORY_ENDPOINT + "?dateFrom={dateFrom}&language={language}&size={size}",
                        dateFrom, language, size).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));

        verify(getRepositoriesUseCase, times(1)).execute(dateFrom, language, size);
    }


}