package me.markakis.demo.statementvalidator.services;

import java.util.List;

import me.markakis.demo.statementvalidator.pojos.FailedRecord;
import me.markakis.demo.statementvalidator.pojos.Record;

/**
 * Defines methods to validate statement records.
 * 
 * @author marqui
 */
public interface ValidationService {

    /**
     * Receives a list of Transactions, validate them against their reference
     * number and their end balance. Returns a Map containing information of
     * each one of the failed records.
     * 
     * @param records
     *            a List of Record objects to be validated.
     * @return a List containing FailedRecord objects.
     */
    List<FailedRecord> validateTransactions(List<Record> records);

}
