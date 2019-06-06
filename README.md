# Sling Application
This project is created from multiple Sling Maven Archetypes combined into a multi-module project.
Archetypes used:
* [sling-servlet-archetype](https://github.com/apache/sling-servlet-archetype)
* [sling-initial-content-archetype](https://github.com/apache/sling-initial-content-archetype)

# Structure
The project is broken down simply into the two following modules:
* `core`
* `ui`

Each of the  two modules has its own purpose and varying output. The purpose of the `ui` module is to store the actual view components (/libs/sling-app) and any initial content necessary (/content/sling-app). This is where the comopnent files live as well as any front-end CSS/JS.
The `core` module contains the back-end Java files which help with interacting with the JCR in a meaningful way. It is where basic Java POJOs and Servlet will live.
