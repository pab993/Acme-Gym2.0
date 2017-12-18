
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

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Gym;
import domain.Manager;
import forms.ManagerForm;

@Service
@Transactional
public class ManagerService {

	//Managed Repository =============================================================================

	@Autowired
	private ManagerRepository	managerRepository;


	//Simple CRUD methods ============================================================================

	public Manager create() {
		Manager result;
		UserAccount userAccount;
		Authority authority;
		Collection<Gym> gyms;

		authority = new Authority();
		userAccount = new UserAccount();
		gyms = new ArrayList<Gym>();

		authority.setAuthority("MANAGER");
		userAccount.addAuthority(authority);

		result = new Manager();

		result.setUserAccount(userAccount);
		result.setGyms(gyms);

		return result;
	}

	public Manager save(Manager manager) {
		Assert.notNull(manager);
		Assert.notNull(manager.getUserAccount());
		Manager result;

		result = managerRepository.save(manager);

		return result;
	}

	public Manager findOne(int managerId) {
		Assert.notNull(managerId);

		Manager manager = managerRepository.findOne(managerId);

		return manager;
	}

	public Collection<Manager> findAll() {

		Collection<Manager> managers = managerRepository.findAll();

		return managers;
	}

	//Other Business Methods =========================================================================

	public Manager findByPrincipal() {
		Manager result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Manager findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Manager result;

		result = managerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Manager reconstruct(ManagerForm managerForm, BindingResult binding) {

		Manager result;

		result = create();
		result.getUserAccount().setUsername(managerForm.getUsername());
		result.setName(managerForm.getName());
		result.setSurname(managerForm.getSurname());
		result.setPhone(managerForm.getPhone());
		result.setEmail(managerForm.getEmail());
		result.setPostalAddress(managerForm.getPostalAddress());
		result.setCity(managerForm.getCity());
		result.setCountry(managerForm.getCountry());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(managerForm.getPassword(), null));
		result.getUserAccount().setEnabled(true);

		comprobarContrasena(managerForm.getPassword(), managerForm.getRepeatPassword(), binding);
		comprobarPostalAddress(managerForm.getPostalAddress(), binding);

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
			codigos[0] = "manager.password.mismatch";
			error = new FieldError("managerForm", "password", password, false, codigos, null, "");
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
				codigos[0] = "manager.postalAddress.error";
				error = new FieldError("managerForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}
		}

		return result;
	}

	public void ban(int managerId) {
		Manager manager = findOne(managerId);
		Assert.notNull(manager);
		if (manager.getUserAccount().isEnabled())
			manager.getUserAccount().setEnabled(false);
		else
			manager.getUserAccount().setEnabled(true);

		this.save(manager);

	}

}
