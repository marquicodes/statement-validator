package me.markakis.demo.statementvalidator.utils.patterns;

import java.math.BigDecimal;

import me.markakis.demo.statementvalidator.pojos.Record;
import me.markakis.demo.statementvalidator.pojos.Transaction;

/**
 * Implements <code>TransactionAdapter</code> interface in order to be able to
 * convert <code>Record</code> objects into <code>Transaction</code> ones.
 * 
 * @author marqui
 */
public class RecordAdapter implements TransactionAdapter {

    private Record record;

    /**
     * Constructs a <code>RecordAdapter</code> instance by providing a
     * <code>Record</code> object.
     * 
     * @param record
     *            the <code>Record</code> object to instantiate this adapter.
     */
    public RecordAdapter(Record record) {
        super();
        this.record = record;
    }

    /*
     * (non-Javadoc)
     * @see
     * me.markakis.demo.statementvalidator.utils.patterns.TransactionAdapter#
     * toTransaction()
     */
    @Override
    public Transaction toTransaction() {
        Transaction transaction = new Transaction();

        try {
            transaction.setReference(Integer.valueOf(record.getReference()));
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Unable to parse reference '" + record.getReference() + "' as number.");
        }

        transaction.setAccountNumber(record.getAccountNumber());
        transaction.setDescription(record.getDescription());

        try {
            transaction.setStartBalance(new BigDecimal(record.getStartBalance()));
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(
                    "Start balance '" + record.getStartBalance() + "' is not valid representation of number.");
        }

        try {
            transaction.setMutation(new BigDecimal(record.getMutation()));
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(
                    "Mutation '" + record.getMutation() + "' is not valid representation of number.");
        }

        try {
            transaction.setEndBalance(new BigDecimal(record.getEndBalance()));
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(
                    "End balance '" + record.getEndBalance() + "' is not valid representation of number.");
        }

        return transaction;
    }

}
