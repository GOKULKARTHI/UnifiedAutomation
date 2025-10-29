# UnifiedAutomationFramework

A unified automation framework using Cucumber (Gherkin) + JUnit 5, Selenium 4, Appium, RestAssured, OpenCV/AShot for image diffs, Allure reporting, and Log4j2 logging.

## Prerequisites

- Java 17
- Maven 3.8+
- Chrome browser (for web tests)
- Appium server (for mobile tests)
- WinAppDriver (for desktop tests on Windows)
- Node.js and npm (for Appium setup in CI)

## Run Commands

- Build: `mvn clean compile`
- Run all tests: `mvn clean test`
- Run web and API tests only: `mvn clean test -Dcucumber.filter.tags="not @desktop and not @mobile"`
- Run mobile and desktop tests: `mvn test -Dcucumber.filter.tags="@mobile or @desktop"`
- Run specific tags: `mvn test -Dcucumber.filter.tags="@smoke"`
- Generate Allure report: `mvn allure:report`
- Serve Allure report: `mvn allure:serve`

## CI Instructions

- Push to branch and create PR.
- CI runs web+api tests by default and uploads `allure-results-web-api` artifact.
- To run mobile/desktop tests, add 'mobile-desktop' label to PR or trigger workflow_dispatch.
- Artifacts: `allure-results-web-api` and `allure-results-mobile-desktop`.

## Adding New Tests

- Add .feature files in `src/test/resources/features/`
- Add step definitions in `src/test/java/com/unifiedautomation/stepdefinitions/`
- Update config in `src/test/resources/config/application.yml`
- For API tests, add JSON data in `src/test/resources/testdata/`
- For mobile/desktop, update `capabilities.json` and config.

## Baseline Image Approval Flow

- Run tests; if baseline absent, image is saved in `baselines/` and test marked pending.
- Review and approve baselines before committing.
- On mismatch, `diff.png` is created in screenshots.

## Device Farm Integration

- For BrowserStack/SauceLabs: Add credentials to GitHub Secrets (see `secrets.example`).
- Switch capabilities in `application.yml` or `capabilities.json` to use cloud URLs.
- Update config to point to cloud hub.

## Troubleshooting

- **Tests fail locally but pass in CI**: Check environment differences (browsers, devices).
- **Screenshots not attaching**: Ensure Allure is configured; check logs for errors.
- **Parallel tests interfere**: Verify ThreadLocal usage in DriverManager.
- **Mobile/desktop tests skip**: Ensure servers (Appium, WinAppDriver) are running.
- **Re-run failed tests**: Use `mvn test -Dcucumber.filter.tags="@failed"` or rerun specific scenarios.
- **Logs location**: Console for Log4j2, Allure attachments for screenshots/requests.
