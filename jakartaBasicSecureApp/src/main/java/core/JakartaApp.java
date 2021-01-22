
package core;

/**
 *
 * @author JOrtiz
 */
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("")
@BasicAuthenticationMechanismDefinition(realmName = "userRealm")
@ApplicationScoped
@DeclareRoles({"ADMIN", "MANAGER", "USER"})  // You need to indicate all roles that are used by the app
public class JakartaApp extends Application {
}
