
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "gym_id"), @Index(columnList = "title"), @Index(columnList = "description")
})
public class Workout extends DomainEntity {

	//Attributes
	// ===============================================================

	private String	title;
	private String	description;


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


	//Relationships
	// ================================================================

	private Collection<Step>		steps;
	private Gym						gym;
	private Collection<Annotation>	annotations;


	@OneToMany(mappedBy = "workout")
	public Collection<Step> getSteps() {
		return steps;
	}
	public void setSteps(Collection<Step> steps) {
		this.steps = steps;
	}

	@Valid
	@ManyToOne(optional = false)
	public Gym getGym() {
		return gym;
	}
	public void setGym(Gym gym) {
		this.gym = gym;
	}

	@OneToMany(mappedBy = "workout")
	public Collection<Annotation> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(Collection<Annotation> annotations) {
		this.annotations = annotations;
	}

}
