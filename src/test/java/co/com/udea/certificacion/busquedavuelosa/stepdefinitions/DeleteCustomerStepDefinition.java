package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.DeleteCustomer;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteCustomerStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }
    @Given("I am connected to the customer service 2")
    public void iAmConnectedToTheCustomerService2() {
        usuario.attemptsTo(ConnectTo.theService());
    }
    @When("I delete a customer with an existing id")
    public void iDeleteACustomerWithAnExistingId() {
        usuario.attemptsTo(DeleteCustomer.withId());
    }
    @Then("the response status code should be 204")
    public void theResponseStatusCodeShouldBe204() {
        usuario.should(
                seeThatResponse(response -> response.statusCode(204))
        );
    }
}
