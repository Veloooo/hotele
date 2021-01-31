package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                service.getTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask",consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

        @RequestMapping(method=RequestMethod.DELETE, value = "deleteTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTask(@RequestBody TaskDto taskDto) {
        service.deleteTask(taskDto.getId());
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteAll")
    public void deleteTasks() {
        service.deleteTasks();
    }


//    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
//    public List<TaskDto> getTasks() {
//        return new ArrayList<>();
//    }
//
//    @GetMapping(value="getTask")
//    public TaskDto getTask(Long taskId) {
//        return new TaskDto(1L, "test title", "test_content");
//    }
//
//    @DeleteMapping
//    public void deleteTask(Long taskId) {}
//
//    @PutMapping
//    public TaskDto updateTask(TaskDto taskDto) {
//        return new TaskDto(1L, "Edited test title", "Test content");
//    }
//
//    @PostMapping
//    public void createTask(TaskDto taskDto) {}
//
//    @GetMapping(value="checkSize")
//    public int checkSize(){
//        return 1;
//    }
//
//    @PutMapping(value="modify")
//    public TaskDto modify(TaskDto taskDto){
//        return new TaskDto(2L,"modifed","test content");
//    }
//
//    @DeleteMapping(value="clearTasks")
//    public void clearTasks(String taskTitle){}


}
