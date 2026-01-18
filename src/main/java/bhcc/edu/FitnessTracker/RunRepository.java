package bhcc.edu.FitnessTracker;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RunRepository extends CrudRepository<Run, Long> {
    Run findById(long id);
    List<Run> findAll();
}
