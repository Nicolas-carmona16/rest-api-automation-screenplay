package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.ConsumerThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.hasItems;

public class GetCustomersStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
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
        usuario.should(seeThatResponse(response -> response.statusCode(200)
                .body("[1].phoneNumber", Matchers.equalTo("34796521862222"))
        ));
    }

}
