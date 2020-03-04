package ca.mcgill.ecse428.graphbook.features;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {
	@Given("a unique <username>, <password>")
	public void a_unique_username_password() {
	  assertTrue(true);
	}

	@Given("<firstName>, <lastName>")
	public void firstname_lastName() {
		 assertTrue(true);
	}

	@When("A person requests to become a user")
	public void a_person_requests_to_become_a_user() {
		 assertTrue(true);
	}

	@Then("the persons credentials are stored and he becomes a user")
	public void the_persons_credentials_are_stored_and_he_becomes_a_user() {
		 assertTrue(true);
	}

	@When("a person submits a requests to become a user")
	public void a_person_submits_a_requests_to_become_a_user() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("an existing <username>, <password>")
	public void an_existing_username_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	

	@Then("an error message is shown and person's credentials are not saved")
	public void an_error_message_is_shown_and_person_s_credentials_are_not_saved() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a user is logged into the GraphBook system")
	public void a_user_is_logged_into_the_GraphBook_system() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("they has at least one connection")
	public void they_has_at_least_one_connection() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("going in their profile page")
	public void going_in_their_profile_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("their personal connection graph is displayed")
	public void their_personal_connection_graph_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("they has no connections")
	public void they_has_no_connections() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("an empty graph is displayed")
	public void an_empty_graph_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a user is logged out of the GraphBook system")
	public void a_user_is_logged_out_of_the_GraphBook_system() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("an error message notifies the user that they need to log in")
	public void an_error_message_notifies_the_user_that_they_need_to_log_in() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("the following users are registered in the GraphBook system:")
	public void the_following_users_are_registered_in_the_GraphBook_system(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new cucumber.api.PendingException();
	}

	@When("user {string} searches for {string}")
	public void user_searches_for(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the following list of users is generated:")
	public void the_following_list_of_users_is_generated(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new cucumber.api.PendingException();
	}

	@Given("I have an existing Graphbook account")
	public void i_have_an_existing_Graphbook_account() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I sign in using existing account's email address")
	public void i_sign_in_using_existing_account_s_email_address() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("corresponding account password")
	public void corresponding_account_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should be logged in the Graphbook system.")
	public void i_should_be_logged_in_the_Graphbook_system() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I sign in using existing account's username")
	public void i_sign_in_using_existing_account_s_username() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I sign in with invalid login credentials")
	public void i_sign_in_with_invalid_login_credentials() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should be prompted with an error message")
	public void i_should_be_prompted_with_an_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should be asked to re-enter my login information.")
	public void i_should_be_asked_to_re_enter_my_login_information() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("that I am a registered user of GraphBook")
	public void that_I_am_a_registered_user_of_GraphBook() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("I am logged into GraphBook")
	public void i_am_logged_into_GraphBook() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("the user is logged into GraphBook")
	public void the_user_is_logged_into_GraphBook() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user requests to logout")
	public void the_user_requests_to_logout() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the system logs the user out")
	public void the_system_logs_the_user_out() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the session of the user is terminated")
	public void the_session_of_the_user_is_terminated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the login page is displayed")
	public void the_login_page_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("A logged in user selects another user X")
	public void a_logged_in_user_selects_another_user_X() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("They define their relationship with that user \\(strong\\/medium\\/none)")
	public void they_define_their_relationship_with_that_user_strong_medium_none() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The GraphBook system records this directional relationship")
	public void the_GraphBook_system_records_this_directional_relationship() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("A relationship definition already exists")
	public void a_relationship_definition_already_exists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("They redefine their relationship with that user \\(strong\\/medium\\/none)")
	public void they_redefine_their_relationship_with_that_user_strong_medium_none() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The GraphBook system overwrites the previous relationship with the new one")
	public void the_GraphBook_system_overwrites_the_previous_relationship_with_the_new_one() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("There is no access to the database\\(no connection)")
	public void there_is_no_access_to_the_database_no_connection() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("They define or redefine their relationship with another user \\(strong\\/medium\\/none)")
	public void they_define_or_redefine_their_relationship_with_another_user_strong_medium_none() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The GraphBook system detects that the database cannot be reached and gives an error message")
	public void the_GraphBook_system_detects_that_the_database_cannot_be_reached_and_gives_an_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a user of GraphBook")
	public void a_user_of_GraphBook() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("this user is logged in")
	public void this_user_is_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user edits the bio input field")
	public void the_user_edits_the_bio_input_field() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("presses the save\\/submit button")
	public void presses_the_save_submit_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I validate the content and length of the bio")
	public void i_validate_the_content_and_length_of_the_bio() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the content of the bio is assigned and stored to this particular user")
	public void the_content_of_the_bio_is_assigned_and_stored_to_this_particular_user() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
