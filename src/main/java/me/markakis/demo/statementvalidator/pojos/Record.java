package me.markakis.demo.statementvalidator.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

/**
 * Facilitates data mapping between statement records contained in a CSV or XML
 * file and the <code>Transaction</code> object.
 * 
 * @author marqui
 */
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

    @CsvBindByName(column = "Reference", required = true)
    @XmlAttribute(name = "reference")
    private String reference;

    @CsvBindByName(column = "AccountNumber", required = true)
    @XmlElement(name = "accountNumber")
    private String accountNumber;

    @CsvBindByName(column = "Description", required = true)
    @XmlElement(name = "description")
    private String description;

    @CsvBindByName(column = "Start Balance", required = true)
    @XmlElement(name = "startBalance")
    private String startBalance;

    @CsvBindByName(column = "Mutation", required = true)
    @XmlElement(name = "mutation")
    private String mutation;

    @CsvBindByName(column = "End Balance", required = true)
    @XmlElement(name = "endBalance")
    private String endBalance;

    /**
     * Default constructor.
     */
    public Record() {
        super();
    }

    /**
     * Constructs a <code>Record</code> object having the specified values.
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
    public Record(String reference, String accountNumber, String description, String startBalance, String mutation,
            String endBalance) {
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
    public String getReference() {
        return reference;
    }

    /**
     * Sets transaction's reference number.
     * 
     * @param reference
     *            transaction's reference number.
     */
    public void setReference(String reference) {
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
    public String getStartBalance() {
        return startBalance;
    }

    /**
     * Sets starting balance in Euros.
     * 
     * @param startBalance
     *            starting balance in Euros.
     */
    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    /**
     * Returns the addition (+) or the deduction (-) of amount in Euros.
     * 
     * @return the addition (+) or the deduction (-) of amount in Euros.
     */
    public String getMutation() {
        return mutation;
    }

    /**
     * Sets the addition (+) or the deduction (-) of amount in Euros.
     * 
     * @param mutation
     *            the addition (+) or the deduction (-) of amount in Euros.
     */
    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    /**
     * Returns end balance in Euros.
     * 
     * @return end balance in Euros.
     */
    public String getEndBalance() {
        return endBalance;
    }

    /**
     * Sets end balance in Euros.
     * 
     * @param endBalance
     *            end balance in Euros.
     */
    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Record [reference: ").append(reference).append(", accountNumber: ").append(accountNumber)
                .append(", description: ").append(description).append(", startBalance: ").append(startBalance)
                .append(", mutation: ").append(mutation).append(", endBalance: ").append(endBalance).append("]");
        return builder.toString();
    }

}
