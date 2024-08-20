# fetch-sdet-exercise

I have used Divide and Conquer algorithm to find the fake gold bar. I have added three tests:
1. verifyFakeGoldBarIsFound - This test finds the fake gold bar, clicks on it, verifies if right alert message is displayed and prints the message, Weighings to console.
2. verifyAlertMessageForInvalidInput - This test inputs same numbers on both side of the scale and verifies if invalid input alert message is displayed.
3. verifyAlertMessageForIncorrectFakeBar - This test finds the fake gold bar and then clicks on wrong number to verify the Oops! Try again alert message.

Please find the detailed instructions on how to run the test below.

## Prerequisites

- JDK 8 or later. You can download it from [Oracle](https://www.oracle.com/java/technologies/downloads/)
- IntelliJ IDEA. You can download it from [JetBrain's Website](https://www.jetbrains.com/idea/download/)
- Git. You can download it from [Git's Website](https://git-scm.com/downloads)

## Getting Started

1. **Clone the repository:**
    - Open IntelliJ IDEA
    - Click on File -> New -> Project From Version Control
    - Copy and paste the [Repository URL](https://github.com/lkoyyana2156/fetch-sdet-exercise.git) in URL field
    - Click Clone and choose the directory to clone the project
2. **Refresh Gradle Dependencies**
    - Click on 'Gradle' tab on the right side of the IntelliJ IDEA window
    - Click on the 'Refresh' button or Right-click on the Project and click Refresh Gradle Dependencies
    - Wait for Gradle to download all dependencies.
3. **Run the Tests**
    - Navigate to src->test->Java->tests folder
    - Right click on FetchTest class and click 'Run FetchTest' to trigger the tests
    - Wait for the test execution to complete
