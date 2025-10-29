# Contributing to UnifiedAutomationFramework

## PR Expectations

- Branch naming: `feature/<short-desc>` or `bugfix/<short-desc>`
- Short summary of change & reasoning in PR description
- Files added/modified listed
- How to run locally (commands)
- CI run result (link or artifact)
- Allure results attached or commands to generate
- No secrets committed
- Tests (features) added for new functionality
- Reviewer notes (any known limitations)

## Coding Style

- Java: Follow standard Java conventions (camelCase, etc.)
- Gherkin: Use Given/When/Then clearly
- Config: YAML for application.yml, JSON for capabilities/testdata
- Logging: Use Log4j2 logger instances

## Tests Required

- Add unit/sample feature tests for anything new
- Cover happy path, error cases, edge cases
- Use appropriate tags (@smoke, @regression, @api, @mobile, @desktop)
- Ensure thread safety for parallel execution

## Process

1. Create branch from `main`
2. Implement changes
3. Run tests locally: `mvn clean test`
4. Commit with clear message
5. Push and create PR
6. Wait for CI and review
7. Merge after approval
