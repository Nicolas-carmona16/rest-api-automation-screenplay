package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.CreateCustomer;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CreateCustomerStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }
    @Given("I am connected to the customer service")
    public void iAmConnectedToTheCustomerService() {
        usuario.attemptsTo(ConnectTo.theService());
    }
    @When("I create a customer")
    public void iCreateACustomer() {
        usuario.attemptsTo(CreateCustomer.withData());
    }
    @Then("the response body should contain the created customer's ID")
    public void theResponseBodyShouldContainTheCreatedCustomersId() {
        usuario.should(
                seeThatResponse("The response should contain the customer's ID",
                        response -> response.body("id", Matchers.notNullValue())
                )
        );
    }
    @Then("I should see a response status code 201")
    public void iShouldSeeResponseStatusCode201() {
        usuario.should(
                seeThatResponse(response -> response.statusCode(201)
                )
        );
    }
}
