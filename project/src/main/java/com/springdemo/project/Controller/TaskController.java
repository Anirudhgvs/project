package com.springdemo.project.Controller;

import com.springdemo.project.DTO.TaskDTO;
import com.springdemo.project.Entity.Task;
import com.springdemo.project.Entity.User;
import com.springdemo.project.Repositories.UserRepo;
import com.springdemo.project.Service.TaskService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepo userRepository;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDTO request) {
        User assignedUser = userRepository.findById(new ObjectId(request.getAssignedTo()))
                .orElseThrow(() -> new RuntimeException("Assigned user not found"));

        User creator = userRepository.findById(new ObjectId(request.getCreatedBy()))
                .orElseThrow(() -> new RuntimeException("Creator user not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setAssignedTo(assignedUser);
        task.setCreatedBy(creator);
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable ObjectId id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable ObjectId id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ObjectId id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
