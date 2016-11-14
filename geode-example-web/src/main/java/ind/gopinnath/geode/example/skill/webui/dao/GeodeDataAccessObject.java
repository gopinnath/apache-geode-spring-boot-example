package ind.gopinnath.geode.example.skill.webui.dao;

import java.util.List;

import org.springframework.data.gemfire.GemfireTemplate;

import ind.gopinnath.geode.example.model.SkillType;
import ind.gopinnath.geode.example.model.Skills;

public class GeodeDataAccessObject {

	private GemfireTemplate geodeTemplate;
	
	public GemfireTemplate getGeodeTemplate() {
		return geodeTemplate;
	}

	public void setGeodeTemplate(GemfireTemplate geodeTemplate) {
		this.geodeTemplate = geodeTemplate;
	}
	
	public void insertSkills(Skills skills)
	{
		skills.getSkill().forEach(skill -> geodeTemplate.put(skill.getSkillName(), skill));
	}
	
	public SkillType getSkill(String name)
	{
		return geodeTemplate.get(name);
	}
	
	public Skills getSkills(){
		
		Skills skills = new Skills();
		List<Object> skillList = geodeTemplate.query("Select * from /skilldata").asList();
		skillList.stream().forEach(skill -> {
									if(skill instanceof SkillType)
									{
										skills.getSkill().add((SkillType)skill);
									}
								}
							);
		return skills;
	}
	
}
