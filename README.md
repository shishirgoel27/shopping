# shopping
   Description : 
      Get discounted price at a shopping store

# Running with Maven
  When running with mvn spring-boot command, the arguments are to be separated by comma.
  If the argument value contains comma, then replace it with a different delimiter(default used is '|').
  In this case, the different arguments submitted are:
      CSV FILE PATH
      2
      1|2|3|4
      1|5
  
# Command to run
mvn spring-boot:run -Drun.arguments="CSV FILE PATH,2,1|2|3|4,1|5"

java -jar discount-0.0.1-SNAPSHOT.jar "../input.csv" "2" "1|2|3|4" "1|5"

Note: The inventory input is assumed to come from CSV file with each row having serial numbers.
