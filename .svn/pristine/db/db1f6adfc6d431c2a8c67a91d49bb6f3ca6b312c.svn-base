
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Manager;
import domain.Step;
import domain.Workout;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class StepServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private WorkoutService	workoutService;

	@Autowired
	private StepService		stepService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a step, the system must check its number, its description and its link of the videotutorial.
	 * 
	 * En este test, se comprueba el número, la descripción y el link al videotutorial.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	public void editStepTest(final String username, int number, String description, String videoTutorial, Integer workoutId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Step result;
			Workout workout = workoutService.findOne(workoutId);
			Assert.notNull(workout);
			result = stepService.create(workout);

			Manager manager = managerService.findByPrincipal();

			Assert.isTrue(manager.equals(result.getWorkout().getGym().getManager()));
			Assert.notNull(number);
			Assert.notNull(description);
			Assert.notNull(result.getWorkout());
			Assert.notNull(videoTutorial);

			stepService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Browse the list of steps of a workout.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los steps de los workouts que existen en nuestra aplicación.
	 */

	public void listOfStepsTest(final String username, Integer workoutId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Workout workout = workoutService.findOne(workoutId);
			for (Step s : workout.getSteps()) {
				System.out.print(s.getDescription());
			}

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverListOfStepsTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				null, 26, null
			},
			// Un manager -> true
			{
				"manager1", 26, null
			},
			// Un cliente -> true
			{
				"customer1", 26, null
			},
			// Una entrenador -> true
			{
				"trainer1", 26, null
			},
			// Un administrador -> true
			{
				"admin", 26, null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfStepsTest((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverEditStepTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, 1, "description1", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 26, IllegalArgumentException.class
			},
			// Un cliente logueado -> false
			{
				"customer1", 1, "description2", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 26, IllegalArgumentException.class
			},
			// Todo vacio -> false
			{
				"manager1", 1, null, null, null, NullPointerException.class
			},
			// Un manager logueado propietario del workout -> true
			{
				"manager1", 1, "description3", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 26, null
			},
			// Un manager logueado que no es propietario del workout -> false
			{
				"manager2", 1, "description5", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 27, IllegalArgumentException.class
			},
			// Un cliente logueado que intenta editar un step -> false
			{
				"customer1", 1, "description5", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 27, IllegalArgumentException.class
			},

			// Un entrenador logueado que intenta editar un step -> false
			{
				"trainer1", 1, "description5", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 27, IllegalArgumentException.class
			},
			// Un manager logueado intenta editar un step inexistente -> false
			{
				"manager1", 1, "description3", "https://www.youtube.com/embed/Vfc0j64BkwI?ecver=2", 27446, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editStepTest((String) testingData[i][0], (int) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Integer) testingData[i][4], (Class<?>) testingData[i][5]);
	}

}
