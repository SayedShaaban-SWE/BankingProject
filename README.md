<div align="center">
  <img src="https://github.com/user-attachments/assets/8ee04541-1bc0-4331-a5d4-1db7c5deceda" alt="Banking Automation" width="1000" height="250" />

# 🏦 Banking Project Automation 🚀
</div>

### 🌐 **[XYZ Banking Application](https://your-banking-app-url.com)**

---

## 📝 Framework & Technologies

* **Selenium WebDriver** (GUI Testing)
* **Rest Assured** (API Testing)
* **Java 17**
* **TestNG**
* **Cucumber BDD**
* **Maven**
* **Allure Reporting**
* **Log4j2**

---

## 🎨 Project Design

* **Page Object Model (POM)** design pattern
* **Behavior Driven Development (BDD)** with Cucumber
* **Data-Driven Testing** with CSV/JSON
* **Integrated Allure Reports** with screenshots on failure
* **Comprehensive Logging** with Log4j2
* **Parallel Execution** ready
* **CI/CD pipeline** compatible

---

## 🔊 This Project Includes:

* **📂 Folder for GitHub workflows**
* **📂 Folder for all pages**
* **📂 Folder for all tests**
* **📂 Folder for all test data**
* **📂 Folder for utilities**

---

## 🚧 Prerequisites

* **Java JDK 17** [Download](https://www.oracle.com/java/technologies/javase-downloads.html)
* **Maven 3.6+** [Install](https://maven.apache.org/install.html)
* **Chrome Browser** (latest version)
* **Allure CLI** [Get Started](https://docs.qameta.io/allure/#_get_started)
* **IntelliJ IDEA** (recommended) [Download](https://www.jetbrains.com/idea/download/)

---

## 🛠️ Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/banking-automation.git

2. **Import Project**:

   * Open IntelliJ IDEA**
   * Select File > Open and choose the project directory**
   * Import as Maven project**

3. **Install Dependencies**:
    ```bash
   mvn clean install

4. **Configure Environment**:
   * Update src/main/resources/config/config.properties
   * WebDriverManager handles browser drivers automatically
   
## 🚀 Running Tests
    ```bash
    mvn test -DsuiteXmlFile=src/test/resources/testng/testng.xml

## 🥒 Run Specific Feature (Cucumber)
    ```bash
    mvn test -Dcucumber.filter.tags="@TC1"

## 📊 Reporting
* **Allure Report**
    ```bash
    allure serve target/allure-results
  
*  **TestNG Report**
    * Automatically generated in target/surefire-reports

## 🔍 Test Data Management
* **GUI Tests: src/test/resources/testdata/customers-data.csv**
* **GUI Tests: src/test/resources/testdata/transactions-amounts-data.csv**
* **API Tests: src/test/resources/testdata/pet-store.json**

## ✨ Key Features
* **Hybrid Framework: Supports both GUI and API testing**

* **Comprehensive Logging: Detailed execution logs with Log4j2**

* **Failure Handling: Automatic screenshots on test failure**

* **Data-Driven: CSV and JSON test data support**

* **Parallel Execution: Ready for parallel test execution**

* **CI/CD Ready: GitHub Actions compatible**

## 📄 Sample Reports
* **Allure Report**
![Screenshot 2025-06-10 182524](https://github.com/user-attachments/assets/714d5619-1a6d-4970-ae8d-dd373de85192)

* **Cucumber Report**
![Screenshot 2025-06-10 182613](https://github.com/user-attachments/assets/6ad7c4a4-83c7-4b58-b620-face42c24edd)

* **testNG Report**
  ![Screenshot 2025-06-10 182832](https://github.com/user-attachments/assets/2ce00937-06ef-4bc7-9810-d52aeecb1d79)

## 🗃️ Documentation

* **[Selenium WebDriver](https://www.selenium.dev/documentation/)**
* **[TestNG Documentation](https://testng.org/doc/documentation-main.html)**
* **[Allure Report Documentation](https://docs.qameta.io/allure/)**

## 📝 Notes
* **Ensure stable internet connection for API tests**

* **Screenshots are automatically saved in target/screenshots on failure**

* **Detailed logs available in target/test.log**

* **For CI/CD integration, see GitHub Actions examples in .github/workflows**

<div align="center"> <br> <strong>Happy Testing! 🧪</strong> </div> ```
