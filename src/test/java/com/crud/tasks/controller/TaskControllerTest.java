package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void shouldGetAllTasksTest() throws Exception {
//        Given
        List<Task> tasks = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(tasks);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetTaskTest() throws Exception{
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");

        when(dbService.getTaskById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask")
                        .param("taskId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Content")))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
    @Test
    void shouldCreateTaskTest() throws Exception{
//        Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");

        when(dbService.saveTask(any(Task.class))).thenReturn(new Task(1L, "Title", "Content"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTaskTest() throws Exception{
//        Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        TaskDto taskDto2 = new TaskDto(4L,"Update Title", "Update Content");

        when(dbService.saveTask(task)).thenReturn(new Task(4L, "Update Title", "Update Content"));
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto2);
        when(taskMapper.mapToTask(any())).thenReturn(task);

        Gson gson = new Gson();
        String jasonContent = gson.toJson(taskDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jasonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Update Title")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteTaskTest() throws Exception{
//        Given
        Task task = new Task(1L, "Title", "Content");

        when(dbService.saveTask(task)).thenReturn(task);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask")
                        .param("taskId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
