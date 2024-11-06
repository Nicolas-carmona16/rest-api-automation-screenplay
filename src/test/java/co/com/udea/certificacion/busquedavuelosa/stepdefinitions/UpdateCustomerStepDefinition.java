package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.UpdateCustomer;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCustomerStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }
    @Given("I am connected to the service")
    public void iAmConnectedToTheService() {
        usuario.attemptsTo(ConnectTo.theService());
    }
    @When("I update a customer with an existing id")
    public void iUpdateACustomerWithAnExistingId() {
        usuario.attemptsTo(UpdateCustomer.withId());
    }
    @Then("The response status code should be 200")
    public void theResponseStatusCodeShouldBe200() {
        usuario.should(
                seeThatResponse(response -> response.statusCode(200))
        );
    }
    @Then("The response should contain the updated customer data")
    public void theResponseShouldContainTheUpdatedCustomerData() {
        usuario.should(
                seeThatResponse("The response should contain the updated customer data",
                        response -> response.body("id", equalTo(44))
                                .body("name", equalTo("Jane Doe"))
                                .body("email", equalTo("jane.doe@example.com"))
                                .body("phoneNumber", equalTo("0987654321"))
                )
        );
    }
}
