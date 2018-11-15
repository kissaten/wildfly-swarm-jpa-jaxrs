package com.example;

// import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
// import io.thorntail.container.Container;
// import io.thorntail.datasources.DatasourcesFraction;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class Main {
  public static void main(String[] args) throws Exception {
    // Container container = new Container();

    // container.fraction(new DatasourcesFraction()
    //     .jdbcDriver("postgresql", (d) -> {
    //       d.driverClassName("org.postgresql.Driver");
    //       d.driverModuleName("org.postgresql");
    //     })
    //     .dataSource("MyPU", (ds) -> {
    //       ds.driverName("postgresql");
    //       ds.connectionUrl(System.getenv("JDBC_DATABASE_URL"));
    //       ds.userName(System.getenv("JDBC_DATABASE_USERNAME"));
    //       ds.password(System.getenv("JDBC_DATABASE_PASSWORD"));
    //     })
    // );

    // // Prevent JPA Fraction from installing it's default datasource fraction
    // container.fraction(new PostgreSQLJPAFraction()
    //     .inhibitDefaultDatasource()
    //     .defaultDatasource("jboss/datasources/MyPU")
    // );

    // container.start();
    Swarm swarm = new Swarm();

    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addClasses(Employee.class);
    deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
    deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/load.sql", Main.class.getClassLoader()), "classes/META-INF/load.sql");
    deployment.addResource(com.example.EmployeeResource.class);
    deployment.addAllDependencies();

    swarm.start().deploy(deployment);
  }
}
