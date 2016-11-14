package ind.gopinnath.geode.example.skill.webui.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SkillLogger {

	private Map<Class<?>,Logger> loggerMap;

	private static SkillLogger selfInstance;
	
	private SkillLogger()
	{
		loggerMap = new HashMap<Class<?>, Logger>();
	}
	
	private static SkillLogger getInstance()
	{
		if(selfInstance == null)
		{
			selfInstance = new SkillLogger();
		}
		return selfInstance;
	}
	
	public static Logger getLogger(Class<?> classObj)
	{
		if(!getInstance().loggerMap.containsKey(classObj)){
			
			getInstance().loggerMap.put(
					classObj, 
					Logger.getLogger(classObj.getCanonicalName()));
			
		}
		return getInstance().loggerMap.get(classObj);
	}
}
