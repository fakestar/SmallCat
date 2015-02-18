package controllers;

import play.data.Form;
import play.data.validation.Constraints;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.content;
import views.html.index;
import views.html.login;


public class Application extends Controller {

	/**
	 * Display the home page.
	 */
	public static Result index() {
		session().clear();

		return ok(index.render());
	}

	@AddCSRFToken
	public static Result login() {

		Form<LoginForm> loginForm = Form.form(LoginForm.class);

		return ok(login.render(loginForm));
	}

	public static Result logout() {
		session().clear();

		return ok(index.render());
	}

	public static class LoginForm {
		@Constraints.Required
		public String id;

		@Constraints.Required
		public String password;
		// 登録時のもの
		public String name;

		public void validate() {
			if(id == null || password == null) {
				//error
			}
		}
	}

	@RequireCSRFCheck
	public static Result certify() {

		Form<LoginForm> form = Form.form(LoginForm.class).bindFromRequest();

		if(form.hasErrors()) {

			return redirect(routes.Application.login());
		}else {
			LoginForm loginForm = form.get();

			String user = loginForm.id;
			session("user", user);
		}

		return ok(content.render("default"));
	}
}