package me.markakis.demo.statementvalidator.utils;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import me.markakis.demo.statementvalidator.pojos.Record;

/**
 * Tests functionality of MultipartParser class.
 * 
 * @author marqui
 */
@RunWith(EasyMockRunner.class)
public class MultipartParserTest {

    @Mock
    private MultipartFile   mockMultipartFile;

    @TestSubject
    private MultipartParser multipartParser = new MultipartParser();

    /**
     * Tests that CSV file is parsed correctly.
     * 
     * @throws IOException
     * @throws JAXBException
     */
    @Test
    public void testGeTransactions_Csv() throws IOException, JAXBException {
        // loads file from test resources
        File file = ResourceUtils.getFile(this.getClass().getResource("/records.csv"));

        expect(mockMultipartFile.getOriginalFilename()).andReturn("records.csv");

        try (InputStream inputStream = new FileInputStream(file)) {

            expect(mockMultipartFile.getInputStream()).andReturn(inputStream);

            replay(mockMultipartFile);

            // actual call
            List<Record> records = multipartParser.geTransactions(mockMultipartFile);

            assertNotNull(records);
            assertEquals(2, records.size());

            assertEquals("194261", records.get(0).getReference());
            assertEquals("112806", records.get(1).getReference());
        }

        verify(mockMultipartFile);
    }

    /**
     * Tests that XML file is parsed correctly.
     * 
     * @throws IOException
     * @throws JAXBException
     */
    @Test
    public void testGeTransactions_Xml() throws IOException, JAXBException {
        // loads file from test resources
        File file = ResourceUtils.getFile(this.getClass().getResource("/records.xml"));

        expect(mockMultipartFile.getOriginalFilename()).andReturn("records.xml");

        try (InputStream inputStream = new FileInputStream(file)) {

            expect(mockMultipartFile.getInputStream()).andReturn(inputStream);

            replay(mockMultipartFile);

            // actual call
            List<Record> records = multipartParser.geTransactions(mockMultipartFile);

            assertNotNull(records);
            assertEquals(2, records.size());

            assertEquals("130498", records.get(0).getReference());
            assertEquals("167875", records.get(1).getReference());
        }

        verify(mockMultipartFile);
    }
    
    /**
     * Tests that XML file is parsed correctly.
     * 
     * @throws IOException
     * @throws JAXBException
     */
    @Test
    public void testGeTransactions_Xml_AlphanumericReference() throws IOException, JAXBException {
        // loads file from test resources
        File file = ResourceUtils.getFile(this.getClass().getResource("/alphanumeric_reference.xml"));

        expect(mockMultipartFile.getOriginalFilename()).andReturn("alphanumeric_reference.xml");

        try (InputStream inputStream = new FileInputStream(file)) {

            expect(mockMultipartFile.getInputStream()).andReturn(inputStream);

            replay(mockMultipartFile);

            // actual call
            List<Record> records = multipartParser.geTransactions(mockMultipartFile);

            assertNotNull(records);
            assertEquals(1, records.size());

            assertEquals("130A98", records.get(0).getReference());
        }

        verify(mockMultipartFile);
    }

    /**
     * Tests that InvalidFileNameException is thrown when file extension differs
     * from the expected .csv or .xml.
     * 
     * @throws IOException
     * @throws JAXBException
     */
    @Test(expected = InvalidFileNameException.class)
    public void testGeTransactions_NonExpectedExtension() throws IOException, JAXBException {

        expect(mockMultipartFile.getOriginalFilename()).andReturn("sample.doc");

        replay(mockMultipartFile);

        // actual call
        List<Record> records = multipartParser.geTransactions(mockMultipartFile);

        assertNotNull(records);
        assertEquals(2, records.size());

        verify(mockMultipartFile);
    }
    
    /**
     * Tests malformed XML.
     * 
     * @throws IOException
     * @throws JAXBException
     */
    @Test(expected = UnmarshalException.class)
    public void testGeTransactions_MalformedXml() throws IOException, JAXBException {
        // loads file from test resources
        File file = ResourceUtils.getFile(this.getClass().getResource("/malformed.xml"));

        expect(mockMultipartFile.getOriginalFilename()).andReturn("malformed.xml");

        try (InputStream inputStream = new FileInputStream(file)) {

            expect(mockMultipartFile.getInputStream()).andReturn(inputStream);

            replay(mockMultipartFile);

            // actual call
            List<Record> records = multipartParser.geTransactions(mockMultipartFile);

            assertNotNull(records);
            assertEquals(1, records.size());

            assertEquals("130A98", records.get(0).getReference());
        }

        verify(mockMultipartFile);
    }

}
