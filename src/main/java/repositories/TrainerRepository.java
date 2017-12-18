
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	@Query("select t from Trainer t where t.userAccount.id = ?1")
	Trainer findByUserAccountId(int userAccountId);

	@Query("select t from Trainer t join t.activities a where a.id = ?1")
	Collection<Trainer> findTrainerByActivity(int activityId);

	@Query("select t from Trainer t where t.name like %?1% or t.surname like %?1%")
	Collection<Trainer> trainersByKeyWord(String keyword);

	@Query("select a.gym.trainers from Activity a where a.id = ?1")
	Collection<Trainer> findTrainerOfAGym(int activityId);

	@Query("select g.trainers from Gym g where g.id = ?1")
	Collection<Trainer> findTrainers(int gymId);

}
