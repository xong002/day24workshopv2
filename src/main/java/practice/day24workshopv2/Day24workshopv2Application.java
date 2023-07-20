package practice.day24workshopv2;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import practice.day24workshopv2.model.Details;
import practice.day24workshopv2.model.Order;
import practice.day24workshopv2.repository.OrderDetailsRepository;
import practice.day24workshopv2.repository.OrderRepository;

@SpringBootApplication
public class Day24workshopv2Application implements CommandLineRunner{

	@Autowired
	private OrderRepository orderRepository;

	@Autowired OrderDetailsRepository detailsRepo;
	public static void main(String[] args) {
		SpringApplication.run(Day24workshopv2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Order order = new Order();
		// order.setOrderDate(Date.valueOf(LocalDate.now()));
		// order.setCustomerName("test customer name");
		// order.setShipAddress("test ship address");
		// order.setNotes("test notes");
		// order.setTax(0.2f);
		// Integer orderId = orderRepository.insertOrder(order);
		// System.out.println(orderId);
		
		Order orderResult = orderRepository.findOrderById(3);
		System.out.println(orderResult.toString());

		// Details details = new Details(null, "test product", 10.0f, 0.2f, 5, 3);
		// Integer detailsResult = detailsRepo.insertDetails(details);
		// System.out.println(detailsResult.toString());

		Details details1 = new Details(null, "test product1", 10.0f, 0.2f, 5, 3);
		Details details2 = new Details(null, "test product2", 1.0f, 0.2f, 2, 3);
		Details details3 = new Details(null, "test product3", 50.0f, 0.2f, 4, 3);
		List<Details> detailsList = new LinkedList<>();
		detailsList.add(details1);
		detailsList.add(details2);
		detailsList.add(details3);

		int[] resultArr = detailsRepo.batchInsertDetails(detailsList);
		for (int i : resultArr){
			System.out.println(i);
		}

	}



}
