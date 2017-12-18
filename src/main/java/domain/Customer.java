
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Relationships
	// =======================================================

	private Collection<Gym>			gyms;
	private Collection<Activity>	activities;


	@ManyToMany
	public Collection<Gym> getGyms() {
		return gyms;
	}

	public void setGyms(Collection<Gym> gyms) {
		this.gyms = gyms;
	}

	@ManyToMany
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

}
