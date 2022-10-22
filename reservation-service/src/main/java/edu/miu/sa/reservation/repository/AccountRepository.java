package edu.miu.sa.reservation.repository;

import edu.miu.sa.reservation.entity.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CassandraRepository<Account, Integer> {
}
