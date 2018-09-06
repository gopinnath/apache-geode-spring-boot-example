- **[Overview](#overview)**
- **[Modules and Details](#modules)** 
- **[Softwares Needed](#swneed)** 
- **[Steps to execute](#stepsexec)**

## <a name="overview"></a>Overview

apache-geode-spring-boot-example is Example Web Client Application that consumes Apache Geode as remote Cache and uses Apache Geode as Spring Boot Data Application. The application code is tested against Java SE 8, Apache Geode 1.0.0-incubating.M3, Maven 3.3.1 and Java EE 7.

## <a name="modules"></a>Modules and Details

geode-example-schema : The Schema Module contains XSD representation of the domain model, this module also compiles the data model into Java Type using JAXB API and packages into a JAR file.
geode-example-geode : The Spring Boot application that serves as Apache Geode Distributed Cache. This module is dependent on geode-example-schema for providing the Java Type that can be stored in cache.
geode-example-web : This module is JAX-RS based REST API implementation and Servlet Context Listener to uploading the Skill Data. This module is dependent on geode-example-schema for providing the Java Type that can be stored in cache.

## <a name="swneed"></a>Softwares Needed

1) Java SE 8
2) JBoss Developer Studio 9.x
3) Apache Geode 1.0.0-incubating.M3
4) Wildfly 9.2 or above / EAP 6.4 or Above.
5) Maven 3.3.1 (Optional). Might also work with embedded maven from JBoss Developer Studio.

## <a name="stepsexec"></a>Steps to execute

- Step 1 : Install and Setup Apache Geode. The code is tested against binary of version 1.0.0-incubating.M3, download apache-geode-1.0.0-incubating.M3.zip. Unzip the content to folder (Let's assume "D:\apache-geode-xxx"). Make sure that JAVA_HOME and PATH environment variables are properly set. Create environment variable GEMFIRE with the path to the folder where Apache Geode was unzipped (in our example "D:\apache-geode-xxx"). Update PATH environment variable to include Apache Geode path to bin folder. Run command "gfsh" from command prompt to validate (should get command window with "gfsh>" prompt) that environment setup is complete. 
- Step 2 : Maven Clean and Install. Run mvn clean install on the root/parent folder of the code. 
- Step 3 : Import Code into "JBoss Developer Studio" workspace as Maven Project. 
- Step 4 : Setup Run Configuration for geode-example-geode module. Open AppMain java file, this file is class with main method in it and can be run as Java Application. Right click on the file and go to "Run Configurations..." option and add "<<GEMFIRE Folder>>\lib\gfsh-dependencies.jar" into classpath (with "Add External Jars" option).
- Step 5 : Update the Port for application server. There are certain ports that are common to both Geode and Application server. It would be better if the port number of ports used by application server is updated. I added digit "1" before all the port that was being configured in "standalone.xml". 
- Step 6 : Execute the application. Run the AppMain java file with the run configuration outlined in Step 4. Start the application server and add geode-example-web application to it. The Skill Data is read from properties file and inserted into Apace Geode during the application start. Following are the REST API paths using which you query the skill data from Apache Geode:
```
	http://localhost:18080/geode-example-web/service/rest/skills
	http://localhost:18080/geode-example-web/service/rest/skill/{skillname}
```
