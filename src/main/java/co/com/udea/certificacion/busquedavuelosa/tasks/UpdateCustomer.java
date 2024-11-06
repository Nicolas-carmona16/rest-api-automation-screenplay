package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class UpdateCustomer implements Task {

    private static final int CUSTOMER_ID = 44;

    @Override
    public <T extends Actor> void performAs(T actor) {
        String updateCustomerData = "{"
                + "\"name\": \"Jane Doe\","
                + "\"email\": \"jane.doe@example.com\","
                + "\"phoneNumber\": \"0987654321\""
                + "}";
        actor.attemptsTo(
                Put.to("/customers/" + CUSTOMER_ID)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(updateCustomerData)
                                .relaxedHTTPSValidation())
        );
    }

    public static UpdateCustomer withId() {
        return Tasks.instrumented(UpdateCustomer.class);
    }
}
