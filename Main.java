//Authors: Drew Grove and Andrew Murphy
//Date: 1/30/23
import java.io.IOException;
import java.util.Scanner;
public class Main
{

	public static void main(String []args) throws IOException
	{
		String option = "";
		String databaseName = "";
		String inputFiles = "";
		String tName;
		DB db = new DB();
		Scanner input = new Scanner(System.in);
		while (true)
		{
			System.out.println("\nWelcome to your new file-based database system!");
			System.out.println("1) Create New Database\n2) Open Database\n3) Close Database\n4) Display Record\n5) Update Record\n6) Create Report\n7) Add a Record\n8) Delete a Record\n9) Quit\n");
			System.out.print("Please enter the option number of your choice: ");
			option = input.nextLine();
			switch (option)
			{
				case "1":
					System.out.print("Please enter the name of your .csv file: ");
					databaseName = input.nextLine();
					db.createDB(databaseName);
					break;
				case "2":
					if (!db.checkIsOpen())
					{
						System.out.print("Please enter the prefix for your pair of files: ");
						inputFiles = input.nextLine();
						db.open(inputFiles + ".data");
					}
					else
						System.out.println("There is a database already open!");
					break;
				case "3":
					if (db.checkIsOpen())
						db.close();
					else
						System.out.println("There is no open database to close!");
					break;
				case "4":
					if (db.checkIsOpen())
					{
						System.out.print("Please enter the name of the record you wish to find: ");
						tName = input.nextLine();
						tName = tName.toUpperCase();
						System.out.println(db.readRecord(db.binarySearch(tName)).toString());
					}
					else
						System.out.println("There is no open database!");
					break;
				case "5":
					if (db.checkIsOpen())
					{
						System.out.print("Please enter the name of the record you wish to update: ");
						tName = input.nextLine();
						tName = tName.toUpperCase();
						db.updateRecord(db.binarySearch(tName));
					}
					else
						System.out.println("There is no open database!");
					break;
				case "6":
				if (db.checkIsOpen())
					{
						int count = 0;
						int numPrinted = 0;
						while (numPrinted < 10 && count < (db.NUM_RECORDS + db.getUnsortedRecords()))
						{
							if (db.readRecord(count).isEmpty())
								count+=1;
							else
							{
								System.out.println(db.readRecord(count).toString());
								count += 1;
								numPrinted += 1;
							}
						}
					}
					else
						System.out.println("There is no open database!");
					break;
				case "7":
					if (db.checkIsOpen())
					{
						String name, rank, city, state, zip, employees;
						System.out.print("Please enter the name of the record: ");
						name = input.nextLine();
						name = name.toUpperCase();
						System.out.print("Please enter the rank of the record: ");
						rank = input.nextLine();
						System.out.print("Please enter the city of the record: ");
						city = input.nextLine();
						city = city.toUpperCase();
						System.out.print("Please enter the state of the record: ");
						state = input.nextLine();
						state = state.toUpperCase();
						System.out.print("Please enter the zip of the record: ");
						zip = input.nextLine();
						System.out.print("Please enter the employees of the record: ");
						employees = input.nextLine();
						db.addRecord(name, rank, city, state, zip, employees);
					}
					else
						System.out.println("There is no open database!");
					break;
				case "8":
					if (db.checkIsOpen())
					{
						System.out.print("Please enter the name of the record you wish to delete: ");
						tName = input.nextLine();
						tName = tName.toUpperCase();
						db.deleteRecord(db.binarySearch(tName));
					}
					else
						System.out.println("There is no open database!");
					break;
				case "9":
					input.close();
					System.exit(0);
				default:
					System.out.println("That is an incorrect option!");
					break;
			}
		}
	}
}