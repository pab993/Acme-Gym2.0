
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	//Relationships
	// =======================================================

	private Collection<Gym>	gyms;


	@OneToMany(mappedBy = "manager")
	public Collection<Gym> getGyms() {
		return gyms;
	}

	public void setGyms(Collection<Gym> gyms) {
		this.gyms = gyms;
	}

}
