package ind.gopinnath.geode.example.skill.webui;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ind.gopinnath.geode.example.skill.webui.endpoint.QueryEndPoint;

@ApplicationPath("service")
public class AppActivator extends Application{
	
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(QueryEndPoint.class);
        return s;
    }
    
}
