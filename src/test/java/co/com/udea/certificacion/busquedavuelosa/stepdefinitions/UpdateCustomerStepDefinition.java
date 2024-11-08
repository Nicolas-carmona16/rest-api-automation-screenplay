package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.UpdateCustomer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCustomerStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
        RestAssured.registerParser("text/plain", Parser.JSON);
    }
    @Given("I am connected to the service")
    public void iAmConnectedToTheService() {
        usuario.attemptsTo(ConnectTo.theService());
    }
    @When("I update a customer with ID {int} with the following data:")
    public void iUpdateACustomerWithIdWithTheFollowingData(int customerId, DataTable customerDataTable) {
        List<Map<String, String>> customerDataList = customerDataTable.asMaps(String.class, String.class);
        Map<String, String> customerData = customerDataList.get(0);

        String name = customerData.get("name");
        String email = customerData.get("email");
        String phoneNumber = customerData.get("phoneNumber");

        usuario.attemptsTo(UpdateCustomer.withData(customerId, name, email, phoneNumber));
    }
    @Then("The update response status code should be {int}")
    public void theUpdateResponseStatusCodeShouldBe(int expectedStatusCode) {
        usuario.should(seeThatResponse(response -> response
                .statusCode(expectedStatusCode))
        );
    }
    @Then("The response should contain the updated customer data")
    public void theResponseShouldContainTheUpdatedCustomerData() {
        usuario.should(seeThatResponse(response -> response
                        .body("id", equalTo(1))
                        .body("name", equalTo("Jane Doe"))
                        .body("email", equalTo("jane.doe@example.com"))
                        .body("phoneNumber", equalTo("0987654321"))
        ));
    }

    @Then("The update response should contain an error message {string}")
    public void theUpdateResponseShouldContainAnErrorMessage(String expectedErrorMessage) {
        usuario.should(seeThatResponse(response -> response
                        .body(equalTo(expectedErrorMessage))
        ));
    }
}
