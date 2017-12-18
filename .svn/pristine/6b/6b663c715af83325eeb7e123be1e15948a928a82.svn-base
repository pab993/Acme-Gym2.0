
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
import domain.Actor;
import domain.Annotation;
import domain.Workout;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class AnnotationServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private AnnotationService	annotationService;

	@Autowired
	private WorkoutService		workoutService;

	@Autowired
	private ActorService		actorService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of an annotation, the system must check its text and who wrote it.
	 * 
	 * En este test, se comprueba el texto, así como el usuario que está logueado que intenta guardar el la annotation.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	public void editAnnotationTest(final String username, String text, Integer workoutId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Annotation result;
			result = annotationService.create(workoutId);

			Actor actor = actorService.findByPrincipal();
			result.setActor(actor);

			Assert.notNull(text);
			Assert.notNull(result.getWorkout());
			Assert.notNull(result.getActor());

			annotationService.save(result);
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

	public void listOfAnnotationsTest(final String username, Integer workoutId, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Workout workout = workoutService.findOne(workoutId);
			for (Annotation a : workout.getAnnotations()) {
				System.out.print(a.getText());
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
	public void driverListOfAnnotationsTest() {

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
			this.listOfAnnotationsTest((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverEditAnnotationTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, "testTitle1", 26, IllegalArgumentException.class
			},
			// Un cliente logueado -> true
			{
				"customer1", "testTitle2", 26, null
			},
			// Un manager logueado -> true
			{
				"manager1", "testTitle3", 26, null
			},
			// Todo vacio -> false
			{
				"manager1", null, 26, IllegalArgumentException.class
			},
			// Un entrenador logueado -> true
			{
				"trainer1", "testTitle4", 26, null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editAnnotationTest((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (Class<?>) testingData[i][3]);
	}

}
