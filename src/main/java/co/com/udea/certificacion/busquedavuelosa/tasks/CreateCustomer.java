package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateCustomer implements Task {

    private final String name;
    private final String email;
    private final String phoneNumber;

    public CreateCustomer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String customerDataJson = "{"
                + "\"name\": \"" + name + "\","
                + "\"email\": \"" + email + "\","
                + "\"phoneNumber\": \"" + phoneNumber + "\""
                + "}";

        actor.attemptsTo(
                Post.to("/customers")
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(customerDataJson)
                                .relaxedHTTPSValidation()
                        )
        );
    }

    public static CreateCustomer withData(String name, String email, String phoneNumber) {
        return Tasks.instrumented(CreateCustomer.class, name, email, phoneNumber);
    }
}
