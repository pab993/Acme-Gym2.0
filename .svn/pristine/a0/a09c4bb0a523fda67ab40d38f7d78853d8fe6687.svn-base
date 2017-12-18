
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import domain.Manager;
import forms.ManagerForm;

@Controller
@RequestMapping("/manag")
public class ManagerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public ManagerController() {
		super();
	}


	//Services
	// ============================================================================

	@Autowired
	private ManagerService	managerService;


	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("manager/edit");

		result.addObject("managerForm", new ManagerForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ManagerForm managerForm, final BindingResult binding) {
		ModelAndView result;
		Manager manager;

		try {
			manager = managerService.reconstruct(managerForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(managerForm, "manager.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				manager = managerService.save(manager);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(managerForm, "manager.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(ManagerForm managerForm, String message) {

		ModelAndView resul = new ModelAndView("manager/edit");

		resul.addObject("managerForm", managerForm);
		resul.addObject("message", message);
		return resul;
	}
}
