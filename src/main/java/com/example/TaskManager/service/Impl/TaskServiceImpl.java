package com.example.TaskManager.service.Impl;

import com.example.TaskManager.dto.TaskDTO;
import com.example.TaskManager.entity.Task;
import com.example.TaskManager.repository.TaskRepository;
import com.example.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;
    public final ModelMapper modelMapper;
    
    public List<TaskDTO> getAllTasks(){
        List<Task> taskDetails =  taskRepository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for(Task taskDetail: taskDetails){

            System.out.println(taskDetail);
            TaskDTO taskDTO = new TaskDTO(taskDetail.getId(), taskDetail.getSubject(),taskDetail.getTask(),taskDetail.getTaskDate());
            taskDTOS.add(taskDTO);
        }

        return taskDTOS;

    }

    public TaskDTO getTaskById(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()->new IllegalArgumentException("This id is not exists"));
        return modelMapper.map(task, TaskDTO.class);
    }

    public TaskDTO createNewTask(TaskDTO taskDTO){

        Task newtask = modelMapper.map(taskDTO, Task.class);
        Task task =  taskRepository.save(newtask);
        return modelMapper.map(task, TaskDTO.class);
    }

    public TaskDTO updateTaskById(Long id, TaskDTO taskDTO){
        Task task = taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("This id is not exists"));
        modelMapper.map(taskDTO, task);

        task = taskRepository.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    public TaskDTO updatePartialTaskById(Long id, Map<String , Object> update){
        Task task = taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("This id is not exists"));

        update.forEach((field, value) ->{
            switch (field){
                case "subject": task.setSubject((String) value);break;
                case "task": task.setTask((String) value);break;
                default: throw new IllegalArgumentException("Field is not supported");
            }
        });

        Task newTask = taskRepository.save(task);
        return modelMapper.map(newTask, TaskDTO.class);
    }

    public void deleteTaskById(Long id){
        if(!taskRepository.existsById(id)){
            throw new IllegalArgumentException("Student id is not Valid:- "+id);
        }
        taskRepository.deleteById(id);
    }
    
    
}
