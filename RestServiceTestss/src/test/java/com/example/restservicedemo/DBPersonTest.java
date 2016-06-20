package com.example.restservicedemo;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by elzoy on 6/6/2016.
 */
public class DBPersonTest {
    private static IDatabaseConnection connection ;
    private static IDatabaseTester databaseTester;

    private static PersonManager pm = new PersonManager();

    @BeforeClass
    public static void setUp() throws Exception{
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";

        Connection jdbcConnection;
        jdbcConnection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        connection = new DatabaseConnection(jdbcConnection);

        databaseTester = new JdbcDatabaseTester(
                "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream(new File("src/test/resources/fullData.xml")));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void addPerson() throws Exception {
        pm.clearPersons();

        Person personToAdd = new Person("Papłyk", 1992);
        given().contentType(MediaType.APPLICATION_JSON).body(personToAdd)
                .when().post("/person/add").then().assertThat().statusCode(201);

        IDataSet dbDataSet = connection.createDataSet();
        ITable actualTable = dbDataSet.getTable("PERSON");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"P_ID"});

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/add_personData.xml"));
        ITable expectedTable = expectedDataSet.getTable("PERSON");

        Assertion.assertEquals(expectedTable, filteredTable);
    }

    @Test
    public void removePerson() throws Exception {
        pm.clearPersons();

        Person personToAdd = new Person("Jatobymzjadlbatona", 1994);
        given().contentType(MediaType.APPLICATION_JSON).body(personToAdd)
                .when().post("/person/add").then().assertThat().statusCode(201);

        IDataSet dbDataSet = connection.createDataSet();
        ITable actualTable = dbDataSet.getTable("PERSON");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"P_ID"});

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/remove_personData.xml"));
        ITable expectedTable = expectedDataSet.getTable("PERSON");

        Assertion.assertEquals(expectedTable, filteredTable);
    }

    @AfterClass
    public static void tearDown() throws Exception{
        databaseTester.onTearDown();
    }
}
