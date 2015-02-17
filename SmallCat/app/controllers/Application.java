package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.content;
import views.html.index;
public class Application extends Controller {

	/**
	 * Display the home page.
	 */
	public static Result index() {
		session().clear();
		return ok(index.render());
	}


	public static Result login(String user) {

		if(user == null || user.trim().equals("")) {
			flash("error", "Please choose a valid username.");

			return redirect(routes.Application.index());
		} else {
			session("user", user);

			return ok(content.render("default"));
		}

	}

}
