package com.example.webguidemo;

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
        pages.screenshot("Wykopalisko-page");
        assertEquals("Wykopalisko - Wykop.pl", pages.wykopalisko().getTitle());
    }

    @When("user clicks OpenSearch button")
    public void userClicksOpenSearchButton() {
        pages.home().clickOpenSearchButton();
    }

    @Then("Search input is shown")
    public void searchInputIsShown() {
        pages.screenshot("search-input-visible");
        assertThat(pages.home().findElement(By.xpath("//input[@name='nsQ']")).getAttribute("style"), either(equalTo("")).or(equalTo("display: none;")));
    }

    @When("user types Pooffy in Search input and clicks button")
    public void searchSomething() {
        pages.home().searchSomething();
    }

    @Then("Search result is shown")
    public void searchResultIsShown() {
        pages.screenshot("search-result");
        // istnienie bloku o klasie "usercard" jest dowodem na otrzymanie przynajmniej jednego wyniku wyszukiwarki
        assertNotNull(pages.search().findElement(By.className("usercard")));
    }

    @Given("user is on Login page")
    public void userIsOnLoginPage(){
        pages.login().open();
    }

    @When("user types invalid data - $login and $password")
    public void userTypesInvalidData(String login, String password) {
        pages.login().loginInvalid(login, password);
    }

    @Then("page title is $title and login error message $message is shown")
    public void loginErrorMessageIsShown(String title, String message) {
        pages.screenshot("login-error");
        assertEquals(message, pages.login().findElement(By.className("wblock")).getText());
        assertEquals(title, pages.wykopalisko().getTitle());
    }

    @Given("user is on Register page")
    public void userIsOnRegisterPage() {
        pages.register().open();
    }

    @When("user types used login - $login")
    public void userTypesUsedLogin(String login) {
        pages.register().invalidRegister(login);
    }

    @Then("page title is $title and register error message $message is shown")
    public void registerErrorMessageIsShown(String title, String message) {
        pages.screenshot("register-error");
        assertEquals("error", pages.register().findElement(By.id("loginField")).getAttribute("class"));
        assertEquals(message, pages.register().findElement(By.className("wblock")).getText());
        assertEquals(title, pages.wykopalisko().getTitle());
    }

    @When("user types valid data - $login and $password")
    public void userTypesValidLogin(String login, String password) {
        pages.login().loginValid(login, password);
    }

    @Then("user should be logged in")
    public void userShouldBeLoggedIn() {
        pages.screenshot("user-logged");
        assertTrue(pages.login().isLogged());

    }

//    @Given("user is on Settings page - if not logged in, then do using $login and $password")
//    public void userIsOnSettingsPage(String login, String password) {
//        pages.login().userGoToSettingsPage(login, password);
//    }
//
//    @When("user changes color version")
//    public void userChangesColorVersion() {
//        pages.login().userChangeColorVersion();
//    }
//
//    @Then("selected color should be changed")
//    public void selectedColorShouldBeChanged() {
//
//    }
}
