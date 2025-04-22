# SwagLabs-Selenium-Java-Framework

This project is a robust **Test Automation Framework** built using **Selenium WebDriver**, **TestNG**, **Page Object Model (POM)**, and **Extent Reports**. It is designed to automate end-to-end functional testing for the [SwagLabs demo application](https://www.saucedemo.com/).

---

##  Project Structure

SwagLabs/
├── Configuration/
│   └── config.properties         # Configuration for environment, browser, credentials
├── Logs/
│   └── application.log           # Execution logs
├── Reports/
│   └── ExtentReports.html        # Beautiful HTML reports
├── Screenshots/
│   └── *.png                     # Screenshots on test failure
├── src/
│   └── com.swaglabs/
│       ├── base/                 # Base classes
│       ├── pageobject/           # Page classes following POM
│       ├── testcases/            # TestNG Test Classes
│       └── utilities/            # Utilities (Excel, Config, Listeners)
├── TestData/
│   └── SwagLabsTestData.xlsx     # Test data for data-driven tests
└── README.md                     # Project documentation

---

##  Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Extent Reports
- Log4j2
- Apache POI (Excel support)
- Maven

---

##  Features

-  Login automation
-  Add/Remove products from cart
-  Checkout and order confirmation
-  Data-driven testing using Excel
-  Automatic screenshot capture on test failure
-  Custom logging with Log4j2
-  Extent Report for beautiful test results

---

##  Getting Started

### Prerequisites

- Java 11 or above
- Maven
- Eclipse/IntelliJ
- ChromeDriver or other browser drivers

### Clone the Repository

git clone https://github.com/codedbyshubham/SwagLabs.git
cd SwagLabs

### Install Dependencies

Make sure Maven is installed and run:

mvn clean install

### Update Configuration

Edit `Configuration/config.properties` with:

baseUrl = https://www.saucedemo.com/
browser = chrome
userName = standard_user
password = secret_sauce

---

##  Running Tests

### Run all tests:

mvn test

### Run a specific test class:

mvn -Dtest=TC_LoginPageTest test

---

##  Test Reports

- **HTML Report:** Automatically generated under `/Reports`
- **Screenshots:** Captured under `/Screenshots` if a test fails
- **Log File:** Logs under `/Logs/application.log`

---

##  Data-Driven Testing

Test data is sourced from:

/TestData/SwagLabsTestData.xlsx

Use the `@DataProvider` annotation in TestNG tests to pull data dynamically.

---

##  Contribution

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

##  License

This project is licensed under the MIT License - see the LICENSE file for details.

---

##  Acknowledgments

- SauceDemo (https://www.saucedemo.com/) for providing a great testing playground.
- ExtentReports (https://extentreports.com/) for elegant reporting.
- Apache POI (https://poi.apache.org/) for Excel handling.

---

