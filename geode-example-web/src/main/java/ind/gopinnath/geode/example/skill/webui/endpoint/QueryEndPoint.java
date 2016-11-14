package ind.gopinnath.geode.example.skill.webui.endpoint;


import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.web.context.support.WebApplicationContextUtils;

import ind.gopinnath.geode.example.model.SkillType;
import ind.gopinnath.geode.example.model.Skills;
import ind.gopinnath.geode.example.skill.webui.dao.GeodeDataAccessObject;

@Stateless
@Path("/rest")
public class QueryEndPoint {
	
	@Context 
	private ServletContext context; 
	
	private GeodeDataAccessObject dataAccessObject;
	
	@PostConstruct
	public void initialize()
	{
		dataAccessObject = WebApplicationContextUtils.getWebApplicationContext(context).getBean(GeodeDataAccessObject.class);
	}
	
	@Produces({MediaType.APPLICATION_JSON})
	@GET
	@Path("/skills")
	public Response getAllSkills(@Context UriInfo uriInfo) {
		Skills skills = dataAccessObject.getSkills();
		return Response.ok(skills).build();
	}
	
	
	@Produces({MediaType.APPLICATION_JSON})
	@GET
	@Path("/skills/{skillname}")
	public Response getSkill(@PathParam(value = "skillname") String skillname) {
		SkillType skill = dataAccessObject.getSkill(skillname);
		Response response = Response.ok(skill).build();
		for(Link link : response.getLinks() )
		{
			System.out.println(link.getRels());
		}
		return response;
	}
	
}
