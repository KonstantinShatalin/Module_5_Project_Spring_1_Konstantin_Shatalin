package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.entity.Task;
import project.service.TaskService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class TaskController {

    private TaskService taskService;

    @GetMapping
    public String tasks(Model model,
                            @RequestParam(value = "page",required = false, defaultValue = "1") int page,
                            @RequestParam(value = "limit",required = false, defaultValue = "10") int limit){
        List<Task> items = taskService.getItems((page - 1) * limit, limit);
        model.addAttribute("tasks", items);
        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                     @PathVariable Integer id,
                     @RequestBody TaskInfo info){
        if (id == null || id <= 0){
            throw new RuntimeException("Invalid id");
        }
        Task task = taskService.edit(id, info.getDescription(), info.getStatus());
        return tasks(model, 1, 10);
    }

    @PostMapping("/")
    public String add(Model model,
                     @RequestBody TaskInfo info){
        Task task = taskService.create(info.getDescription(), info.getStatus());
        return tasks(model, 1, 10);
    }

    @DeleteMapping("/{id}")
    public String delete(Model model,
                     @PathVariable Integer id){
        if (id == null || id <= 0){
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
        return tasks(model, 1, 10);
    }
}
