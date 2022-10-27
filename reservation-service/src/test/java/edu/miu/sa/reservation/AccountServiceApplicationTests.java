package edu.miu.sa.reservation;

import edu.miu.sa.reservation.entity.Account;
import edu.miu.sa.reservation.repository.AccountRepository;
import edu.miu.sa.reservation.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceApplicationTests {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountService service;

	@Test
	void should_return_reservation() {
		List<String> l = new ArrayList<>();
		l.add("visa");
		l.add("master");
		l.add("paypal");
		Account account = new Account("any street number","myemail@gmail.com", l);
		when(accountRepository.findByEmail(account.getEmail())).thenReturn(Optional.of(account));
		assertThat(service.findByEmail(account.getEmail())).isEqualTo(Optional.of(account));
	}

	@Test
	void should_return_all(){
		when(accountRepository.findAll()).thenReturn(List.of());
		assertThat(service.findAll()).isNotNull();
	}
}
