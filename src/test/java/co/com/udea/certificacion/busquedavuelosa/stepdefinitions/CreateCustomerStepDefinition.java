package co.com.udea.certificacion.busquedavuelosa.stepdefinitions;

import co.com.udea.certificacion.busquedavuelosa.tasks.ConnectTo;
import co.com.udea.certificacion.busquedavuelosa.tasks.CreateCustomer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

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
    @When("I create a customer with the following data:")
    public void iCreateACustomerWithTheFollowingData(DataTable customerDataTable) {
        List<Map<String, String>> customerDataList = customerDataTable.asMaps(String.class, String.class);
        Map<String, String> customerData = customerDataList.get(0);

        String name = customerData.get("name");
        String email = customerData.get("email");
        String phoneNumber = customerData.get("phoneNumber");

        usuario.attemptsTo(CreateCustomer.withData(name, email, phoneNumber));
    }
    @Then("the response body should contain the created customer's ID")
    public void theResponseBodyShouldContainTheCreatedCustomersId() {
        usuario.should(
                seeThatResponse("The response should contain the customer's ID",
                        response -> response.body("id", Matchers.notNullValue())
                )
        );
    }
    @And("I should see a response status code 201")
    public void iShouldSeeResponseStatusCode201() {
        usuario.should(
                seeThatResponse(response -> response.statusCode(201)
                )
        );
    }
}
