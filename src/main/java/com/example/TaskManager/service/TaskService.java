package com.example.TaskManager.service;

import com.example.TaskManager.dto.TaskDTO;

import java.util.List;
import java.util.Map;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO createNewTask(TaskDTO taskDTO);

    TaskDTO updateTaskById(Long id, TaskDTO taskDTO);

    TaskDTO updatePartialTaskById(Long id, Map<String, Object> update);

    void deleteTaskById(Long id);
}
