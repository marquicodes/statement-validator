package me.markakis.demo.statementvalidator.utils.patterns;

import me.markakis.demo.statementvalidator.pojos.Transaction;

/**
 * Defines the method to make possible to any class implementing this interface,
 * to return a <code>Transaction</code> object.
 * 
 * @author marqui
 */
public interface TransactionAdapter {

    /**
     * Returns a <code>Transaction</code> object.
     * 
     * @return a <code>Transaction</code> object.
     * @throws NumberFormatException
     *             if reference cannot be parsed as a number, or in case any of
     *             the start balance, mutation, or end balance is not a valid
     *             representation of {@code BigDecimal}.
     */
    Transaction toTransaction();

}
