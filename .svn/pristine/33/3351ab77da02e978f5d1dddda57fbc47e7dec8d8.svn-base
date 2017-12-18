
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query("select a from Activity a where a.gym.closed = false and a.cancelled = false and a.gym.id = ?1")
	Collection<Activity> findActivitiesByGymId(int gymId);

	@Query("select a from Activity a where a.gym.id = ?1")
	Collection<Activity> findActivitiesOfGymId(int gymId);

	@Query("select a from Activity a where a.gym.closed = false and a.cancelled = false")
	Collection<Activity> findActivitiesNotClosed();

	@Query("select a from Activity a where a.gym.closed = false and a.cancelled = false and (a.title like %?1% or a.description like %?1%) and a.day like %?2% and (a.startTime like %?3% or a.endTime like %?3%)")
	Collection<Activity> search(String keyword, String day, String time);

	@Query("select a from Activity a join a.trainers t where t.id = ?1")
	Collection<Activity> findActivitiesByTrainer(int trainerId);
}
