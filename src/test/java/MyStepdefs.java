import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;


public class MyStepdefs {
    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        Selenide.open("https://qa-course-01.andersenlab.com/login");
    }

    @When("the user enters incorrect email {string} and password {string}")
    public void userEntersIncorrectCredentials(String email, String password) {
        $("#root > div > div > div > form > div > div:nth-child(1) > label > input").setValue(email);
        $("#root > div > div > div > form > div > div:nth-child(2) > label > input").setValue(password);
        $("#root > div > div > div > form > button").click();
    }

    @Then("an error message should be displayed")
    public void errorMessageIsDisplayed() {
        $("#root > div > div > div > form > div > div:nth-child(1) > div > span").shouldBe(Condition.visible);
    }

    @When("the user enters valid email {string} and password {string} and submits the form")
    public void userEntersValidCredentials(String email, String password) {
        $("#root > div > div > div > form > div > div:nth-child(1) > label > input").setValue(email);
        $("#root > div > div > div > form > div > div:nth-child(2) > label > input").setValue(password);
        $("#root > div > div > div > form > button").click();
    }

    @Then("the user should be redirected to the main page")
    public void userRedirectedToMainPage() {
        Selenide.Wait().until(webDriver ->
                webDriver.getCurrentUrl().equals("https://qa-course-01.andersenlab.com/")
        );
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(currentUrl).isEqualTo("https://qa-course-01.andersenlab.com/");
    }

    @When("the user tries to access the registration page")
    public void userTriesToAccessRegistrationPage() {
        Selenide.open("https://qa-course-01.andersenlab.com/registration");
    }


    @When("the user enters invalid first name {string} and last name {string}")
    public void userEntersInvalidFirstNameAndLastName(String firstName, String lastName) {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#registerButton").click();
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        Selenide.open("https://qa-course-01.andersenlab.com/login");
        boolean log = WebDriverRunner.getWebDriver().getCurrentUrl().equals("https://qa-course-01.andersenlab.com/login");
        if (log) {
            return;
        }
        $("#root > div > div > div > form > div > div:nth-child(1) > label > input").setValue("subsatura@gmail.com");
        $("#root > div > div > div > form > div > div:nth-child(2) > label > input").setValue("21121488");
        $("#root > div > div > div > form > button").click();
        Selenide.Wait().until(webDriver ->
                webDriver.getCurrentUrl().equals("https://qa-course-01.andersenlab.com/")
        );
    }
}
