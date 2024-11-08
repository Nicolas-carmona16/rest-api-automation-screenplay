package co.com.udea.certificacion.busquedavuelosa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class BringA implements Task {

    private final int customerId;

    public BringA(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/customers/" + customerId)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .relaxedHTTPSValidation())
        );
    }

    public static BringA customerById(int customerId) {
        return Tasks.instrumented(BringA.class, customerId);
    }
}
