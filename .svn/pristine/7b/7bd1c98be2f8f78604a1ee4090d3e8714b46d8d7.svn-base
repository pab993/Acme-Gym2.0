
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Manager;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ManagerServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ManagerService	managerService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a new manager in our system, the system must check the username,
	 * the passwords, the name, the surname, the phone, the email, the postal address, the city and the country.
	 * 
	 * En este test, se comprueba el registro de un nuevo manager.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Register a new manager.
	 * 
	 * En este caso de uso simularemos el registro de un manager.
	 */

	public void managerRegisterTest(String username, String password, String passwordRepeat, String name, String surname, String phone, String email, String postalAddress, String city, String country, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Manager result = managerService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			Assert.notNull(passwordRepeat);
			Assert.isTrue(password.equals(passwordRepeat));
			Assert.notNull(phone);
			Assert.isTrue(phone.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"));
			Assert.notNull(email);
			Assert.notNull(name);
			Assert.notNull(surname);

			result.getUserAccount().setUsername(username);
			result.setName(name);
			result.setSurname(surname);
			result.setPhone(phone);
			result.setEmail(email);
			result.setPostalAddress(postalAddress);
			result.setCity(city);
			result.setCountry(country);
			result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

			managerService.save(result);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverManagerRegisterTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				"managerTest", "managerTest", "managerTest", "managerTestName", "managerTestSurname", "+ES1234456", "managerTest@managerTest.com", "12345", "Sevilla", "España", null
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, null, null, null, null, null, IllegalArgumentException.class
			},
			// Las contraseñas no coinciden -> false
			{
				"managerTest", "managerTest", "12345", "managerTestName", "managerTestSurname", "+ES1234456", "managerTest@managerTest.com", "12345", "Sevilla", "España", IllegalArgumentException.class
			},
			// Todos los campos completados, excepto la direccion postal, la ciudad y el país -> true
			{
				"managerTest", "managerTest", "managerTest", "managerTestName", "managerTestSurname", "+ES1234456", "managerTest@managerTest.com", "", "", "", null
			},
			// Patrón del telefono erroneo -> false
			{
				"managerTest", "managerTest", "managerTest", "managerTestName", "managerTestSurname", "1234456", "managerTest@managerTest.com", "12345", "Sevilla", "España", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.managerRegisterTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (String) testingData[i][9], (Class<?>) testingData[i][10]);
	}
}
