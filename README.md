# shopping
   Description
      Get discounted price when shopping at a store

# Running with Maven
  When running with Spring Boot, the arguments are separated by comma.
  If the argument contains comma, then use a different delimiter.
  In this case, the different arguments are :
  CSV FILE PATH
  2
  1|2|3|4
  1|5
  
# Command to run
mvn spring-boot:run -Drun.arguments="CSV FILE PATH,2,1|2|3|4,1|5"
