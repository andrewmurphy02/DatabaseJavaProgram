import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class DB {
  public static final int NUM_RECORDS = 500;
  public static final int RECORD_SIZE = 84;

  private RandomAccessFile Din;
  private RandomAccessFile Dout;
  private int num_records;
  private int unsortedRecords;
  private String Name;
  private String Rank;
  private String City;
  private String State;
  private String Zip;
  private String Employees;
  private boolean isOpen;

  public DB() {
    this.Din = null;
    this.Dout = null;
    this.num_records = 0;
    this.unsortedRecords = 0;
    this.Name = "NAME";
    this.Rank = "RANK";
    this.City = "CITY";
    this.State = "STATE";
    this.Zip = "ZIP";
    this.Employees = "EMPLOYEES";
    this.isOpen = false;
  }

  //Returns true if DB is open, otherwises returns false
  public boolean checkIsOpen()
  {
    return this.isOpen;
  }

  /**
   * Opens the file in read/write mode
   * 
   * @param filename (e.g., input.data)
   * @return status true if operation successful
   */
  public void  open(String filename) {
    // Set the number of records
    this.num_records = NUM_RECORDS;
    this.isOpen = true;
    // Open file in read/write mode
    try {
      this.Din = new RandomAccessFile(filename, "rw");
    } catch (FileNotFoundException e) {
      System.out.println("Could not open file\n");
      this.isOpen = false;
      e.printStackTrace();
    }
  }
  
  public static RandomAccessFile openFile(String fileName) throws IOException {
      return new RandomAccessFile(fileName, "rw");
  }
  
  /** 
   * 
   *  
   */
  public void writeRecord(RandomAccessFile file, String Name, String Rank, String City, String State, String Zip, String Employees ) {
	  
	  try {
		  
		  file.writeBytes(Name + Rank + City + State + Zip + Employees +"\n");
      
		  
	} catch (IOException e) {
		e.printStackTrace();
	}  
  }
  
  public int getUnsortedRecords()
  {
    return unsortedRecords;
  }

  public void addRecord(String Name, String Rank, String City, String State, String Zip, String Employees)
  {
    String spaces = "                                                                                                                                               ";
    String nameS = Name + spaces;
    String rankS = Rank + spaces;
    String cityS = City + spaces;
    String stateS = State + spaces;
    String zipS = Zip + spaces;
    String employeesS = Employees + spaces;
    byte[] bName = nameS.getBytes();
    byte[] bRank = rankS.getBytes();
    byte[] bCity = cityS.getBytes();
    byte[] bState = stateS.getBytes();
    byte[] bZip = zipS.getBytes();
    byte[] bEmployees = employeesS.getBytes();
    try 
    {
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE);
      Din.write(bName, 0, 40);
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE + 40);
      Din.write(bRank, 0, 5);
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE + 45);
      Din.write(bCity, 0, 20);
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE + 65);
      Din.write(bState, 0, 4);
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE + 69);
      Din.write(bZip, 0, 7);
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE + 76);
      Din.write(bEmployees, 0, 7);
      Din.seek((NUM_RECORDS + unsortedRecords) * RECORD_SIZE + 83);
      Din.writeBytes("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    unsortedRecords += 1;

  }

  public void updateRecord(int record_num)
  {
    if (record_num == -1)
    {
      System.out.println("That record does not exist!");
      return;
    }
    String option = "";
    String inputS = "";
    String spaces = "                                                                                                      ";
    String temp = "";
    byte[] b;
    Scanner inputOpt = new Scanner(System.in);
    while(true)
    {
      System.out.println("\n" + readRecord(record_num).toString());
      System.out.println("1) Rank\n2) City\n3) State\n4) Zip\n5) Employees\n6) Exit");
      System.out.print("Please enter which field you would like to edit: ");
      option = inputOpt.nextLine();
      switch (option)
      {
        case "1":
          System.out.print("Please enter the new Rank you would like to assign: ");
          inputS = inputOpt.nextLine();
          temp = inputS + spaces;
          b = temp.getBytes();
          try {
            Din.seek(record_num * RECORD_SIZE + 40);
            Din.write(b, 0, 5);
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        case "2":
          System.out.print("Please enter the new City you would like to assign: ");
          inputS = inputOpt.nextLine();
          inputS = inputS.toUpperCase();
          temp = inputS + spaces;
          b = temp.getBytes();
          try {
            Din.seek(record_num * RECORD_SIZE + 45);
            Din.write(b, 0, 20);
          } catch (IOException e) {
            e.printStackTrace();
          } 
          break;
        case "3":
          System.out.print("Please enter the new State you would like to assign: ");
          inputS = inputOpt.nextLine();
          inputS = inputS.toUpperCase();
          temp = inputS + spaces;
          b = temp.getBytes();
          try {
            Din.seek(record_num * RECORD_SIZE + 65);
            Din.write(b, 0, 4);
          } catch (IOException e) {
            e.printStackTrace();
          } 
          break;
        case "4":
          System.out.print("Please enter the new Zip you would like to assign: ");
          inputS = inputOpt.nextLine();
          temp = inputS + spaces;
          b = temp.getBytes();
          try {
            Din.seek(record_num * RECORD_SIZE + 69);
            Din.write(b, 0, 7);
          } catch (IOException e) {
            e.printStackTrace();
          } 
          break;
        case "5":
          System.out.print("Please enter the new number of Employees you would like to assign: ");
          inputS = inputOpt.nextLine();
          temp = inputS + spaces;
          b = temp.getBytes();
          try {
            Din.seek(record_num * RECORD_SIZE + 76);
            Din.write(b, 0, 7);
          } catch (IOException e) {
            e.printStackTrace();
          } 
          break;
        case "6":
          return;
        default:
          System.out.println("That's not a valid option!");
          break;
      }
    }  
  }

  public void deleteRecord(int record_num)
  {
    if (record_num == -1)
    {
      System.out.println("That record does not exist!");
      return;
    }
    String spaces = "                                                                                                                                               ";
    String temp = "NULL" + spaces;
    byte[] b = temp.getBytes();
    try 
    {
      Din.seek(record_num * RECORD_SIZE + 40);
      Din.write(b, 0, 43);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Opens CSV file and creates new data file
   * Reads CSV file attributes
   * @throws IOException 
   */
  public void createDB(String filename) throws IOException {
	  
	  this.Din = new RandomAccessFile(filename+".csv", "r");
    RandomAccessFile configFile = openFile(filename+".config");
    try {
        configFile.writeBytes("Number of Records: 500\nNumber of Data Fields: 6 {\n\tName: 40 bytes\n\tRank: 5 bytes\n\tCity: 20 bytes\n\tState: 4 bytes\n\tZip: 7 bytes\n\tEmployees: 7 bytes\n\tEOF: 1 byte\n}");
    } catch (IOException e) {
        e.printStackTrace();
    }
    configFile.close();
	  RandomAccessFile file = openFile(filename+".data"); 
	  
	  String line;
      while ((line = this.Din.readLine()) != null) {
          String[] attribute = line.split(",");
          this.Name = String.format("%-40s", attribute[0]);
          this.Rank = String.format("%-5s", attribute[1]);
          this.City = String.format("%-20s", attribute[2]);
          this.State = String.format("%-4s", attribute[3]);
          this.Zip = String.format("%-7s", attribute[4]);
          this.Employees = String.format("%-7s", attribute[5]);
               
          writeRecord (file, this.Name, this.Rank, this.City, this.State, this.Zip, this.Employees);
      }
      file.close();
      //Din.close();
  }

  /**
   * Close the database file
   */
  public void close() {
    try {
      this.isOpen = false;
      Din.close();
    } catch (IOException e) {
      System.out.println("There was an error while attempting to close the database file.\n");
      e.printStackTrace();
    }
  }

  /**
   * Get record number n (Records numbered from 0 to NUM_RECORDS-1)
   * 
   * @param record_num
   * @return values of the fields with the name of the field and
   *         the values read from the record
   */
  public Record readRecord(int record_num) {
    Record record = new Record();
    String[] fields;

    if ((record_num >= 0) && (record_num < this.num_records + unsortedRecords)) {
      try {
        Din.seek(0); // return to the top of the file
        Din.skipBytes(record_num * RECORD_SIZE);
        // parse record and update fields
        fields = Din.readLine().split("\\s{2,}", 0);
        if(fields[1].length() == 4 && (fields[1].substring(0,4).compareTo("NULL")) == 0)
        {
          record.Name = fields[0];
        }
        else
          record.updateFields(fields);
      } catch (IOException e) {
        System.out.println("There was an error while attempting to read a record from the database file.\n");
        e.printStackTrace();
      }
    }
    return record;
  }

  /**
   * Binary Search by record id
   * 
   * @param id
   * @return Record number (which can then be used by read to
   *         get the fields) or -1 if id not found
   */
  public int binarySearch(String Name) {
    int Low = 0;
    int High = NUM_RECORDS - 1;
    int Middle = 0;
    String linearId;
    boolean Found = false;
    Record record, record2;

    while (!Found && (High >= Low)) {
      Middle = (Low + High) / 2;
      record = readRecord(Middle);
      String MiddleId = record.Name;

      int result = MiddleId.compareTo(Name); // DOES STRING COMPARE
      
      if (result == 0 && record.Rank != null)
        Found = true;
      else if (result < 0)
        Low = Middle + 1;
      else
        High = Middle - 1;
    }
    
    if (!Found && unsortedRecords > 0)
    {
      int totalRecords = NUM_RECORDS + unsortedRecords;
      for (int i = NUM_RECORDS; i < totalRecords; i++)
      {
        record2 = readRecord(i);
        linearId = record2.Name;
        int result2 = linearId.compareTo(Name);
        if (result2 == 0 && record2.Rank != null)
        {
          Found = true;
          return i;
        }
      }
    }
    
    if (Found) {
      return Middle; // the record number of the record
    } else
      return -1;
  }
}