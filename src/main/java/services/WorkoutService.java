
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.WorkoutRepository;
import domain.Annotation;
import domain.Gym;
import domain.Manager;
import domain.Step;
import domain.Workout;

@Service
@Transactional
public class WorkoutService {

	//Managed Repository =============================================================================

	@Autowired
	private WorkoutRepository	workoutRepository;

	//Services
	// ===============================================================================================

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private GymService			gymService;

	@Autowired
	private StepService			stepService;

	@Autowired
	private AnnotationService	annotationService;

	@Autowired
	private Validator			validator;


	//SCRUDs Methods
	//================================================================================================

	public Workout create(int gymId) {
		Workout result;

		Manager manager = managerService.findByPrincipal();
		Gym gym = gymService.findOne(gymId);

		Assert.isTrue(manager != null);

		result = new Workout();

		Collection<Annotation> annotations = new ArrayList<Annotation>();
		result.setAnnotations(annotations);
		result.setGym(gym);

		return result;
	}

	public Workout save(Workout workout) {
		Assert.notNull(workout);

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(workout.getGym().getManager().equals(manager));
		Workout result;

		result = workoutRepository.save(workout);

		return result;
	}

	public void delete(Workout workout) {
		Assert.notNull(workout);

		Manager manager = managerService.findByPrincipal();

		Assert.isTrue(workout.getGym().getManager().equals(manager));

		for (Step s : workout.getSteps()) {
			stepService.delete(s);
		}

		for (Annotation a : workout.getAnnotations()) {
			annotationService.delete(a);
		}

		workoutRepository.delete(workout);
	}

	public Workout findOne(int workoutId) {
		// TODO Auto-generated method stub
		Workout workout;

		workout = workoutRepository.findOne(workoutId);

		return workout;
	}

	public Collection<Workout> findAll() {
		// TODO Auto-generated method stub
		Collection<Workout> workouts;

		workouts = workoutRepository.findAll();

		return workouts;
	}

	public Collection<Workout> findByGym(int gymId) {
		Collection<Workout> workouts;

		workouts = workoutRepository.findByGym(gymId);

		return workouts;
	}

	public Workout reconstruct(Workout workout, BindingResult binding) {
		Workout result;

		if (workout.getId() == 0) {
			result = workout;
			result.setGym(workout.getGym());
			result.setDescription(workout.getDescription());
			result.setTitle(workout.getTitle());
			validator.validate(result, binding);
		} else {
			Workout res = workoutRepository.findOne(workout.getId());
			result = workout;
			result.setDescription(workout.getDescription());
			result.setTitle(workout.getTitle());

			result.setAnnotations(res.getAnnotations());
			result.setGym(res.getGym());
			result.setId(res.getId());
			result.setVersion(res.getVersion());
			result.setSteps(res.getSteps());

			validator.validate(result, binding);
		}

		return result;
	}

	public Collection<Workout> findByKeyword(String keyword) {
		Collection<Workout> workouts = workoutRepository.findByKeyword(keyword);

		return workouts;
	}

	public Workout findByStep(int stepId) {
		Workout workout = workoutRepository.findByStep(stepId);

		return workout;
	}

	public Collection<Object[]> sixthQuery() {

		Collection<Object[]> sixth = workoutRepository.sixthQuery();

		return sixth;
	}

	public Collection<Workout> eighthQuery() {

		Collection<Workout> eighth = workoutRepository.eighthQuery();

		return eighth;
	}

	public Collection<Workout> findAllOpened() {

		Collection<Workout> findAllOpened = workoutRepository.findAllOpened();

		return findAllOpened;
	}

}
