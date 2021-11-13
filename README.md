# Tool Rental CLI Application
This is a sample tool rental application that is runnable from a command line of your choice. Here are the following options that you can provide to the application:

```bash
Usage: Tool Rental [-hV] -d=<checkOutDate> -p=<discountPercent> -r=<rentalDays>
                   -t=<toolCode>
  -d, --checkout-date=<checkOutDate>
                  Check out date in the format MM/dd/yy
  -h, --help      Show this help message and exit.
  -p, --discount-percent=<discountPercent>
                  Discount percent
  -r, --rental-days=<rentalDays>
                  Rental days
  -t, --tool-code=<toolCode>
                  Tool code for the rental tool. Acceptable tool codes are
                    LADW, CHNS, JAKD, JAKR.
  -V, --version   Print version information and exit.
```

## Test
To run the tests, execute them with Maven:

```bash
mvn test -f path/to/pom.xml
```

## Build
To build the application so that it can be run from an executable script, run the Maven assembler with the following command:

```bash
mvn package appassembler:assemble -f path/to/pom.xml
```
The script will be generated and saved to the target folder in the path ${projectdir}/target/appassembler/bin/toolrental

## Script
To run the application, execute the script from the command line of your choice. Using an example from a Bash terminal, you can run the application like so:

```bash
mohanlon@6ZRKPL2 MINGW64 ~/development/practice/demo/target/appassembler/bin (main)
$ ./toolrental -d 7/2/20 -p 50 -r 4 -t JAKR
 Tool code: JAKR       
 Tool type: Jackhammer 
 Tool brand: Rigid     
 Rental days: 4        
 Check out date: 7/2/20
 Due date: 7/6/20
 Daily rental charge: $2.99
 Charge days: 1
 Pre-discount charge: $2.99
 Discount percent: 50%
 Discount amount: $1.50
 Final charge: $1.50
 ```