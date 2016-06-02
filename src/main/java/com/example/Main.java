package com.example;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.postgresql.PostgreSQLJPAFraction;

public class Main {
  public static void main(String[] args) throws Exception {
    Container container = new Container();

    // Prevent JPA Fraction from installing it's default datasource fraction
    container.fraction(new PostgreSQLJPAFraction()
        .inhibitDefaultDatasource()
    );

    container.start();

    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addClasses(Employee.class);
    deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
    deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/load.sql", Main.class.getClassLoader()), "classes/META-INF/load.sql");
    deployment.addResource(com.example.EmployeeResource.class);
    deployment.addAllDependencies();

    container.deploy(deployment);
  }
}