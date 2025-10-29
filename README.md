# UnifiedAutomationFramework

A unified automation framework using Cucumber (Gherkin) + JUnit 5, Selenium 4, Appium, RestAssured, OpenCV/AShot for image diffs, Allure reporting, and Log4j2 logging.

## Prerequisites

- Java 17
- Maven 3.8+
- Chrome browser (for web tests)
- Appium server (for mobile tests)
- WinAppDriver (for desktop tests on Windows)

## Run Commands

- Build: `mvn clean compile`
- Run all tests: `mvn clean test`
- Run specific tags: `mvn test -Dcucumber.filter.tags="@smoke"`
- Generate Allure report: `mvn allure:report`

## CI Instructions

- Push to branch and create PR.
- CI will run `mvn clean test` and upload Allure results.

## Adding New Tests

- Add .feature files in `src/test/resources/features/`
- Add step definitions in `src/test/java/`
- Update config in `src/test/resources/config/application.yml`

## Baseline Image Approval Flow

- Run tests; if baseline absent, image is saved in `baselines/`.
- Review and approve baselines before committing.
