
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnnotationService;
import services.ManagerService;
import domain.Actor;
import domain.Annotation;
import domain.Manager;

@Controller
@RequestMapping("/annotation")
public class AnnotationController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AnnotationController() {
		super();
	}


	//Services
	// ============================================================================

	@Autowired
	private AnnotationService	annotationService;

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private ActorService		actorService;


	//Edition
	// ============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Annotation annotationPruned, BindingResult binding) {
		ModelAndView result;

		Annotation annotation = annotationService.reconstruct(annotationPruned, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(annotation);
		} else {
			try {

				annotationService.save(annotation);
				Actor actor = actorService.findByPrincipal();
				if (annotation.getWorkout().getGym().getManager().getId() == actor.getId()) {
					result = new ModelAndView("redirect:/workout/detailsLogin.do?workoutId=" + annotation.getWorkout().getId());
				} else {
					result = new ModelAndView("redirect:/workout/details.do?workoutId=" + annotation.getWorkout().getId());
				}

			} catch (Throwable oops) {

				result = createEditModelAndView(annotation, "annotation.commit.error");
			}
		}
		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(Annotation annotation) {
		ModelAndView result;

		result = createEditModelAndView(annotation, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Annotation annotation, String message) {
		ModelAndView result;

		result = new ModelAndView("workout/details");

		if (annotation.getActor().getId() == annotation.getWorkout().getGym().getManager().getId()) {
			Manager principal = managerService.findByPrincipal();

			result.addObject("principal", principal);
			result.addObject("workout", annotation.getWorkout());
			result.addObject("steps", annotation.getWorkout().getSteps());
			result.addObject("annotations", annotation.getWorkout().getAnnotations());

			result.addObject("annotation", annotation);
			result.addObject("message", message);
		} else {
			result.addObject("workout", annotation.getWorkout());
			result.addObject("steps", annotation.getWorkout().getSteps());
			result.addObject("annotations", annotation.getWorkout().getAnnotations());

			result.addObject("annotation", annotation);
			result.addObject("message", message);
		}

		return result;
	}

}
