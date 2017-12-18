
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);

	@Query("select min(g.customers.size), max(g.customers.size), avg(g.customers.size), stddev(g.customers.size) from Gym g")
	Collection<Object[]> thirstQuery();

	@Query("select c1 from Customer c1 where (select count(a1) from Customer c join c.activities a1 where c1=c) >= All(select count(a2) from Customer c2 join c2.activities a2 group by c2)")
	Collection<Customer> fifthQuery();

}
