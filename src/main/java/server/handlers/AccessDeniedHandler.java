package server.handlers;

import io.javalin.Javalin;
import server.exception.AccessDeniedException;

// MANEJANDO LA EXCEPCION AccessDeniedException
public class AccessDeniedHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        // atrapa la excepcion personalizada
        app.exception(AccessDeniedException.class, (e, context) -> {
            // podemos redireccionar
            context.render("401.hbs");
        });
    }
}
