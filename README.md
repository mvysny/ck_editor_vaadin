The CK Editor 4 Wrapper for Vaadin 7
====================================

This project hosts a Vaadin component which nests the [CK Editor 4](https://ckeditor.com/ckeditor-4/) JavaScript component.

The project also includes a simple UI which demonstrates the usage of the component. This app is a demonstration project for the
component that only requires a Servlet 3.0 container to run.

Workflow
========

To compile the entire project, run `mvn package`.

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Using with your project
=======================

The project is unfortunately not yet deployed in any external Maven repo. You
can run `mvn package` to create a `jar` file in the `target/` directory and drop that jar into your project.

You can also simply copy the `CKEditor*.java` files into your project. You'll need the CK Editor sources itself,
they are located in `src/main/resources/` in both `VAADIN/ckeditor` and `com/github/ckeditor` folders, respectively.


Client-Side compilation
-------------------------

All code is written in JavaScript and no widgetset recompilation is needed. You can
simply include all the necessary files into your project as-is.
