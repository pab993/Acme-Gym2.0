
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "cancelled"), @Index(columnList = "gym_id"), @Index(columnList = "title"), @Index(columnList = "description"), @Index(columnList = "day"), @Index(columnList = "endTime")
})
public class Activity extends DomainEntity {

	//Attributes
	// ===============================================================

	private String				title;
	private String				description;
	private Collection<String>	pictures;
	private String				day;
	private String				startTime;
	private String				endTime;
	private Boolean				cancelled;
	private int					availableSeats;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Pattern(regexp = "^[0-2]{1}[0-9]{1}[:][0-5]{1}[0-9]{1}$")
	//Hay que comprobar que si se ha establecido un 2 en la primera hora los numeros siguientes permitidos son 0-4
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Pattern(regexp = "^[0-2]{1}[0-9]{1}[:][0-5]{1}[0-9]{1}$")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@NotNull
	@Min(1)
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Boolean getCancelled() {
		return cancelled;
	}
	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}


	//Relationships
	// =======================================================

	private Collection<Trainer>		trainers;
	private Collection<Customer>	customers;
	private Gym						gym;


	@ManyToMany
	public Collection<Trainer> getTrainers() {
		return trainers;
	}
	public void setTrainers(Collection<Trainer> trainers) {
		this.trainers = trainers;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}

	@ManyToMany
	public Collection<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

}
