package Group20SpringBoot.Group20.task.repository;

import Group20SpringBoot.Group20.task.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
}
