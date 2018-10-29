package me.markakis.demo.statementvalidator.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.validator.routines.IBANValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.markakis.demo.statementvalidator.pojos.FailedRecord;
import me.markakis.demo.statementvalidator.pojos.Record;
import me.markakis.demo.statementvalidator.pojos.Transaction;
import me.markakis.demo.statementvalidator.utils.patterns.RecordAdapter;

/**
 * Implements methods to validate statement records.
 * 
 * @author marqui
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    private static final Logger       logger            = LoggerFactory.getLogger(ValidationService.class);

    // Concurrent Set that stores every reference number has been checked.
    // Used instead of an in-memory database.
    private static final Set<Integer> REFERENCE_NUMBERS = ConcurrentHashMap.newKeySet();

    @Override
    public List<FailedRecord> validateTransactions(List<Record> records) {
        List<FailedRecord> failed = new ArrayList<>();

        for (Record record : records) {
            Transaction transaction = null;

            try {
                // tries to convert record to transaction
                transaction = new RecordAdapter(record).toTransaction();
            } catch (Exception e) {
                // if anything breaks, means that that record failed
                logger.error(e.getMessage());
                failed.add(new FailedRecord(record.getReference(), record.getDescription()));
                continue;
            }

            Integer referenceNumber = transaction.getReference();
            if (REFERENCE_NUMBERS.contains(referenceNumber)
                    || !IBANValidator.getInstance().isValid(transaction.getAccountNumber())
                    || BigDecimal.ZERO.compareTo(transaction.getStartBalance()) > 1 || !isBalanceCorrect(transaction)) {
                failed.add(new FailedRecord(record.getReference(), record.getDescription()));
            }
            REFERENCE_NUMBERS.add(referenceNumber);
        }

        return failed;
    }

    /**
     * Checks whether end balance is correct or not.
     * 
     * @param transaction
     *            the transaction to be checked for its end balance.
     * @return <code>true</code> if end balance is correct, <code>false</code>
     *         otherwise.
     */
    private boolean isBalanceCorrect(Transaction transaction) {
        return transaction.getStartBalance().add(transaction.getMutation()).stripTrailingZeros()
                .equals(transaction.getEndBalance().stripTrailingZeros());
    }

}
