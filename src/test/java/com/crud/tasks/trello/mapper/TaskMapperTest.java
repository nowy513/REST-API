package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TaskMapperTest {


    @Autowired
    TaskMapper taskMapper;


    @Test
    void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = taskMapper.mapToTask(taskDto);

        //When
        Long taskId = task.getId();
        String taskTitle = task.getTitle();
        String taskContent = task.getContent();

        //Then
        assertEquals(1L, taskId);
        assertEquals("Title", taskTitle);
        assertEquals("Content", taskContent);
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //When
        Long taskDtoId = taskDto.getId();
        String taskDtoTitle = taskDto.getTitle();
        String taskDtoContent = taskDto.getContent();

        //Then
        assertEquals(1L, taskDtoId);
        assertEquals("Title", taskDtoTitle);
        assertEquals("Content", taskDtoContent);
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "Title", "Content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(1, taskDtoList.size());    }

}
