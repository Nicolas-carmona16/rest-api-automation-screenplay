package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteCustomer implements Task {

    private static final int CUSTOMER_ID = 39;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/customers/" + CUSTOMER_ID)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .relaxedHTTPSValidation())
        );
    }

    public static DeleteCustomer withId() {
        return Tasks.instrumented(DeleteCustomer.class);
    }
}
