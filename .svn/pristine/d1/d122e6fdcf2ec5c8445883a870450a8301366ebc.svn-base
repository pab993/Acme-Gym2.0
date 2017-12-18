
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
import domain.Activity;
import domain.Gym;
import domain.Manager;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ActivityServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private GymService		gymService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	private ActivityService	activityService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an activity, the system must check its title, its description, its pictures, its day, its start time, its end time, and its availableSeats.
	 * 
	 * En este test, se comprueba el titulo, la descripcion, las imagenes, el día, el horario de inicio, el horario fin y el numero de sitios disponibles.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Browse the list of activities who have registered to the system.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar las actividades que existen en nuestra aplicación.
	 */

	public void listOfActivitiesTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			activityService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit an activitie or create a new one.
	 * 
	 * En este caso de uso una compañía puede crear/editar una nueva oferta.
	 */

	public void editActivityTest(final String username, String title, String description, String day, String startTime, String endTime, Integer availableSeats, int gymId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Activity result;
			Gym gym = gymService.findOne(gymId);

			result = activityService.create(gym);

			Manager manager = managerService.findByPrincipal();

			Assert.notNull(title);
			Assert.notNull(description);
			Assert.notNull(day);
			Assert.notNull(startTime);
			Assert.notNull(endTime);
			Assert.notNull(availableSeats);
			Assert.isTrue(availableSeats >= 1);
			Assert.notNull(gym);
			Assert.isTrue(gym.getManager().equals(manager));
			Assert.isTrue(day == "MONDAY" || day == "TUESDAY" || day == "WEDNESDAY" || day == "THRUSDAY" || day == "FRIDAY" || day == "SATURDAY" || day == "SUNDAY");

			result.setTitle(title);
			result.setDescription(description);
			result.setDay(day);
			result.setStartTime(startTime);
			result.setEndTime(endTime);
			result.setAvailableSeats(availableSeats);
			result.setCancelled(true);
			result.setGym(gym);

			activityService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	//Drivers
	// ===================================================

	@Test
	public void driverListActivityTest() {

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
			this.listOfActivitiesTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverEditActivityTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, "testTitle1", "testDescription1", "MONDAY", "17:00", "18:00", 10, 21, IllegalArgumentException.class
			},
			// Alguien que no es un manager -> false
			{
				"customer1", "testTitle2", "testDescription2", "MONDAY", "17:00", "18:00", 10, 21, IllegalArgumentException.class
			},
			// Todo correcto -> true
			{
				"manager1", "testTitle3", "testDescription3", "MONDAY", "17:00", "18:00", 10, 21, null
			},
			// Todo vacio -> false
			{
				"manager1", null, null, null, null, null, null, 21, IllegalArgumentException.class
			},
			// El número de sitios disponibles en su mínimo valor (1) -> true
			{
				"manager1", "testTitle4", "testDescription4", "MONDAY", "17:00", "18:00", 1, 21, null
			},
			// El numero de sitios disponibles por debajo de su mínimo valor -> false
			{
				"manager1", "testTitle5", "testDescription5", "MONDAY", "17:00", "18:00", -1, 21, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editActivityTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Integer) testingData[i][6], (int) testingData[i][7],
				(Class<?>) testingData[i][8]);
	}

}
