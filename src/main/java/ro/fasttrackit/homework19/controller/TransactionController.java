package ro.fasttrackit.homework19.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework19.model.Transaction;
import ro.fasttrackit.homework19.model.TransactionType;
import ro.fasttrackit.homework19.service.TransactionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("{type}")
    public List<Transaction> getTransactionsType(@PathVariable TransactionType type) {
        return service.getTransactionsType(type);
    }

    @GetMapping("{id}")
    public Optional<Transaction> getTransactionById(@PathVariable int id) {
        return service.getTransactionById(id);
    }

    @GetMapping("/min")
    public List<Transaction> getMinAmountTransactions(@RequestParam double minAmount) {
        return service.getMinAmountTransactions(minAmount);
    }

    @GetMapping("/max")
    public List<Transaction> getMaxAmountTransactions(@RequestParam double maxAmount) {
        return service.getMaxAmountTransactions(maxAmount);
    }

    @PostMapping
    public Transaction newTransaction(@RequestBody Transaction transaction) {
        return service.newTransaction(transaction);
    }

    @PutMapping("{id}")
    public Optional<Transaction> replaceTransaction(
            @PathVariable int id,
            @RequestBody Transaction transaction) {
        return service.replaceTransaction(id, transaction);
    }

    @DeleteMapping("{id}")
    public Optional<Transaction> deleteTransaction(@PathVariable int id) {
        return service.deleteTransaction(id);
    }

    @GetMapping("/type-min")
    public List<Transaction> findByTypeAndAmountGreaterThan(
            @RequestParam TransactionType type,
            @RequestParam double minAmount) {
        return service.findByTypeAndAmountGreaterThan(type, minAmount);
    }

    @GetMapping("/type-max")
    public List<Transaction> findByTypeAndAmountLessThan(
            @RequestParam TransactionType type,
            @RequestParam double maxAmount) {
        return service.findByTypeAndAmountLessThan(type, maxAmount);
    }

    @GetMapping("/min-max")
    public List<Transaction> findByAmountBetween(
            @RequestParam double minAmount,
            @RequestParam double maxAmount) {
        return service.findByAmountBetween(minAmount, maxAmount);
    }

    @GetMapping("/type-min-max")
    public List<Transaction> findByTypeAndAmountBetween(
            @RequestParam TransactionType type,
            @RequestParam double minAmount,
            @RequestParam double maxAmount) {
        return service.findByTypeAndAmountBetween(type, minAmount, maxAmount);
    }

    // Reports
    @GetMapping("/reports/product")
    public Map<String, List<String>> getProductReports() {
        return service.getProductReports();
    }

    @GetMapping("/reports/type")
    public Map<TransactionType, List<String>> getTypeReports() {
        return service.getTypeReports();
    }
}
