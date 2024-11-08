package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.BringA;
import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.ConsumerThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetCustomersStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
        RestAssured.registerParser("text/plain", Parser.TEXT);
    }

    @Given("I am connect to capacities of the service")
    public void iAmConnectToCapacitiesOfTheService() {
        usuario.attemptsTo(ConnectTo.theService());
    }
    @When("I get the information of the store")
    public void iGetTheInformationOfTheStore() {
        usuario.attemptsTo(ConsumerThe.service());
    }
    @Then("I can see all information about the customers")
    public void iCanSeeAllInformationAboutTheCustomers() {
        usuario.should(seeThatResponse(response -> response
                .body("[0].name", Matchers.equalTo("John Doe"))
        ));
    }
    @Then("The get response status code should be {int}")
    public void theGetResponseStatusCodeShouldBe(int expectedStatusCode) {
        usuario.should(seeThatResponse(response -> response
                .statusCode(expectedStatusCode))
        );
    }
    @When("I get the information of the customer with ID {int}")
    public void iGetTheInformationOfTheCustomerWithID(int customerId) {
        usuario.attemptsTo(BringA.customerById(customerId));
    }
    @Then("I can see the customer's name as {string}")
    public void iCanSeeTheCustomerNameAs(String expectedName) {
        usuario.should(seeThatResponse(response -> response
                .body("name", Matchers.equalTo(expectedName))
        ));
    }
    @Then("The get response should contain an error message {string}")
    public void theGetResponseShouldContainAnErrorMessage(String expectedErrorMessage) {
        usuario.should(seeThatResponse(response -> response
                .body(Matchers.equalTo(expectedErrorMessage))
        ));
    }
}
