
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {

	@Query("select min(w.steps.size), avg(w.steps.size), max(w.steps.size) from Workout w")
	Collection<Object[]> seventhQuery();

}
