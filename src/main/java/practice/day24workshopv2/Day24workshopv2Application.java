package practice.day24workshopv2;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import practice.day24workshopv2.model.Order;
import practice.day24workshopv2.repository.OrderRepository;

@SpringBootApplication
public class Day24workshopv2Application implements CommandLineRunner{

	@Autowired
	private OrderRepository orderRepository;
	public static void main(String[] args) {
		SpringApplication.run(Day24workshopv2Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Order order = new Order();
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		order.setCustomerName("test customer name");
		order.setShipAddress("test ship address");
		order.setNotes("test notes");
		order.setTax(0.2f);
		Integer orderId = orderRepository.insertOrder(order);
		System.out.println(orderId);
		System.exit(0);
	}

}
