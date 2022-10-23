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
		Account account = new Account(123, "any street number","myemail@gmail.com", l);
		when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
		assertThat(service.findById(account.getId())).isEqualTo(account);
	}

	@Test
	void should_throw_exception() {
		List<String> l = new ArrayList<>();
		l.add("visa");
		Account account = new Account(123, "any street number","myemail@gmail.com", l);
		when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(null));
		assertThatThrownBy(() -> service.findById(account.getId()))
				.isInstanceOf(RuntimeException.class)
				.hasMessage("Cannot find reservation: " + account.getId());

	}

	@Test
	void should_return_all(){
		when(accountRepository.findAll()).thenReturn(List.of());
		assertThat(service.findAll()).isNotNull();
	}

//	@Test
//	void should_save(){
//		List<String> l = new ArrayList<>();
//		l.add("visa");
//		Account account = new Account(223124, "any street number","myemail@gmail.com", l);
//		service.save(account);
//		assertThat(account.getId()).isEqualTo(223124);
//		assertThat(account.getEmail()).isEqualTo("myemail@gmail.com");
//		assertThat(account.getAddress()).isEqualTo("any street number");
//
//
//	}
}
