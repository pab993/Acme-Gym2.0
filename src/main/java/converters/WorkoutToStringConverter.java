
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Workout;

@Component
@Transactional
public class WorkoutToStringConverter implements Converter<Workout, String> {

	@Override
	public String convert(Workout workout) {

		String result;
		if (workout == null) {
			result = null;
		} else {
			result = String.valueOf(workout.getId());
		}
		return result;
	}

}
