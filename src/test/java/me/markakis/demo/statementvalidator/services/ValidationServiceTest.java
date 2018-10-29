package me.markakis.demo.statementvalidator.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.markakis.demo.statementvalidator.pojos.FailedRecord;
import me.markakis.demo.statementvalidator.pojos.Record;

/**
 * Tests functionality of ValidationService class.
 * 
 * @author marqui
 */
@RunWith(EasyMockRunner.class)
public class ValidationServiceTest {

    @TestSubject
    private ValidationService validationService = new ValidationServiceImpl();

    /**
     * Tests the case where end balance has not a leading zero while calculated
     * result has one.
     */
    @Test
    public void testValidateTransactions_CorrectEndBalance_IgnoreLeadingZeros() {
        Record record1 = new Record("123456", "NL43AEGO0773393871", "first", "134.50", "-4.30", "130.2");

        List<Record> records = Arrays.asList(record1);

        // actual call
        List<FailedRecord> failedRecords = validationService.validateTransactions(records);
        assertNotNull(failedRecords);
        assertTrue(failedRecords.isEmpty());
    }
    
    /**
     * Tests the case where reference number is not numeric.
     */
    @Test
    public void testValidateTransactions_AlphanumericReference() {
        Record record2 = new Record("12A4s7", "NL91RABO0315273637", "second", "15.24", "-7.20", "12.44");

        List<Record> records = Arrays.asList(record2);

        // actual call
        List<FailedRecord> failedRecords = validationService.validateTransactions(records);
        assertNotNull(failedRecords);

        assertEquals(1, failedRecords.size());
        assertEquals(record2.getReference(), failedRecords.get(0).getReference());
    }

    /**
     * Tests the case where end balance is wrong.
     */
    @Test
    public void testValidateTransactions_WrongEndBalanceAmount() {
        Record record3 = new Record("123457", "NL91RABO0315273637", "third", "15.24", "-7.20", "12.44");

        List<Record> records = Arrays.asList(record3);

        // actual call
        List<FailedRecord> failedRecords = validationService.validateTransactions(records);
        assertNotNull(failedRecords);

        assertEquals(1, failedRecords.size());
        assertEquals(record3.getReference(), failedRecords.get(0).getReference());
    }

    /**
     * Tests the case where two records have the same reference number.
     */
    @Test
    public void testValidateTransactions_DuplicateReferenceNumber() {
        Record record1 = new Record("123458", "NL43AEGO0773393871", "first", "134.50", "-4.30", "130.20");

        Record record2 = new Record("123459", "NL93ABNA0585619023", "second", "93.45", "+47.24", "140.69");

        Record record3 = new Record("123458", "NL27SNSB0917829871", "third", "16.87", "+206.24", "223.11");

        List<Record> records = Arrays.asList(record1, record2, record3);

        // actual call
        List<FailedRecord> failedRecords = validationService.validateTransactions(records);
        assertNotNull(failedRecords);

        assertEquals(1, failedRecords.size());
        assertEquals(record3.getReference(), failedRecords.get(0).getReference());
    }
    
    /**
     * Tests the case where balance related fields are not well formed.
     */
    @Test
    public void testValidateTransactions_WrongStartBalancelRepresentation() {
        Record record3 = new Record("123457", "NL91RABO0315273637", "third", "134,50", "-4.30", "130.20");

        List<Record> records = Arrays.asList(record3);

        // actual call
        List<FailedRecord> failedRecords = validationService.validateTransactions(records);
        assertNotNull(failedRecords);

        assertEquals(1, failedRecords.size());
        assertEquals(record3.getReference(), failedRecords.get(0).getReference());
    }
    
    /**
     * Tests the case IBAN is not valid.
     */
    @Test
    public void testValidateTransactions_WrongIBAN() {
        Record record = new Record("123237", "NL91RABO0314573637", "Wrong IBAN", "134,50", "-4.30", "130.20");

        List<Record> records = Arrays.asList(record);

        // actual call
        List<FailedRecord> failedRecords = validationService.validateTransactions(records);
        assertNotNull(failedRecords);

        assertEquals(1, failedRecords.size());
        assertEquals(record.getReference(), failedRecords.get(0).getReference());
    }

}
