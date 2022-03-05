package ro.fasttrackit.homework19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.homework19.model.Transaction;
import ro.fasttrackit.homework19.model.TransactionType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByType(TransactionType type);

    List<Transaction> findByAmountGreaterThan(double minAmount);

    List<Transaction> findByAmountLessThan(double maxAmount);

    Optional<Transaction> deleteById(int id);

    List<Transaction> findByTypeAndAmountGreaterThan(TransactionType type, double minAmount);

    List<Transaction> findByTypeAndAmountLessThan(TransactionType type, double maxAmount);

    List<Transaction> findByAmountBetween(double minAmount, double maxAmount);

    List<Transaction> findByTypeAndAmountBetween(TransactionType type, double minAmount, double maxAmount);

}
