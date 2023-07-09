package nahid.com.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricSummaryRepository extends JpaRepository<MetricSummary, Long> {
	List<MetricSummary> findBySystem(String system);
}
