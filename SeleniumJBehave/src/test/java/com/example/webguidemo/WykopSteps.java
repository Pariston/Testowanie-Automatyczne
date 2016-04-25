package com.example.webguidemo;

import junit.framework.TestCase;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Daniel on 19.04.2016.
 */
public class WykopSteps {

    private final Pages pages;

    public WykopSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on Home page")
    public void userIsOnHomePage(){
        pages.home().open();
    }

    @When("user opens Wykopalisko link")
    public void userClicksOnWykopaliskoLink() {
        pages.home().clickWykopaliskoLink();
    }

    @Then("Wykopalisko page is shown")
    public void wykopaliskoPageIsShown(){
        assertEquals("Wykopalisko - Wykop.pl", pages.wykopalisko().getTitle());
    }

    @When("user clicks OpenSearch button")
    public void userClicksOpenSearchButton() {
        pages.home().clickOpenSearchButton();
    }

    @Then("Search input is shown")
    public void searchInputIsShown() {
        assertThat(pages.home().findElement(By.xpath("//input[@name='nsQ']")).getAttribute("style"), either(equalTo("")).or(equalTo("display: none;")));
    }

    @When("user types Pooffy in Search input and clicks button")
    public void searchSomething() {
        pages.home().searchSomething();
    }

    @Then("Search result is shown")
    public void searchResultIsShown() {
        // istnienie bloku o klasie "usercard" jest dowodem na otrzymanie przynajmniej jednego wyniku wyszukiwarki
        assertNotNull(pages.search().findElement(By.className("usercard")));
    }

    @Given("user is on Login page")
    public void userIsOnLoginPage(){
        pages.login().open();
    }

    @When("user types invalid data")
    public void userTypesInvalidData() {
        pages.login().loginInvalid();
    }

    @Then("login error message is shown")
    public void loginErrorMessageIsShown() {
        TestCase.assertEquals("Niepoprawny login lub hasło", pages.login().findElement(By.className("wblock")).getText());
    }

    @Given("user is on Register page")
    public void userIsOnRegisterPage() {
        pages.register().open();
    }

    @When("user types used login")
    public void userTypesUsedLogin() {
        pages.register().invalidRegister();
    }

    @Then("register error message is shown")
    public void registerErrorMessageIsShown() {
        assertEquals("error", pages.register().findElement(By.id("loginField")).getAttribute("class"));
        assertEquals("Wybrany login jest zajęty", pages.register().findElement(By.className("wblock")).getText());
    }

    @When("user types valid data")
    public void userTypesValidLogin() {
        pages.login().loginValid();
    }

    @Then("user should be logged in")
    public void userShouldBeLoggedIn() {
        assertTrue(pages.login().isLogged());
    }
}
