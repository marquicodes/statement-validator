package me.markakis.demo.statementvalidator.utils.patterns;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.markakis.demo.statementvalidator.pojos.Record;
import me.markakis.demo.statementvalidator.pojos.Transaction;

/**
 * Tests functionality of RecordAdapter class.
 * 
 * @author marqui
 */
@RunWith(EasyMockRunner.class)
public class RecordAdapterTest {

    @Mock
    private Record        mockRecord;

    @TestSubject
    private RecordAdapter recordAdapter = new RecordAdapter(mockRecord);

    /**
     * Tests constructor.
     */
    @Test
    public void testRecordAdapter() {
        RecordAdapter adapter = new RecordAdapter(mockRecord);
        assertNotNull(adapter);
    }

    /**
     * Tests that toTransaction method returns a Transaction object as expected.
     */
    @Test
    public void testToTransaction() {
        String reference = "290518";
        String accountNumber = "NL43AEGO0773393871";
        String description = "Present for someone special";
        String startBalanceStr = "3110.82";
        String mutationStr = "710.84";
        String endBalanceStr = "2399.98";

        expect(mockRecord.getReference()).andReturn(reference);
        expect(mockRecord.getAccountNumber()).andReturn(accountNumber);
        expect(mockRecord.getDescription()).andReturn(description);
        expect(mockRecord.getStartBalance()).andReturn(startBalanceStr);
        expect(mockRecord.getMutation()).andReturn(mutationStr);
        expect(mockRecord.getEndBalance()).andReturn(endBalanceStr);

        replay(mockRecord);

        // actual call
        Transaction transaction = recordAdapter.toTransaction();
        assertNotNull(transaction);

        assertEquals(Integer.valueOf(reference), transaction.getReference());
        assertEquals(accountNumber, transaction.getAccountNumber());
        assertEquals(description, transaction.getDescription());
        assertTrue(new BigDecimal(startBalanceStr).equals(transaction.getStartBalance()));
        assertTrue(new BigDecimal(mutationStr).equals(transaction.getMutation()));
        assertTrue(new BigDecimal(endBalanceStr).equals(transaction.getEndBalance()));

        verify(mockRecord);
    }

    /**
     * Tests toTransaction when transaction reference is not a number.
     */
    @Test(expected = NumberFormatException.class)
    public void testToTransaction_ReferenceIsNotNumber() {
        String reference = "290a518";

        expect(mockRecord.getReference()).andReturn(reference).times(2);

        replay(mockRecord);

        // actual call
        Transaction transaction = recordAdapter.toTransaction();
        assertNotNull(transaction);

        verify(mockRecord);
    }

    /**
     * Tests toTransaction when start balance is not a valid BigDecimal value.
     */
    @Test(expected = NumberFormatException.class)
    public void testToTransaction_StartBalanceIsNotBigDecimal() {
        String reference = "2903518";
        String accountNumber = "NL43AEGO0773393871";
        String description = "Present for someone special";
        String startBalanceStr = "3110,82";

        expect(mockRecord.getReference()).andReturn(reference);
        expect(mockRecord.getAccountNumber()).andReturn(accountNumber);
        expect(mockRecord.getDescription()).andReturn(description);
        expect(mockRecord.getStartBalance()).andReturn(startBalanceStr).times(2);

        replay(mockRecord);

        // actual call
        Transaction transaction = recordAdapter.toTransaction();
        assertNotNull(transaction);

        verify(mockRecord);
    }

    /**
     * Tests toTransaction when mutation is not a valid BigDecimal value.
     */
    @Test(expected = NumberFormatException.class)
    public void testToTransaction_MutationIsNotBigDecimal() {
        String reference = "2903518";
        String accountNumber = "NL43AEGO0773393871";
        String description = "Present for someone special";
        String startBalanceStr = "3110.82";
        String mutationStr = "710,84";

        expect(mockRecord.getReference()).andReturn(reference);
        expect(mockRecord.getAccountNumber()).andReturn(accountNumber);
        expect(mockRecord.getDescription()).andReturn(description);
        expect(mockRecord.getStartBalance()).andReturn(startBalanceStr);
        expect(mockRecord.getMutation()).andReturn(mutationStr).times(2);

        replay(mockRecord);

        // actual call
        Transaction transaction = recordAdapter.toTransaction();
        assertNotNull(transaction);

        verify(mockRecord);
    }

    /**
     * Tests toTransaction when end balance is not a valid BigDecimal value.
     */
    @Test(expected = NumberFormatException.class)
    public void testToTransaction_EndBalanceIsNotBigDecimal() {
        String reference = "2903518";
        String accountNumber = "NL43AEGO0773393871";
        String description = "Present for someone special";
        String startBalanceStr = "3110.82";
        String mutationStr = "710.84";
        String endBalanceStr = "2399,98";

        expect(mockRecord.getReference()).andReturn(reference);
        expect(mockRecord.getAccountNumber()).andReturn(accountNumber);
        expect(mockRecord.getDescription()).andReturn(description);
        expect(mockRecord.getStartBalance()).andReturn(startBalanceStr);
        expect(mockRecord.getMutation()).andReturn(mutationStr);
        expect(mockRecord.getEndBalance()).andReturn(endBalanceStr).times(2);

        replay(mockRecord);

        // actual call
        Transaction transaction = recordAdapter.toTransaction();
        assertNotNull(transaction);

        verify(mockRecord);
    }

}
