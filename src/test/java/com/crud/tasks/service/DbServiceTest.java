package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    void getAllTaskTest(){
//        Given
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(taskRepository.findAll()).thenReturn(tasks);

//          When
        List<Task> taskList = dbService.getAllTasks();

//        Then
        assertEquals(1, taskList.size());
    }

    @Test
    void getTaskTest(){
//        Given
        Task task = new Task(1L, "Title Test", "Content Test");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

//        When
        Optional<Task> theTask = dbService.getTaskById(1L);

//        Then
        assertEquals(Optional.of(task), theTask);

    }

    @Test
    void saveTaskTest(){
//        Given
        Task task = new Task(1L, "Title", "Content");

        when(taskRepository.save(task)).thenReturn(task);

//        When
        Task saveTest = dbService.saveTask(task);

//        Then
        assertEquals(1L, saveTest.getId());
        assertEquals("Title", saveTest.getTitle());
        assertEquals("Content", saveTest.getContent());
    }

    @Test
    void deleteTaskTest(){
//        Given
        Task task = new Task(1L, "Title", "Content");
        Long id = task.getId();

        dbService.deleteTask(id);

//        When
        Optional<Task> theTask = dbService.getTaskById(1L);

//        Then
        assertFalse(theTask.isPresent());
    }
}
