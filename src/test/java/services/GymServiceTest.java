
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

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class GymServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private GymService		gymService;

	@Autowired
	private ManagerService	managerService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a gym, the system must check its logo, its name, its address and its fee.
	 * 
	 * En este test, se comprueba el logo, el nombre, la dirección y la cuota de un nuevo gymnasio.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Browse the list of gyms who have registered to the system.
	 * 
	 * En este caso de uso se comprobamos que cualquiera puede listar los gymnasios que existen en nuestra aplicación.
	 */

	public void listOfGymsTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			gymService.findAll();

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit an gym or create a new one.
	 * 
	 * En este caso de uso una compañía puede crear/editar una nueva oferta.
	 */

	public void editGymTest(final String username, String logo, String name, String address, Double fee, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Gym result;

			result = gymService.create();

			Manager manager = managerService.findByPrincipal();

			Assert.notNull(logo);
			Assert.notNull(name);
			Assert.notNull(address);
			Assert.notNull(fee);
			Assert.isTrue(fee >= 0);

			result.setLogo(logo);
			result.setName(name);
			result.setAddress(address);
			result.setFee(fee);
			result.setClosed(false);
			result.setManager(manager);

			gymService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	//Drivers
	// ===================================================

	@Test
	public void driverListgymTest() {

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
			this.listOfGymsTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void driverEditgymTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, "testLogo1", "testName1", "addressTest", 20.0, IllegalArgumentException.class
			},
			// Alguien que no es un manager -> false
			{
				"cliente1", "testLogo1", "testName1", "addressTest", 20.0, IllegalArgumentException.class
			},
			// Todo correcto -> true
			{
				"manager1", "testLogo1", "testName1", "addressTest", 20.0, null
			},
			// Todo vacio -> false
			{
				"manager1", null, null, null, null, IllegalArgumentException.class
			},
			// La cuota en su mínimo valor (0) -> true
			{
				"manager1", "testLogo1", "testName1", "addressTest", 0.0, null
			},
			// La cuota por debajo de su mínimo valor -> false
			{
				"manager1", "testLogo1", "testName1", "addressTest", -1.0, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editGymTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Double) testingData[i][4], (Class<?>) testingData[i][5]);
	}

}
