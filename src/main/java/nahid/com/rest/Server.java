package nahid.com.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



/**
 * Main class to run the project
 * @author Nahid
 * 
 */
@SpringBootApplication
@EntityScan("nahid.com.rest")
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
		
		
	}
}
