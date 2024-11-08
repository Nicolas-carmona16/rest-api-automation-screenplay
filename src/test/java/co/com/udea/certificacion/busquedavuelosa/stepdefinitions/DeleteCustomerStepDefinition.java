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
import org.hamcrest.Matchers;

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
    @When("I delete a customer with id {int}")
    public void iDeleteACustomerWithId(int customerId) {
        usuario.attemptsTo(DeleteCustomer.withId(customerId));
    }
    @Then("The delete response status code should be {int}")
    public void theDeleteResponseStatusCodeShouldBe(int expectedStatusCode) {
        usuario.should(seeThatResponse(response -> response
                .statusCode(expectedStatusCode))
        );
    }
    @Then("The delete response should contain an error message {string}")
    public void theDeleteResponseShouldContainAnErrorMessage(String expectedErrorMessage) {
        usuario.should(seeThatResponse(response -> response
                        .body(Matchers.equalTo(expectedErrorMessage))
        ));
    }
}
