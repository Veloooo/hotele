package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DbService service;

    @MockBean
    TaskMapper mapper;

    @Test
    void shouldGetTasks() throws Exception{
        //Given
        List<TaskDto> dtos = List.of(new TaskDto(1L, "Test task", "content"));
        when(mapper.mapToTaskDtoList(anyList())).thenReturn(dtos);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content")));
    }

    @Test
    void shouldGetTask() throws Exception{
        //Given
        Task task = new Task(1L, "Test task", "content");
        TaskDto dto = new TaskDto(1L, "mapped task", "mapped content");
        when(mapper.mapToTaskDto(task)).thenReturn(dto);
        when(service.getTask(anyLong())).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("mapped task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("mapped content")));
    }

    @Test
    void shouldUpdateTask() throws Exception{
        //Given
        Task task = new Task(1L, "Test task", "content");
        TaskDto dto = new TaskDto(1L, "mapped task", "mapped content");
        when(mapper.mapToTaskDto(task)).thenReturn(dto);
        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-*")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("mapped task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("mapped content")));
    }

    @Test
    void shouldCreateTask() throws Exception{
        //Given
        Task task = new Task(1L, "Test task", "content");
        TaskDto dto = new TaskDto(1L, "mapped task", "mapped content");
        when(mapper.mapToTask(dto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(dto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    public void shouldDeleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/task/deleteTask")
                .param("taskId","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
