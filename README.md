# MyDemoApp Test Automation Framework

[![Run Appium Tests on BrowserStack (SDK 24)](https://github.com/valukonyte/My_demo_app/actions/workflows/manual.yml/badge.svg)](https://github.com/valukonyte/My_demo_app/actions/workflows/manual.yml)

Automated UI tests for **MyDemoApp** using **Java, JUnit 5, Appium, and Maven**.  
The framework is designed as a **portfolio project** to demonstrate skills in **mobile automation and CI/CD**.  
All tests are executed on **BrowserStack emulators and real devices**, triggered automatically via **GitHub Actions**.

---

### Features
- **AppiumDriver-based** automation (Android/iOS) built on the **Selenium WebDriver protocol**.
- **Page Object Model (POM)** for maintainable, reusable code.
- **Parameterized JUnit 5 tests** with external CSV test data.
- **Dynamic test data generation** using `Faker` (names, addresses, payment details).
- **Integrated CI/CD**: tests run on every push or pull request via GitHub Actions.

---

### Running the Tests

This project uses a **pre-configured app (stored in my BrowserStack account)** and is set up to run in **CI only**.

- Anyone can **view the test runs and results** in the [GitHub Actions tab](https://github.com/valukonyte/My_demo_app/actions).
- The project is not configured for direct execution on other accounts (since the app ID `bs://35bc001c4dd6874cd42b2eb634aaf50302153d19` is linked to my BrowserStack workspace).

---

### Why This Project?

This framework demonstrates:
- Building a **scalable automation framework** for mobile apps using **Appium and JUnit 5**.
- Applying **best practices** (POM, reusable utilities, data-driven testing).
- **Cloud-based testing** on BrowserStack with **CI/CD integration** using GitHub Actions.
