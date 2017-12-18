
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

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Activity;
import domain.Customer;
import domain.Gym;
import forms.CustomerForm;

@Service
@Transactional
public class CustomerService {

	//Managed Repository =============================================================================

	@Autowired
	private CustomerRepository	customerRepository;


	//Simple CRUD methods ============================================================================

	public Customer create() {
		Customer result;
		UserAccount userAccount;
		Authority authority;
		Collection<Gym> gyms;
		Collection<Activity> activities;

		authority = new Authority();
		userAccount = new UserAccount();
		gyms = new ArrayList<Gym>();
		activities = new ArrayList<Activity>();

		authority.setAuthority("CUSTOMER");
		userAccount.addAuthority(authority);

		result = new Customer();

		result.setUserAccount(userAccount);
		result.setGyms(gyms);
		result.setActivities(activities);

		return result;
	}

	public Customer save(Customer customer) {
		Assert.notNull(customer);
		Assert.notNull(customer.getUserAccount());
		Customer result;

		result = customerRepository.save(customer);

		return result;
	}

	//Other Business Methods =========================================================================

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Customer findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Customer result;

		result = customerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Customer reconstruct(CustomerForm customerForm, BindingResult binding) {

		Customer result;

		result = create();
		result.getUserAccount().setUsername(customerForm.getUsername());
		result.setName(customerForm.getName());
		result.setSurname(customerForm.getSurname());
		result.setPhone(customerForm.getPhone());
		result.setEmail(customerForm.getEmail());
		result.setPostalAddress(customerForm.getPostalAddress());
		result.setCity(customerForm.getCity());
		result.setCountry(customerForm.getCountry());
		result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(customerForm.getPassword(), null));
		result.getUserAccount().setEnabled(true);

		comprobarContrasena(customerForm.getPassword(), customerForm.getRepeatPassword(), binding);
		comprobarPostalAddress(customerForm.getPostalAddress(), binding);

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
			codigos[0] = "customer.password.mismatch";
			error = new FieldError("customerForm", "password", password, false, codigos, null, "");
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
				codigos[0] = "customer.postalAddress.error";
				error = new FieldError("customerForm", "postalAddress", postalAddress, false, codigos, null, "");
				binding.addError(error);
			}
		}

		return result;
	}

	public Collection<Object[]> thirstQuery() {
		Collection<Object[]> thirstQuery;

		thirstQuery = customerRepository.thirstQuery();

		return thirstQuery;
	}

	public Collection<Customer> fifthQuery() {
		Collection<Customer> fifthQuery;

		fifthQuery = customerRepository.fifthQuery();

		return fifthQuery;
	}
}
