
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {

	@Query("select g from Gym g where g.closed=false")
	Collection<Gym> findAllNotClosed();

	@Query("select g from Gym g where g.manager.id = ?1")
	Collection<Gym> findAllMyGyms(int managerId);

	@Query("select a.gym from Activity a where a.gym.closed=false and a.id = ?1")
	Collection<Gym> findGymByActivity(int activityId);

	@Query("select g from Gym g where g.closed=false and g.manager.id = ?1")
	Collection<Gym> findGymsByManagerNotClosed(int managerId);

	@Query("select min(m.gyms.size), max(m.gyms.size), avg(m.gyms.size), stddev(m.gyms.size) from Manager m")
	Collection<Object[]> firstQuery();

	@Query("select min(c.gyms.size), max(c.gyms.size), avg(c.gyms.size), stddev(c.gyms.size) from Customer c")
	Collection<Object[]> secondQuery();

	@Query("select g1 from Gym g1 where (select count(a1) from Gym g join g.activities a1 where a1.cancelled=false and g1=g) >= All(select count(a2) from Gym g2 join g2.activities a2 where a2.cancelled=false group by g2)")
	Collection<Gym> forthQuery();

}
