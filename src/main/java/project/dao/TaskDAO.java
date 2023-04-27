package project.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Task;

import java.util.List;

@Repository
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaskDAO {
    private final SessionFactory sessionFactory;

    public Task getById (final int id){
        return getCurrentSession().get(Task.class, id);
    }

    public List<Task> getItems (int offset, int count){
        Query<Task> query = getCurrentSession().createQuery("select t from Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<Task> findAll (){
        return getCurrentSession().createQuery("select t from Task t", Task.class).list();
    }

    @Transactional
    public Task saveOrUpdate (final Task task){
        getCurrentSession().persist(task);
        return task;
    }

    @Transactional
    public Task update (final Task task){
        return getCurrentSession().merge(task);
    }

    @Transactional
    public void delete (final Task task){
        getCurrentSession().remove(task);
    }

    @Transactional
    public void deleteById (final int taskId){
        final Task task = getById(taskId);
        delete(task);
    }
    protected Session getCurrentSession (){
        return sessionFactory.getCurrentSession();
    }
}
