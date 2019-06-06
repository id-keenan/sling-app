# Sling Application
This project is created from multiple Sling Maven Archetypes combined into a multi-module project.
Archetypes used:
* [sling-servlet-archetype](https://github.com/apache/sling-servlet-archetype)
* [sling-initial-content-archetype](https://github.com/apache/sling-initial-content-archetype)

# Structure
The project is broken down simply into the two following modules:
* `core`
  * This module contains the back-end Java files which help with interacting with the JCR in a meaningful way. It is where basic Java POJOs and Servlet will live.
* `ui`
  * This module is to store the actual view components (/libs/sling-app) and any initial content necessary (/content/sling-app). This is where the comopnent files live as well as any front-end CSS/JS at /etc/clientlibs/sling-app.

# Usage
##### System Requirements
* Java 8
* Maven 3.3.9+
* [Sling Standalone 11](https://sling.apache.org/downloads.cgi)

##### Initial Setup
* Place the Sling Standalone JAR downloaded into a directory to use as a workspace.
* Run the JAR with the simple `java -jar org.apache.sling.starter-11.jar` command
  * Note: You can enable debugging in your server by adding `-agentlib:jdwp=transport=dt_socket,address=8005,server=y,suspend=n` between `java` and `-jar` in the above command
* Once the server has started, login as admin at [http://localhost:8080/system/sling/form/login](http://localhost:8080/system/sling/form/login)

##### Building the bundles
The project is compiled and deployed using Maven.
Running the below command in the root of the project will result in a build and deployment of both `core` and `ui` bundles:
```
mvn clean install -PautoInstallBundle
```
You can run the same command in the module specific directories as well to build and deploy just the single module.

##### Miscellaneous
* For using JSON Viewer servlet put a path in your browser with `.jsonviewer.json` - e.g. [http://localhost:8080/content.jsonviewer.json](http://localhost:8080/content.jsonviewer.json)
  * I recommend a chrome extension for JSON formatting. I use [this one](https://chrome.google.com/webstore/detail/json-formatter/bcjindcccaagfpapjjmafapmmgkkhgoa?hl=en).
* Some of the resources in the Sling server are protected and require you to login. If you cannot view [http://localhost:8080/apps](http://localhost:8080/apps) then you are not logged in and should do so.
