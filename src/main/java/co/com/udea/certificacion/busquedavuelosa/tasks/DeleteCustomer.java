package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteCustomer implements Task {

    private final int customerId;

    public DeleteCustomer(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/customers/" + customerId)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .relaxedHTTPSValidation())
        );
    }

    public static DeleteCustomer withId(int customerId) {
        return Tasks.instrumented(DeleteCustomer.class, customerId);
    }
}
