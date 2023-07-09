package nahid.com.rest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name="metricSummary")
public class MetricSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`system`")
    private String system;
    
    private String name;
    
    @Column(name = "from_date")
    private Instant fromDate;
    
    @Column(name = "to_date")
    private Instant toDate;
    
    private Integer value;

   

    public MetricSummary() {}

    public MetricSummary(String system, String name, Instant fromDate, Instant toDate, Integer value) {
        this.system = system;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.value = value;
    }

    // Getters and Setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getFromDate() {
        return fromDate;
    }

    public void setFromDate(Instant from) {
        this.fromDate = from;
    }

    public Instant getToDate() {
        return toDate;
    }

    public void setToDate(Instant to) {
        this.toDate = to;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}