package project.service;

import project.dao.TaskDAO;
import project.entity.Status;
import project.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class TaskService {
    private final TaskDAO taskDAO;

    public List<Task> getItems (int offset, int count){
        return taskDAO.getItems(offset,count);
    }
    public List<Task> findAll (){
        return taskDAO.findAll();
    }
    @Transactional
    public Task create (String description, Status status){
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }
    @Transactional
    public Task edit (int id, String description, Status status){
        Task task = taskDAO.getById(id);
        if (task == null){
            throw new RuntimeException("Not found!");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;

    }
    @Transactional
    public Task delete (int id){
        Task task = taskDAO.getById(id);
        if (task == null){
            throw new RuntimeException("Not found!");
        }
        taskDAO.delete(task);
        return task;
    }

}
