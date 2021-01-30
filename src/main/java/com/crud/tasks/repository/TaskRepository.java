package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();

    List<Task> findTaskById(Long ID);
}
