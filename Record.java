import java.io.IOException;
public class Record {

  private boolean empty;

  public String Name; //Id
  public String Rank; //Experience
  public String City; //Married
  public String State; //Wage
  public String Zip; //Industry
  public String Employees; //Empty

  public Record() {
    empty = true;
  }

  /**
   * Update the fields of a record from an array of fields
   * 
   * @param fields array with values of fields
   * @return nothing
   * @throws IOException
   */
  public void updateFields(String[] fields) throws IOException {
    if (fields.length == 6) {
      this.Name = fields[0];
      this.Rank = fields[1];
      this.City = fields[2];
      this.State = fields[3];
      this.Zip = fields[4];
      this.Employees = fields[5];

      empty = false;
    } else
      throw new IOException();
  }

  /**
   * Check if record fields have been updated
   * 
   * @return true if record has been updated otherwise false
   */
  public boolean isEmpty() {
    return empty;
  }

  public boolean isNull() {
    if (this.Name == null)
      return true;
    else
      return false;
  }

  public String toString() {
    if (this.Rank == null)
      return "This record does not exist";
    else
      return "Name: " + this.Name +
        ", Rank: " + this.Rank +
        ", City: " + this.City +
        ", State: " + this.State +
        ", Zip: " + this.Zip +
        ", Employees: " + this.Employees;
  }

}