package com.banking.utils;

import com.opencsv.CSVReader;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FilesReader {
    private static final Logger logger = LoggerUtil.getLogger();

    public static List<String[]> readCustomerData(String filePath) {
        LoggerUtil.logMethodEntry(filePath);
        List<String[]> customers = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.readNext(); // Skip header
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                customers.add(record);
            }
            logger.info("Read {} customer records from {}", customers.size(), filePath);
        } catch (Exception e) {
            logger.error("Error reading CSV file: {}", filePath, e);
        }
        LoggerUtil.logMethodExit(customers);
        return customers;
    }

    public static List<String> readTransactionsData(String filePath) {
        LoggerUtil.logMethodEntry(filePath);
        List<String> amounts = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.readNext(); // Skip header
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                amounts.add(record[0]);
            }
            logger.info("Read {} deposit amounts from {}", amounts.size(), filePath);
        } catch (Exception e) {
            logger.error("Error reading CSV file: {}", filePath, e);
        }
        LoggerUtil.logMethodExit(amounts);
        return amounts;
    }
}