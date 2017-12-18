
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import repositories.TrainerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Activity;
import domain.Manager;
import domain.Trainer;
import forms.TrainerForm;

@Service
@Transactional
public class TrainerService {

	//Managed Repository =============================================================================

	@Autowired
	private TrainerRepository	trainerRepository;

	@Autowired
	private ActivityService		activityService;

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private Validator			validator;


	//Simple CRUD methods ============================================================================

	public Trainer create() {
		Trainer result;
		UserAccount userAccount;
		Authority authority;
		Collection<Activity> activities;

		Manager manager = managerService.findByPrincipal();
		Assert.notNull(manager);

		authority = new Authority();
		userAccount = new UserAccount();
		activities = new ArrayList<Activity>();

		authority.setAuthority("TRAINER");
		userAccount.addAuthority(authority);

		result = new Trainer();

		result.setUserAccount(userAccount);
		result.setActivities(activities);

		return result;
	}

	public Trainer save(Trainer trainer) {
		Assert.notNull(trainer);
		Assert.notNull(trainer.getUserAccount());
		Trainer result;

		result = trainerRepository.save(trainer);

		return result;
	}

	public Trainer save2(Trainer trainer) {
		Assert.notNull(trainer);
		Assert.notNull(trainer.getUserAccount());
		Trainer result;

		result = findOne(trainer.getId());
		if (trainer.getGym() != result.getGym()) {
			for (Activity activity : result.getActivities()) {
				activity.getTrainers().remove(result);
				activityService.save2(activity);
			}
			Collection<Activity> activities = new ArrayList<Activity>();
			result.setActivities(activities);

		}

		result = trainerRepository.save(trainer);

		return result;
	}

	public Trainer save3(Trainer trainer) {
		Assert.notNull(trainer);
		Assert.notNull(trainer.getUserAccount());
		Trainer result;

		result = trainerRepository.save(trainer);

		return result;
	}

	public Collection<Trainer> findAll() {
		Collection<Trainer> trainers = trainerRepository.findAll();
		return trainers;
	}

	public Trainer findOne(int trainerId) {
		Trainer trainer = trainerRepository.findOne(trainerId);
		return trainer;
	}

	//Other Business Methods =========================================================================

	public Trainer findByPrincipal() {
		Trainer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Trainer findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Trainer result;

		result = trainerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Trainer reconstruct(TrainerForm trainerForm, BindingResult binding) {

		Trainer result;

		result = create();
		result.getUserAccount().setUsername(trainerForm.getUsername());
		result.setName(trainerForm.getName());
		result.setSurname(trainerForm.getSurname());
		result.setPhone(trainerForm.getPhone());
		result.setEmail(trainerForm.getEmail());
		result.setPostalAddress(trainerForm.getPostalAddress());
		result.setCity(trainerForm.getCity());
		result.setCountry(trainerForm.getCountry());
		//		result.setGym(trainerForm.getGym());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(trainerForm.getPassword(), null));
		result.getUserAccount().setEnabled(true);

		comprobarContrasena(trainerForm.getPassword(), trainerForm.getRepeatPassword(), binding);
		comprobarPostalAddress(trainerForm.getPostalAddress(), binding);

		return result;
	}

	private boolean comprobarContrasena(String password, String passwordRepeat, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordRepeat))
			result = password.equals(passwordRepeat);
		else
			result = false;

		if (!result) {
			codigos = new String[1];
			codigos[0] = "trainer.password.mismatch";
			error = new FieldError("trainerForm", "password", password, false, codigos, null, "");
			binding.addError(error);
		}

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
				codigos[0] = "trainer.postalAddress.error";
				error = new FieldError("trainerForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}
		}

		return result;
	}

	public Collection<Trainer> findTrainerByActivity(int activityId) {

		Collection<Trainer> trainers;

		trainers = trainerRepository.findTrainerByActivity(activityId);

		return trainers;

	}

	public Collection<Trainer> trainersByKeyWord(String keyword) {
		Collection<Trainer> trainers;

		trainers = trainerRepository.trainersByKeyWord(keyword);

		return trainers;
	}

	public Collection<Trainer> findTrainerOfAGym(int activityId) {
		Collection<Trainer> trainers;

		trainers = trainerRepository.findTrainerOfAGym(activityId);

		return trainers;
	}

	public Collection<Trainer> findTrainers(int gymId) {
		Collection<Trainer> trainers;

		trainers = trainerRepository.findTrainers(gymId);

		return trainers;
	}

	public Trainer reconstruct(Trainer trainer, BindingResult binding) {
		Trainer result = new Trainer();

		Trainer res = findOne(trainer.getId());

		result.setGym(trainer.getGym());
		result.setActivities(res.getActivities());
		result.setCity(res.getCity());
		result.setCountry(res.getCountry());
		result.setEmail(res.getEmail());
		result.setId(res.getId());
		result.setName(res.getName());
		result.setPhone(res.getPhone());
		result.setPostalAddress(res.getPostalAddress());
		result.setSurname(res.getSurname());
		result.setVersion(res.getVersion());
		result.setUserAccount(res.getUserAccount());
		validator.validate(result, binding);

		return result;
	}

}
