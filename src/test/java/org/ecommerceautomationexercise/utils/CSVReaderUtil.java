package utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtil {

    @DataProvider(name = "signupData")
    public static Iterator<Object[]> readSignupData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/signup_users.csv"));
        String line;
        br.readLine(); // Skip header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            data.add(new Object[]{
                    parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
                    parts[8], parts[9], parts[10], parts[11], parts[12], parts[13], parts[14], parts[15],
                    parts[16], parts[17] // expectedResult, expectedMessage
            });
        }
        br.close();
        return data.iterator();
    }
    @DataProvider(name = "loginData")
    public static Iterator<Object[]> readLoginData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/login_users.csv"));
        String line;
        br.readLine(); // skip header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            data.add(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4]});
        }
        br.close();
        return data.iterator();
    }

    @DataProvider(name = "contactFormData")
    public static Iterator<Object[]> readContactFormData() throws Exception {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/contact_data.csv"));
        String line;
        br.readLine(); // Skip header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", -1); // Avoid ignoring empty values
            String absoluteFilePath = Paths.get(parts[4]).toAbsolutePath().toString();
            data.add(new Object[]{
                    parts[0], // name
                    parts[1], // email
                    parts[2], // subject
                    parts[3], // message
                    absoluteFilePath, // file path
                    parts[5], // expectedResult
                    parts[6]  // expectedMessage
            });
        }
        br.close();
        return data.iterator();
    }
    @DataProvider(name = "searchKeywords")
    public static Iterator<Object[]> readSearchKeywords() throws Exception {
        List<Object[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/search_product_data.csv"));
        String line;
        br.readLine(); // Skip header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",", -1);
            data.add(new Object[]{parts[0], Boolean.parseBoolean(parts[1])});
        }
        br.close();
        return data.iterator();
    }
}
