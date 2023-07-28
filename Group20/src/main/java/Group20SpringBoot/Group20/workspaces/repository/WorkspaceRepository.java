package Group20SpringBoot.Group20.workspaces.repository;

import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<WorkspaceModel, Integer> {
}
