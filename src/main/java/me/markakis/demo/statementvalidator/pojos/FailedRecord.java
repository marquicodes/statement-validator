package me.markakis.demo.statementvalidator.pojos;

/**
 * Stores information for a failed record.
 * 
 * @author marqui
 */
public class FailedRecord {

    private String reference;

    private String description;

    /**
     * Default constructor.
     */
    public FailedRecord() {
        super();
    }

    /**
     * Constructs a FailedRecord object having the supplied values.
     * 
     * @param reference
     *            failed transaction's reference number.
     * @param description
     *            failed transaction's additional information.
     */
    public FailedRecord(String reference, String description) {
        super();
        this.reference = reference;
        this.description = description;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FailedRecord [reference: ").append(reference).append(", description: ").append(description)
                .append("]");
        return builder.toString();
    }

}
