
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.GymRepository;
import domain.Activity;
import domain.Customer;
import domain.Gym;
import domain.Manager;

@Service
@Transactional
public class GymService {

	//Managed Repository =============================================================================

	@Autowired
	private GymRepository	gymRepository;

	//Other services =================================================================================

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private ActivityService	activityService;

	@Autowired
	private CustomerService	customerService;

	@Autowired
	private Validator		validator;


	//Simple CRUD methods ============================================================================

	public Gym create() {
		Gym result;

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(manager != null);

		result = new Gym();

		Collection<Activity> activities = new ArrayList<Activity>();
		Collection<Customer> customers = new ArrayList<Customer>();
		result.setActivities(activities);
		result.setClosed(false);
		result.setCustomers(customers);
		result.setManager(manager);

		return result;
	}

	public Gym save(Gym gym) {
		Assert.notNull(gym);

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(gym.getManager().equals(manager));
		Gym result;

		result = gymRepository.save(gym);

		return result;
	}

	public void delete(Gym gym) {
		Assert.notNull(gym);

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(gym.getManager().equals(manager));

		for (Activity activity : gym.getActivities()) {

			activity.setCancelled(true);
			activityService.save(activity);
		}

		gym.setClosed(true);
		save(gym);
	}

	public Gym findOne(int gymId) {
		// TODO Auto-generated method stub
		Gym gym;

		gym = gymRepository.findOne(gymId);

		return gym;
	}

	public Collection<Gym> findAll() {
		// TODO Auto-generated method stub
		Collection<Gym> gyms;

		gyms = gymRepository.findAll();

		return gyms;
	}

	//Other Business Methods =========================================================================

	public Collection<Gym> findAllNotClosed() {

		Collection<Gym> gyms;
		gyms = gymRepository.findAllNotClosed();

		return gyms;
	}

	public Collection<Gym> findGymByActivity(int activityId) {

		Collection<Gym> gyms;
		gyms = gymRepository.findGymByActivity(activityId);

		return gyms;
	}

	public Collection<Gym> findAllMyGyms(int managerId) {

		Collection<Gym> gyms;
		gyms = gymRepository.findAllMyGyms(managerId);

		return gyms;
	}

	public Collection<Gym> findGymsByManagerNotClosed(int managerId) {

		Collection<Gym> gyms;
		gyms = gymRepository.findGymsByManagerNotClosed(managerId);

		return gyms;
	}

	public Gym reconstruct(Gym gym, BindingResult binding) {
		Gym result;

		Manager manager = managerService.findByPrincipal();

		if (gym.getId() == 0) {
			result = gym;
			result.setManager(manager);
			result.setClosed(false);
			validator.validate(result, binding);
		} else {
			Gym res = gymRepository.findOne(gym.getId());
			result = gym;
			result.setLogo(gym.getLogo());
			result.setName(gym.getName());
			result.setFee(gym.getFee());
			result.setAddress(gym.getAddress());

			result.setActivities(res.getActivities());
			result.setCustomers(res.getCustomers());
			result.setId(res.getId());
			result.setVersion(res.getVersion());
			result.setClosed(res.getClosed());
			result.setManager(res.getManager());
			result.setTrainers(res.getTrainers());

			validator.validate(result, binding);
		}

		return result;
	}

	public Gym recover(Gym gym) {
		Gym result;

		result = gymRepository.findOne(gym.getId());

		return result;
	}

	public Gym join(Gym gym) {

		Customer principal = customerService.findByPrincipal();

		gym.getCustomers().add(principal);
		principal.getGyms().add(gym);

		Gym saved = gymRepository.save(gym);
		customerService.save(principal);

		return saved;
	}

	public Gym leave(Gym gym) {
		// TODO Auto-generated method stub

		Customer principal = customerService.findByPrincipal();

		gym.getCustomers().remove(principal);
		principal.getGyms().remove(gym);

		Gym saved = gymRepository.save(gym);
		customerService.save(principal);

		return saved;
	}

	public Collection<Object[]> firstQuery() {
		Collection<Object[]> firstQuery;

		firstQuery = gymRepository.firstQuery();

		return firstQuery;
	}

	public Collection<Object[]> secondQuery() {
		Collection<Object[]> secondQuery;

		secondQuery = gymRepository.secondQuery();

		return secondQuery;
	}

	public Collection<Gym> forthQuery() {
		Collection<Gym> forthQuery;

		forthQuery = gymRepository.forthQuery();

		return forthQuery;
	}

}
