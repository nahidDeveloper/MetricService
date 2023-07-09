package nahid.com.rest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface MetricRepository extends JpaRepository<Metric, Long> {
	List<Metric> findBySystem(String system);

	Optional<Metric> findMetricById(Long id);

	List<Metric> findBySystemAndName(String system, String name);

	List<Metric> findBySystemAndDateAfter(String system, Instant from);

	List<Metric> findBySystemAndDateBefore(String system, Instant to);

	List<Metric> findBySystemAndDateBetween(String system, Instant from, Instant to);

	List<Metric> findBySystemAndNameAndDateAfter(String system, String name, Instant from);

	List<Metric> findBySystemAndNameAndDateBefore(String system, String name, Instant to);

	List<Metric> findBySystemAndNameAndDateBetween(String system, String name, Instant from, Instant to);
}
