# Runbook for UnifiedAutomationFramework

## Common Failures and Troubleshooting

### Tests Fail Locally but Pass in CI
- Check browser versions, drivers, or device availability.
- Ensure local environment matches CI (Ubuntu, Java 17).
- Verify config paths and credentials.

### Screenshots Not Attaching to Allure
- Check Allure plugin in pom.xml.
- Ensure Hooks.java attaches on failure.
- Logs: Look for Allure attachment errors.

### Parallel Tests Interfere
- Confirm DriverManager uses ThreadLocal.
- Check shared resources (files, configs) are thread-safe.
- Run with single thread: `mvn test -DforkCount=1`

### Mobile/Desktop Tests Skip
- Ensure Appium server running: `appium --address 127.0.0.1 --port 4723`
- For WinAppDriver: Start WinAppDriver.exe and note URL in config.
- Check capabilities.json and device/emulator availability.

### API Tests Fail
- Verify apiBaseUrl in application.yml.
- Check network/proxy settings.
- Logs: Request/response in console/Allure.

### Image Compare Issues
- Baseline absent: Image saved, test pending; approve and commit.
- Mismatch: Check diff.png in screenshots; adjust threshold in ImageCompareUtil.
- OpenCV/AShot errors: Ensure dependencies in pom.xml.

### Re-run Failed Tests
- Use tags: `mvn test -Dcucumber.filter.tags="@failed"`
- Rerun specific scenario: Update feature file and rerun.
- Check Allure history for patterns.

## Logs and Artifacts Location
- **Log4j2 logs**: Console output.
- **Allure results**: `allure-results/` directory.
- **Screenshots**: Attached to Allure reports.
- **Diff images**: `screenshots/` directory.
- **CI artifacts**: Download from GitHub Actions.

## Maintenance
- Update dependencies quarterly.
- Review baselines after UI changes.
- Monitor CI for flaky tests and adjust retries.
- Backup baselines and testdata regularly.
