package ind.gopinnath.geode.example.skill.webui.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import ind.gopinnath.geode.example.model.SkillCategoryType;
import ind.gopinnath.geode.example.model.SkillType;
import ind.gopinnath.geode.example.model.Skills;

import ind.gopinnath.geode.example.skill.webui.dao.GeodeDataAccessObject;

public class MasterLoader {
	
	private static Logger LOGGER = SkillLogger.getLogger(MasterLoader.class);

	
	public static final String MASTERDATA_FILENAME = "masterdata.properties"; 
	
	public static final String MASTERDATA_KEY = "masterrecords";
	
	public static final String RECORD_DELIM = "\\^";
	public static final String COLUMN_DELIM = "\\|";
	
	public static final int SKILLNAME_INDEX = 0;
	public static final int CATEGORYNAME_INDEX = 1;
	

	
	private GeodeDataAccessObject dataAccessObject;
	
	public GeodeDataAccessObject getDataAccessObject() {
		return dataAccessObject;
	}

	public void setDataAccessObject(GeodeDataAccessObject dataAccessObject) {
		this.dataAccessObject = dataAccessObject;
	}

	private static MasterLoader loader;
	
	private MasterLoader()
	{
		
	}
	
	public void cacheMasterData()	{
		
		
		LOGGER.log(Level.INFO,"Starting to Load");

		if(!loader.loaded())
		{
			Properties properties = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			try(final InputStream stream = classLoader
	                .getResourceAsStream(MASTERDATA_FILENAME))	{
				
				properties.load(stream);
				Skills skills = loader.buildSkillsFromProperty(properties.getProperty(MASTERDATA_KEY)); 
				dataAccessObject.insertSkills(skills);
				LOGGER.log(Level.INFO,"Cached");
			} catch (IOException exception) {
				LOGGER.log(Level.WARNING," persistMasterData Failed ",exception);
				throw new RuntimeException(" persistMasterData Failed ");
			}
		}
	}
	
	private Skills buildSkillsFromProperty(String skillMasterData)	{
		Skills skills = new Skills();
		for(String stringSkill : skillMasterData.split(RECORD_DELIM))
		{
			skills.getSkill().add(buildSkill(stringSkill));
		}
		return skills;
	}
	
	private SkillType buildSkill(String stringSkill)	{
		SkillType skill = new SkillType();
		String[] skillSplit = stringSkill.split(COLUMN_DELIM);
		
		skill.setSkillName(skillSplit[SKILLNAME_INDEX]);
		skill.setSkillCategory(buildSkillCategory(skillSplit[CATEGORYNAME_INDEX]));
		
		return skill;
	}
	
	private SkillCategoryType buildSkillCategory(String categoryName)	{
		SkillCategoryType category = new SkillCategoryType();
		category.setSkillCategoryName(categoryName);
		return category;
	}
	
	public static MasterLoader getInstance()
	{
		if(loader == null) {
			loader = new MasterLoader();
		}
		return loader;
	}

	public boolean loaded()
	{
		return false;
	}
}
