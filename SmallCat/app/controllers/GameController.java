package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import creare.sc.models.Game;

public class GameController extends Controller {

	/**
	 * Display the home page.
	 */
	public static Result home() {
		Game game = new Game();

		game.exec();

		return TODO;
	}

	/**
	 * Display the home page.
	 */
	public static Result friendList() {

		return TODO;
	}
}
