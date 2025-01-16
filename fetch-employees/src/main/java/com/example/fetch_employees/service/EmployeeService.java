package com.example.fetch_employees.service;




import org.apache.velocity.VelocityContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fetch_employees.config.ConfigProperties;
import com.example.fetch_employees.model.Employee;

import  com.example.fetch_employees.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Service class to manage employee-related operations.
 */
@Service
public class EmployeeService {

	@Autowired
	TicketBooking ticketBooking;
	
	@Autowired
	private ConfigProperties configProperties;
	
    // Sample employee list, you can replace it with a database later
    private List<Employee> employees = new ArrayList<>();

    // Adding some initial employees for testing
    public EmployeeService() {
        employees.add(new Employee(1, "John Doe", "Engineering"));
        employees.add(new Employee(2, "Jane Smith", "Marketing"));
        employees.add(new Employee(3, "Mike Brown", "Sales"));
    }

    /**
     * Fetches all the employees.
     * @return List of Employee objects
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adds a new employee to the list.
     * @param employee the employee to be added
     * @return the added employee
     */
    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }
    
    
    	public void conceptOfMultithreading() {
    	
    		Thread user1 = new Thread(() -> ticketBooking.bookTicket("User 1", 2));
    	        Thread user2 = new Thread(() -> ticketBooking.bookTicket("User 2", 2));
    	        Thread user3 = new Thread(() -> ticketBooking.bookTicket("User 3", 1));

    	        user1.start();
    	        user2.start();
    	        user3.start();
    	    }
    


	public String learningStringConcepts(){
		// Step 1: Define the input strings
		String str1 = "apple banana cherry apple banana grape";
		String str2 = "apple orange banana grape";

		// Step 2: Concatenate both strings

		StringBuilder result=new StringBuilder();
		result.append(str1).append(" ").append(str2);
		// Step 3: Split the concatenated string into words
		String[] words = result.toString().split(" ");

		System.out.println("words :: "+ words);

		// Step 4: Remove duplicates using LinkedHashSet (preserving order)
		Set<String> uniqueWords=new LinkedHashSet<>(Arrays.asList(words));

		System.out.println("uniqueWords ::"+ uniqueWords);
		// Step 5: Reverse the order of words
		List<String> reversedWords=new ArrayList(uniqueWords);
		Collections.reverse(reversedWords);

		System.out.println("reversedWords :: "+reversedWords);

		// Step 6: Combine the reversed words into a single string
StringBuilder reveresedString=new StringBuilder();
for (String str:reversedWords){
	reveresedString.append(str).append(" ");
}
		// Step 7: Trim any extra space and print the final result
		System.out.println("reveresedString :: "+reveresedString);
		return reveresedString.toString().trim();





	}
    
    public void logboxTestSplit() throws IOException {
    	// Create a File object for the given filename
    	String filename="/Users/keethakalyankumar/Documents/logBox_sample.xml";
    	File file = new File(filename);

    	// Get the size of the file in bytes
    	long fileSizeInBytes = file.length(); 
    	System.out.println("File Size Length :"+fileSizeInBytes);

    	// Define the maximum allowable file size (3 MB), converted to bytes
    	long maxFileSizeInBytes = 3 * 1024 * 1024;

    	// Read all the lines from the file and store them in a List of Strings
    	// This assumes the file is encoded in UTF-8
    	List<String> allLines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);

    	// Check if the file size exceeds the maximum allowed size
    	if (fileSizeInBytes > maxFileSizeInBytes) {
    	    // Log a debug message indicating the file will be split
    	    System.out.println("File exceeds 3MB, splitting into smaller parts");

    	    // Create a list to store all the split parts of the file
    	    List<List<String>> splitParts = new ArrayList<>();
    	    // Temporary list to hold the current part being processed
    	    List<String> currentPart = new ArrayList<>();
    	    // Variable to track the size of the current part
    	    long currentPartSize = 0;
    	    		System.out.println("CurrentPart size befor for loop :"+ currentPartSize);
    	    // Loop through each line of the file
    	    for (String line : allLines) {
    	    	System.out.println("Entered::::");
    	        // Calculate the size of the current line in bytes
    	        long lineSize = line.getBytes(StandardCharsets.UTF_8).length;
    	        System.out.println("LineSize : "+ lineSize);

    	        // Check if adding this line would exceed the maximum part size
    	        System.out.println("currentPartSize + lineSize::"+(currentPartSize + lineSize));
    	        if (currentPartSize + lineSize > maxFileSizeInBytes) {
    	        	System.out.println("Entered into :::");
    	            // If yes, add the current part to the list of split parts
    	            splitParts.add(currentPart);

    	            // Start a new part and reset the size counter
    	            currentPart = new ArrayList<>();
    	            currentPartSize = 0;
    	        }

    	        // Add the current line to the part and update its size
    	        currentPart.add(line);
    	        currentPartSize += lineSize;
    	        System.out.println("currentPartSize : "+currentPartSize);
    	    }

    	    // If there are any remaining lines in the last part, add them as well
    	    if (!currentPart.isEmpty()) {
    	        splitParts.add(currentPart);
    	    }

    	    // Write each part to a separate file
    	    int partNumber = 1; // Part numbering starts at 1
    	    for (List<String> part : splitParts) {
    	    	// Specify the path to save the part files
    	    	String directoryPath = "/Users/keethakalyankumar/Documents/";

    	    	// Write the part file to the specified directory
    	    	String partFilename = directoryPath + "logBox_part" + partNumber + ".xml";

    	    	// Save the part file
    	    	Files.write(Paths.get(partFilename), part, StandardCharsets.UTF_8);

    	    	// Log the part file saved location
    	    	System.out.println("Saved part: " + partFilename);

    	        // Increment the part number for the next file
    	        partNumber++;
    	    }

    	} else {
    	    // If the file is smaller than the maximum size, save it directly
    	    System.out.println("File size is under 3MB, saving directly as: " + filename);

    	    // Write the file content to a new file
    	    Files.write(Paths.get(filename), allLines, StandardCharsets.UTF_8);
    	}
    }
    
    public void testDataGenerator() {
    	String filename = "/Users/keethakalyankumar/Documents/logBox_sample.xml";
        int repeatCount = 15000; // Number of transactions to generate (adjust as needed)

        String transactionTemplate = 
            "<transaction>\n" +
            "    <timestamp>2025-01-07T10:00:00</timestamp>\n" +
            "    <mode>Credit</mode>\n" +
            "    <particulars>Salary</particulars>\n" +
            "    <deposits>5000.00</deposits>\n" +
            "    <withdrawals>0.00</withdrawals>\n" +
            "    <balance>15000.00</balance>\n" +
            "    <cbsReference>REF12345</cbsReference>\n" +
            "    <txnId>TXN67890</txnId>\n" +
            "</transaction>\n";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("<transactions>\n");
            for (int i = 0; i < repeatCount; i++) {
                writer.write(transactionTemplate);
            }
            writer.write("</transactions>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public List<Employee> getEmployeesForProfiles() {
        // Fetch the employee limit from the active profile's configuration.
        int limit = configProperties.getEmployeeLimit();
        List<Employee> employees = new ArrayList<>();

        // Create mock employee data up to the configured limit.
        for (int i = 1; i <= limit; i++) {
            employees.add(new Employee(i, "Employee " + i, "Department " + i));
        }

        // Return the list of employees as the response.
        return employees;
    }

	public String test() {
		
		String ans="TEST";
		  String jsonString = """
			        {
			            "data": {
			                "applicants": [
			                    {
			                        "address": [
			                            {
			                                "address_type": "1",
			                                "address": {
			                                    "street_name": "123 Main St",
			                                    "city": "Hyderabad",
			                                    "state": "Telangana",
			                                    "zip_code": "500001"
			                                }
			                            }
			                        ]
			                    },
			                    {
			                        "address": [
			                            {
			                                "address_type": "1",
			                                "address": {
			                                    "street_name": "456 Another St",
			                                    "city": "Bangalore",
			                                    "state": "Karnataka",
			                                    "zip_code": "560001"
			                                }
			                            }
			                        ]
			                    }
			                ]
			            }
			        }
			        """;
		  
		// Parse the JSON string
	        JSONObject jsonObject = new JSONObject(jsonString);
	        int count=0;
	        // Create a VelocityContext to store the address
	        VelocityContext context = new VelocityContext();
	        
	        
	        // Navigate to the "applicants" array
	        try {
	            JSONArray applicantsArray = jsonObject.getJSONObject("data").getJSONArray("applicants");
	            JSONObject address=new JSONObject();
	            JSONObject applicant=new JSONObject();// Loop through each applicant
	            for (int i = 0; i < applicantsArray.length(); i++) {
	                 applicant = applicantsArray.getJSONObject(i);
	                JSONArray addressArray = applicant.getJSONArray("address");

	                // Check if there are addresses
	                for(int j=0;j<addressArray.length();j++) {
	                	System.out.println("No of times iterated : "+ ++count);
	                	 address = addressArray.getJSONObject(j);
	                	 String addressType = address.getString("address_type");
	                	 System.out.println("Addresss type is : "+ addressType);
	                	 JSONObject currentAddress = address.getJSONObject("address");
	                	 System.out.println("Current Address : "+ currentAddress);
	                	 if(addressType.equalsIgnoreCase("1")) {
	                		 System.out.println("Entered into if condition");
	                		 context.put("CurrentAddress", currentAddress);
	                	 }
	                }
	                
	            }
	           

	        } catch (Exception e) {
	            System.err.println("Error fetching address type or setting in context: " + e.getMessage());
	        }
		return ""+context.get("CurrentAddress");
	}
	
	
	
}


