
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
import domain.Gym;
import domain.Manager;
import domain.Workout;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class WorkoutServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private WorkoutService	workoutService;

	@Autowired
	private GymService		gymService;

	@Autowired
	private ManagerService	managerService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a workout, the system must check its title and its description.
	 * 
	 * En este test, se comprueba el titulo y la descripcion, así como el usuario que está logueado que intenta guardar el workout.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	public void editWorkoutTest(final String username, String title, String description, Integer gymId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Workout result;
			Gym gym = gymService.findOne(gymId);
			result = workoutService.create(gymId);

			Manager manager = managerService.findByPrincipal();

			Assert.notNull(title);
			Assert.notNull(description);
			Assert.notNull(gym);
			Assert.isTrue(gym.getManager().equals(manager));

			result.setTitle(title);
			result.setDescription(description);
			result.setGym(gym);

			workoutService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Browse the list of workouts.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los workouts que existen en nuestra aplicación.
	 */

	public void listOfWorkoutsTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			workoutService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverListOfWorkoutsTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				null, null
			},
			// Un manager -> true
			{
				"manager1", null
			},
			// Un cliente -> true
			{
				"customer1", null
			},
			// Una entrenador -> true
			{
				"trainer1", null
			},
			// Un administrador -> true
			{
				"admin", null
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listOfWorkoutsTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverEditWorkoutTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, "testTitle1", "testDescription1", 21, IllegalArgumentException.class
			},
			// Alguien que no es un manager -> false
			{
				"customer1", "testTitle2", "testDescription2", 21, IllegalArgumentException.class
			},
			// Todo correcto -> true
			{
				"manager1", "testTitle3", "testDescription3", 21, null
			},
			// Todo vacio -> false
			{
				"manager1", null, null, 21, IllegalArgumentException.class
			},
			// El gimnasio no es de ese manager -> false
			{
				"manager1", "testTitle4", "testDescription4", 22, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editWorkoutTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Integer) testingData[i][3], (Class<?>) testingData[i][4]);
	}

}
