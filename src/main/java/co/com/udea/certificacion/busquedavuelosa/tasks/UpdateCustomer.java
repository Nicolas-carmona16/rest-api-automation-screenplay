package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class UpdateCustomer implements Task {

    private final int customerId;
    private final String name;
    private final String email;
    private final String phoneNumber;

    public UpdateCustomer(int customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String updateCustomerData = "{"
                + "\"name\": \"" + name + "\","
                + "\"email\": \"" + email + "\","
                + "\"phoneNumber\": \"" + phoneNumber + "\""
                + "}";
        actor.attemptsTo(
                Put.to("/customers/" + customerId)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(updateCustomerData)
                                .relaxedHTTPSValidation())
        );
    }

    public static UpdateCustomer withData(int customerId, String name, String email, String phoneNumber) {
        return Tasks.instrumented(UpdateCustomer.class, customerId, name, email, phoneNumber);
    }
}
