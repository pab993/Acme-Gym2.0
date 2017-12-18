
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AnnotationRepository;
import domain.Actor;
import domain.Annotation;
import domain.Workout;

@Service
@Transactional
public class AnnotationService {

	//Managed Repository =============================================================================

	@Autowired
	private AnnotationRepository	annotationRepository;

	//Services
	// ===============================================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private WorkoutService			workoutService;

	@Autowired
	private Validator				validator;


	//SCRUDs Methods
	//===============================================================================================

	public Annotation create(int workoutId) {
		Annotation result;

		Workout workout = workoutService.findOne(workoutId);

		result = new Annotation();

		result.setWorkout(workout);

		return result;
	}

	public Annotation save(Annotation annotation) {
		Assert.notNull(annotation);

		Annotation result;

		result = annotationRepository.save(annotation);

		return result;
	}

	public void delete(Annotation annotation) {
		Assert.notNull(annotation);

		annotationRepository.delete(annotation);

	}

	public Annotation findOne(int annotationId) {
		// TODO Auto-generated method stub
		Annotation Annotation;

		Annotation = annotationRepository.findOne(annotationId);

		return Annotation;
	}

	public Collection<Annotation> findAll() {
		// TODO Auto-generated method stub
		Collection<Annotation> annotations;

		annotations = annotationRepository.findAll();

		return annotations;
	}

	//Other bussiness methods
	//=============================================================================

	public Annotation reconstruct(Annotation annotation, BindingResult binding) {
		Annotation result;

		result = annotation;
		result.setText(annotation.getText());
		Actor actor = actorService.findByPrincipal();
		result.setActor(actor);
		result.setWorkout(annotation.getWorkout());

		validator.validate(result, binding);

		return result;
	}

}
