package ind.gopinnath.geode.example.skill.webui.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ind.gopinnath.geode.example.skill.webui.helper.MasterLoader;

@WebListener
public class MasterDataLoadTrigger implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext context = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		MasterLoader loader = context.getBean(MasterLoader.class);
		loader.cacheMasterData();
	}

}
