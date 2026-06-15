# 🚀 Enterprise GUI Automation Framework (Capstone Project)


## 📌 Overview
This repository contains my Capstone Project: a highly robust, scalable, and data-driven end-to-end UI automation framework. Built using **Java, Selenium WebDriver, and TestNG**, this framework follows the **Page Object Model (POM)** design pattern and is fully integrated into a **Jenkins CI/CD pipeline** with automated **ExtentReports** generation.

## 🌟 Key Features & Advanced Handling
This suite successfully automates complex, real-world UI challenges, including:
* **Shadow DOM Piercing:** Utilizes CSS selectors and `getShadowRoot()` to interact with encapsulated, hidden HTML elements.
* **Complex Web Tables:** Dynamic extraction and validation across Static, Dynamic (shifting columns), and Pagination-based web tables.
* **API Broken Link Verification:** Bypasses slow UI clicking by using Java's `HttpURLConnection` to instantly ping and validate HTTP response codes (400, 404, 500) in the background.
* **File Uploads & Tab Management:** Handles native OS file uploads seamlessly via standard paths and successfully manages multi-window/tab switching.
* **Advanced Interactions:** Automates Drag-and-Drop, Mouse Hover actions, hidden Dropdowns, and dynamically injected Forms.

## 🏗️ Framework Architecture (POM)

```text
📦 Capstone_Project
 ┣ 📂 src/test/java               
 ┃ ┣ 📂 base
 ┃ ┃ ┗ ☕ BaseTest.java           # WebDriver initialization & teardown
 ┃ ┣ 📂 pages
 ┃ ┃ ┗ ☕ GUIPage.java            # Page Object Model (Locators & Methods)
 ┃ ┣ 📂 tests
 ┃ ┃ ┗ ☕ GUIAutomationTest.java  # TestNG Executable Test Cases
 ┃ ┗ 📂 utils
 ┃   ┣ ☕ ExtentReportManager.java# HTML reporting engine configuration
 ┃   ┣ ☕ ScreenshotUtil.java     # Automated failure evidence capture
 ┃   ┗ ☕ TestListener.java       # Bridges live execution data to the report
 ┣ 📂 src/test/resources          # Test Data (e.g., sample.txt for uploads)
 ┣ 📂 reports                     # Output directory for HTML Dashboards
 ┣ 📄 pom.xml                     # Maven dependency management
 ┗ ⚙️ testng.xml                  # Test suite runner & parameterization
