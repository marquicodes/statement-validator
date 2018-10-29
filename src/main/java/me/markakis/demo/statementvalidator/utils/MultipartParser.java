package me.markakis.demo.statementvalidator.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import me.markakis.demo.statementvalidator.pojos.Record;
import me.markakis.demo.statementvalidator.pojos.Statement;

/**
 * This class is responsible to parse the uploaded files and get their data
 * mapped in the appropriate objects.
 * 
 * @author marqui
 */
@Component
public class MultipartParser {

    /**
     * Parses the supplied file and return a list of Transactions.
     * 
     * @param file
     *            the multipart file containing statement records.
     * @return the List of Transactions.
     * @throws IOException
     *             in case of access errors (if the temporary store fails).
     * @throws JAXBException
     *             in case of any unexpected errors occur while unmarshalling.
     */
    public List<Record> geTransactions(MultipartFile file) throws IOException, JAXBException {

        // retrieves uploaded file extension
        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.length() - 3);

        if ("csv".equalsIgnoreCase(extension)) {
            return parseCsvFile(file);
        } else if ("xml".equalsIgnoreCase(extension)) {
            return parseXmlFile(file);
        }

        throw new InvalidFileNameException(filename, "Only .csv and .xml files are supported.");
    }

    /**
     * Parses a CSV file and returns its content as List of Transactions.
     * 
     * @param file
     *            the multipart file containing statement records.
     * @return the List of Transactions.
     * @throws IOException
     *             in case of access errors (if the temporary store fails).
     */
    private List<Record> parseCsvFile(MultipartFile file) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<Record> csv = new CsvToBeanBuilder<Record>(reader).withType(Record.class).build();
            return csv.parse();
        }
    }

    /**
     * Parses an XML file and returns its content as List of Transactions.
     * 
     * @param file
     *            the multipart file containing statement records.
     * @return the List of Transactions.
     * @throws IOException
     *             in case of access errors (if the temporary store fails).
     * @throws JAXBException
     *             in case of any unexpected errors occur while unmarshalling.
     */
    private List<Record> parseXmlFile(MultipartFile file) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Statement.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Statement statement = (Statement) unmarshaller.unmarshal(file.getInputStream());
        return statement.getTransactions();
    }

}
