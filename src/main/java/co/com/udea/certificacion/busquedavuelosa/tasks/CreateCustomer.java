package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateCustomer implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        String customerDataJson = "{"
                + "\"name\": \"John Doe\","
                + "\"email\": \"john@example.com\","
                + "\"phoneNumber\": \"1234567890\""
                + "}";

        actor.attemptsTo(
                Post.to("/customers")
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(customerDataJson)
                        )
        );
    }

    public static CreateCustomer withData() {
        return Tasks.instrumented(CreateCustomer.class);
    }
}
