package controllers;

import play.data.Form;
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

		Form<LoginForm> loginForm = Form.form(LoginForm.class);

		return ok(index.render(loginForm));
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

	public static class LoginForm {
		// メールアドレス
		public String id;
		// パスワード
		public String password;
		// 登録時のもの
		public String name;
	}

	public static Result certify() {

		Form<LoginForm> form = Form.form(LoginForm.class).bindFromRequest();

		if(form.hasErrors()) {

			return redirect(routes.Application.index());
		}else {
			LoginForm loginForm = form.get();

			String user = loginForm.id;
			session("user", user);
		}

		return ok(content.render("default"));
	}
}