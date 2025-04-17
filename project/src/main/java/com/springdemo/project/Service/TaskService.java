package com.springdemo.project.Service;

import com.springdemo.project.Entity.Role;
import com.springdemo.project.Entity.Task;
import com.springdemo.project.Entity.User;
import com.springdemo.project.Repositories.TaskRepo;
import com.springdemo.project.Repositories.UserRepo;
import com.springdemo.project.Utils.AuthUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthUtil authUtil;

    public Task createTask(Task task) {
        User currentUser = authUtil.getCurrentUser();
        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only ADMIN can create tasks.");
        }
        return taskRepo.save(task);
    }

    public List<Task> getTasks() {
        User user = authUtil.getCurrentUser();
        return user.getRole() == Role.ADMIN ?
                taskRepo.findAll() :
                taskRepo.findByAssignedTo(user);
    }

    public Task getTaskById(ObjectId id) {
        return taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(ObjectId id, Task updatedTask) {
        User currentUser = authUtil.getCurrentUser();
        Task task = getTaskById(id);

        if (currentUser.getRole() != Role.ADMIN && !task.getAssignedTo().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Not authorized to update this task.");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setAssignedTo(updatedTask.getAssignedTo());
        return taskRepo.save(task);
    }

    public void deleteTask(ObjectId id) {
        User currentUser = authUtil.getCurrentUser();
        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only ADMIN can delete tasks.");
        }
        taskRepo.deleteById(id);
    }
}
