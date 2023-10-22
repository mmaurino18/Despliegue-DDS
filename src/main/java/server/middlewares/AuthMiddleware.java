package server.middlewares;

import io.javalin.config.JavalinConfig;
import server.exception.AccessDeniedException;

public class AuthMiddleware {

    public static void apply(JavalinConfig config) {
        config.accessManager(((handler, context, routeRoles) -> {
            // id_usuario
            if(context.sessionAttribute("id_usuario") == null && !context.path().equals("login")){
                throw new AccessDeniedException();
            }
            else{
                handler.handle(context);
            }
        }));


        /*
        config.accessManager(((handler, context, routeRoles) -> {
            TipoRol userRole = getUserRoleType(context);

            if(routeRoles.size() == 0 || routeRoles.contains(userRole)) {
                handler.handle(context);
            }
            else {
                throw new AccessDeniedException();
            }
        }));
         */
    }
/*
    private static TipoRol getUserRoleType(Context context) {
        return context.sessionAttribute("tipo_rol") != null?
                TipoRol.valueOf(context.sessionAttribute("tipo_rol")) : null;
    }
 */
}
