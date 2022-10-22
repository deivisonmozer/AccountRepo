package edu.miu.sa.reservation;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
class ReservationServiceApplicationTests {
	@Autowired
	private AccountRepository repository;

	@Autowired
	private KafkaTemplate<String, Object> template;

	@Value("${kafka.topic.get}")
	private String topicGet;

	@TestConfiguration
	void test() {
		List<String> l = new ArrayList<String>();
		l.add("visa");
		l.add("master");

		Account a = new Account(123, "email@mail.com","Street",l);
		repository.save(a);

		Account saved = repository.findById(a.getId()).get();
		assertEquals(saved, a);
	}

	@Test
	void test2(){
		List<String> list = new ArrayList<String>();
		Account acc = new Account(123,"value@mail.com", "", list); //
		template.send(topicGet, acc);

	}

}
