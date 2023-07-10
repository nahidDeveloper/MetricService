package nahid.com.rest;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Metric Controller class to handle all API endpoints for Metrics
 * @author Nahid
 *
 */
@RestController
@RequestMapping("/metrics")
public class MetricController {
	 private final MetricRepository metricRepository;
	 private static final Logger logger = LogManager.getLogger(MetricController.class);

	    @Autowired
	    public MetricController(MetricRepository metricRepository) {
	        this.metricRepository = metricRepository;
	    }
	    
	    //Updating a specific Metric given their ID
	    @PutMapping("/{id}")
	    public ResponseEntity<?>updateMetric(@PathVariable Long id, @RequestBody Metric updatedMetric){
	    	//This Long if statement can be optimised
	    	if(updatedMetric.getSystem()==null || updatedMetric.getSystem().isEmpty()||
	    			updatedMetric.getName()==null || updatedMetric.getName().isEmpty()||
	    			updatedMetric.getDate()==null) {
	    		logger.error("Bad request, illgal parameters in request");
	    		return ResponseEntity.badRequest().build();
	    	}
	    	
	    	 Optional<Metric> optionalMetric = metricRepository.findById(id);

	         if (optionalMetric.isEmpty()) {
	        	 logger.error("Metric does not exist");
	        	 throw new MetricNotFoundException("Metric not found");
	         }
	         //Metric with ID exists so we can update Values
	         Metric metric = optionalMetric.get();
	         metric.setSystem(updatedMetric.getSystem());
	         metric.setName(updatedMetric.getName());
	         metric.setDate(updatedMetric.getDate());

	         if (updatedMetric.getValue() != null) {
	             metric.setValue(updatedMetric.getValue());
	             logger.info("Metric changed to set value");
	         } else {
	             metric.setValue(metric.getValue() + 1);
	             logger.info("Metric Value not set so incremented by 1");
	         }

	         Metric newlyUpdatedMetric = metricRepository.save(metric);
	         logger.info("Metric updated");
	         return ResponseEntity.ok(newlyUpdatedMetric);
	    	
	    }
	    
	    //Getting a specific Metric based on ID
	    @GetMapping("/{id}")
	    public ResponseEntity<?>getMetric(@PathVariable Long id){
	    	Optional<Metric> optionalMetric = metricRepository.findById(id);
	        
	        if (optionalMetric.isEmpty()) {
	        	logger.error("Metric does not exist");
	        	throw new MetricNotFoundException("Metric not found");
	        }
	        
	        Metric metric = optionalMetric.get();
	        logger.info("Metric Found");
	        return ResponseEntity.ok(metric);
	    }
	    
	    //Returns List of All metrics
	    @GetMapping
	    public ResponseEntity<?> getAllMetrics(
	    		@RequestParam("system") String system,
	            @RequestParam(value = "name", required = false) String name,
	            @RequestParam(value = "from", required = false) Instant from,
	            @RequestParam(value = "to", required = false) Instant to	) 
	    {	
	    	 if (system.isEmpty()) {
	    	        return ResponseEntity.badRequest().body("System parameter is required");
	    	    }
	    	List<Metric> metrics;
	    	if (name != null && from != null && to != null) {
	            // Filter by system, name, from, and to
	            metrics = metricRepository.findBySystemAndNameAndDateBetween(system, name, from, to);
	        } else if (name != null && from != null) {
	            // Filter by system, name, and from
	            metrics = metricRepository.findBySystemAndNameAndDateAfter(system, name, from);
	        } else if (name != null && to != null) {
	            // Filter by system, name, and to
	            metrics = metricRepository.findBySystemAndNameAndDateBefore(system, name, to);
	        } else if (from != null && to != null) {
	            // Filter by system, from, and to
	            metrics = metricRepository.findBySystemAndDateBetween(system, from, to);
	        } else if (name != null) {
	            // Filter by system and name
	            metrics = metricRepository.findBySystemAndName(system, name);
	        } else if (from != null) {
	            // Filter by system and from
	            metrics = metricRepository.findBySystemAndDateAfter(system, from);
	        } else if (to != null) {
	            // Filter by system and to
	            metrics = metricRepository.findBySystemAndDateBefore(system, to);
	        } else {
	            // No filtering parameters provided, retrieve all metrics for the system
	            metrics = metricRepository.findBySystem(system);
	        }
	    	 logger.info("Metric(s) Found");
	        return ResponseEntity.ok(metrics);
	    	
	    }
	    //Create Metric
	    @PostMapping
	    public ResponseEntity<?> createMetric(@RequestBody Metric metric) {
	    	if(metric.getSystem()==null || metric.getSystem().isEmpty()||
	    			metric.getName()==null || metric.getName().isEmpty()) {
	    		logger.error("Bad request, illgal parameters in request");
	    		return ResponseEntity.badRequest().build();
	    	}
	    	metric.setValue(1);
	    	metric.setDate(Instant.now());
	        Metric createdMetric = metricRepository.save(metric);
	        logger.info("New Metric saved");
	        return ResponseEntity.ok(createdMetric);

	    }

}
