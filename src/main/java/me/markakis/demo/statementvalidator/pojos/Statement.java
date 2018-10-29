package me.markakis.demo.statementvalidator.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class for collection of <code>Record</code>s coming from XML files.
 * 
 * @author marqui
 */
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Statement {

    @XmlElement(name = "record")
    private List<Record> records;

    /**
     * Default constructor.
     */
    public Statement() {
    }

    /**
     * Constructs a Statement object containing a list of <code>Record</code>s.
     * 
     * @param records
     *            the list of <code>Record</code>s belonging to this statement.
     */
    public Statement(List<Record> records) {
        super();
        this.records = records;
    }

    /**
     * Returns the list of <code>Record</code>s
     * 
     * @return the list of <code>Record</code>s
     */
    public List<Record> getTransactions() {
        return records;
    }

    /**
     * Sets the list of <code>Record</code>s
     * 
     * @param records
     *            the list of <code>Record</code>s
     */
    public void setTransactions(List<Record> records) {
        this.records = records;
    }

}
