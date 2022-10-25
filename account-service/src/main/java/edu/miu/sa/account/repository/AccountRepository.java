package edu.miu.sa.account.repository;

import edu.miu.sa.account.entity.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CassandraRepository<Account, Integer> {
}
