
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import forms.ActorForm;

@Service
@Transactional
public class ActorService {

	//Managed Repository =============================================================================

	@Autowired
	private ActorRepository	actorRepository;


	//Services
	// ===============================================================================================

	//SCRUDs Methods
	//===============================================================================================

	public Actor save(Actor actor) {
		Assert.notNull(actor);
		Assert.notNull(actor.getUserAccount());
		Actor result;

		Actor principal = findByPrincipal();
		Assert.notNull(principal);

		result = actorRepository.save(actor);

		return result;
	}

	//Other Business Methods =========================================================================

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Actor findOne(int actorId) {
		Actor result;

		result = actorRepository.findOne(actorId);

		return result;
	}

	public ActorForm reconstructToForm(Actor actor) {
		ActorForm actorForm = new ActorForm();

		actorForm.setName(actor.getName());
		actorForm.setSurname(actor.getSurname());
		actorForm.setPhone(actor.getPhone());
		actorForm.setEmail(actor.getEmail());
		actorForm.setPostalAddress(actor.getPostalAddress());
		actorForm.setCity(actor.getCity());
		actorForm.setCountry(actor.getCountry());

		return actorForm;

	}

	public Actor reconstruct1(ActorForm actorForm, BindingResult binding) {
		Actor result;

		result = findByPrincipal();

		comprobarPostalAddress(actorForm.getPostalAddress(), binding);

		return result;
	}

	//Hay dos reconstructs porque por alguna razón aquí se guardan los cambios en la base de datos en este metodo. Así que de esta manera hago un "rollback".
	public Actor reconstruct2(ActorForm actorForm, BindingResult binding) {
		Actor result;

		result = findByPrincipal();
		result.setName(actorForm.getName());
		result.setSurname(actorForm.getSurname());
		result.setPhone(actorForm.getPhone());
		result.setEmail(actorForm.getEmail());
		result.setPostalAddress(actorForm.getPostalAddress());
		result.setCity(actorForm.getCity());
		result.setCountry(actorForm.getCountry());

		comprobarPostalAddress(actorForm.getPostalAddress(), binding);

		return result;
	}

	private boolean comprobarPostalAddress(String postalAddress, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (postalAddress == null || postalAddress.isEmpty()) {
			result = true;
		} else {
			result = false;
		}

		if (!result) {
			if (postalAddress.matches("^[0-9]{5}$")) {
				result = true;
			} else {
				codigos = new String[1];
				codigos[0] = "actor.postalAddress.error";
				error = new FieldError("actorForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}
		}

		return result;
	}

}
