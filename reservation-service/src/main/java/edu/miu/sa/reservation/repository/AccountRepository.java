package edu.miu.sa.reservation.repository;

import edu.miu.sa.reservation.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Integer> {
}
