package server.middlewares;

import dominio.actores.TipoRol;
import dominio.dataBase.repositorios.UsuarioRepository;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.security.RouteRole;
import server.exception.AccessDeniedException;

import java.util.List;
import java.util.Set;

public class AuthMiddleware {

    public static void apply(JavalinConfig config) {
        config.accessManager( ( (handler, context, routeRoles) -> {

            rutasPermitidas(handler,context,routeRoles);

        } ) );

    }

    private static void rutasPermitidas(Handler handler, Context context, Set<? extends RouteRole> routeRoles) throws Exception {
        boolean validas = context.path().equals("/login") || context.path().equals("/inicio") || context.path().equals("/registro");

        if (context.sessionAttribute("id_usuario") == null && !validas) {
            throw new AccessDeniedException();
        } else {
            // Déjalo pasar
            chequearAcceso(handler,context,routeRoles);
            handler.handle(context);
        }
    }

    private static void chequearAcceso(Handler handler, Context context, Set<? extends RouteRole> routeRoles) throws Exception {
        TipoRol userRole = getUserRoleType(context);

        if (routeRoles.isEmpty() || routeRoles.contains(userRole)) {
            // Déjalo pasar
            handler.handle(context);
        } else {
            throw new AccessDeniedException();
        }
    }

    private static TipoRol getUserRoleType(Context context) {
        if(context.sessionAttribute("id_usuario") == null){
            return null;
        }
        else{
            UsuarioRepository repository = new UsuarioRepository();
            return repository.findById( context.sessionAttribute("id_usuario") ).getRol().getTipo();
        }
        /*
        return context.sessionAttribute("tipo_rol") != null?
                TipoRol.valueOf(context.sessionAttribute("tipo_rol")) : null;

         */
    }

}
