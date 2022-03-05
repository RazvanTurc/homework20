package ro.fasttrackit.homework19.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.homework19.model.Transaction;
import ro.fasttrackit.homework19.model.TransactionType;
import ro.fasttrackit.homework19.repository.TransactionRepository;

import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public List<Transaction> getTransactionsType(TransactionType type) {
        return repository.findAllByType(type);
    }

    public List<Transaction> getMinAmountTransactions(double minAmount) {
        return repository.findByAmountGreaterThan(minAmount);
    }

    public List<Transaction> getMaxAmountTransactions(double maxAmount) {
        return repository.findByAmountLessThan(maxAmount);
    }

    public Optional<Transaction> getTransactionById(int id) {
        return repository.findById(id);
    }

    public Transaction newTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public Optional<Transaction> replaceTransaction(int id, Transaction transaction) {
        return findById(id)
                .map(existing -> replaceExistingTransaction(id, existing, transaction));
    }

    public Optional<Transaction> deleteTransaction(int id) {
        return repository.deleteById(id);
    }

    public List<Transaction> findByTypeAndAmountGreaterThan(TransactionType type, double minAmount) {
        return repository.findByTypeAndAmountGreaterThan(type, minAmount);
    }

    public List<Transaction> findByTypeAndAmountLessThan(TransactionType type, double maxAmount) {
        return repository.findByTypeAndAmountLessThan(type, maxAmount);
    }

    public List<Transaction> findByAmountBetween(double minAmount, double maxAmount) {
        return repository.findByAmountBetween(minAmount, maxAmount);
    }

    public List<Transaction> findByTypeAndAmountBetween(TransactionType type, double minAmount, double maxAmount) {
        return repository.findByTypeAndAmountBetween(type, minAmount, maxAmount);
    }

    private Transaction replaceExistingTransaction(int id, Transaction existing, Transaction transaction) {
        repository.delete(existing);
        Transaction newTransaction = cloneWithId(id, transaction);
        return repository.save(newTransaction);
    }

    public Optional<Transaction> findById(int id) {
        return repository.findById(id);
    }

    private Transaction cloneWithId(int id, Transaction transaction) {
        return new Transaction(
                id,
                transaction.getProduct(),
                transaction.getType(),
                transaction.getAmount()
        );
    }

    public Map<String, List<String>> getProductReports() {
        Map<String, List<String>> result = new HashMap<>();
        for(Transaction transaction : repository.findAll()) {
            List<String> products = result.computeIfAbsent(transaction.getProduct(), k -> new ArrayList<>());
            products.add("type: " + transaction.getType() + ", amount: " + transaction.getAmount());
        }
        return result;
    }

    public Map<TransactionType, List<String>> getTypeReports() {
        Map<TransactionType, List<String>> result = new HashMap<>();
        for(Transaction transaction : repository.findAll()) {
            List<String> products = result.computeIfAbsent(transaction.getType(), k -> new ArrayList<>());
            products.add("type: " + transaction.getType() + ", amount: " + transaction.getAmount());
        }
        return result;
    }
}
