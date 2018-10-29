package me.markakis.demo.statementvalidator.pojos;

import java.math.BigDecimal;

/**
 * Defines a class to store each transaction's information.
 * 
 * @author marqui
 */
public class Transaction {

    private Integer    reference;

    private String     accountNumber;

    private String     description;

    private BigDecimal startBalance;

    private BigDecimal mutation;

    private BigDecimal endBalance;

    /**
     * Default constructor.
     */
    public Transaction() {
        super();
    }

    /**
     * Constructs a <code>Transaction</code> object having the specified values.
     * 
     * @param reference
     *            transaction's reference number.
     * @param accountNumber
     *            account's IBAN.
     * @param description
     *            additional information about transaction.
     * @param startBalance
     *            starting balance in Euros.
     * @param mutation
     *            either the addition (+) or the deduction (-) of amount in
     *            Euros.
     * @param endBalance
     *            end balance in Euros.
     */
    public Transaction(Integer reference, String accountNumber, String description, BigDecimal startBalance,
            BigDecimal mutation, BigDecimal endBalance) {
        super();
        this.reference = reference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }

    /**
     * Returns transaction's reference number.
     * 
     * @return transaction's reference number.
     */
    public Integer getReference() {
        return reference;
    }

    /**
     * Sets transaction's reference number.
     * 
     * @param reference
     *            transaction's reference number.
     */
    public void setReference(Integer reference) {
        this.reference = reference;
    }

    /**
     * Returns account's IBAN.
     * 
     * @return account's IBAN.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets account's IBAN.
     * 
     * @param accountNumber
     *            account's IBAN.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Returns additional information about transaction.
     * 
     * @return additional information about transaction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets additional information about transaction.
     * 
     * @param description
     *            additional information about transaction.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns starting balance in Euros.
     * 
     * @return starting balance in Euros.
     */
    public BigDecimal getStartBalance() {
        return startBalance;
    }

    /**
     * Sets starting balance in Euros.
     * 
     * @param startBalance
     *            starting balance in Euros.
     */
    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    /**
     * Returns the addition (+) or the deduction (-) of amount in Euros.
     * 
     * @return the addition (+) or the deduction (-) of amount in Euros.
     */
    public BigDecimal getMutation() {
        return mutation;
    }

    /**
     * Sets the addition (+) or the deduction (-) of amount in Euros.
     * 
     * @param mutation
     *            the addition (+) or the deduction (-) of amount in Euros.
     */
    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }

    /**
     * Returns end balance in Euros.
     * 
     * @return end balance in Euros.
     */
    public BigDecimal getEndBalance() {
        return endBalance;
    }

    /**
     * Sets end balance in Euros.
     * 
     * @param endBalance
     *            end balance in Euros.
     */
    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Transaction [reference: ").append(reference).append(", accountNumber: ").append(accountNumber)
                .append(", description: ").append(description).append(", startBalance: ").append(startBalance)
                .append(", mutation: ").append(mutation).append(", endBalance: ").append(endBalance).append("]");
        return builder.toString();
    }

}
