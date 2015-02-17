package creare.sc.models;

import static akka.pattern.Patterns.*;
import static java.util.concurrent.TimeUnit.*;

import java.util.HashMap;
import java.util.Map;

import play.Logger;
import play.libs.Akka;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.libs.Json;
import play.mvc.WebSocket;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import creare.sc.models.command.Command;
import creare.sc.models.command.Join;
import creare.sc.models.command.Quit;
import creare.sc.models.command.Talk;
import creare.sc.models.command.Translate;


public class Room extends UntypedActor {

		// Default room.
		static ActorRef defaultRoom = Akka.system().actorOf(Props.create(Room.class));
		// Members of this room.
		Map<String, WebSocket.Out<JsonNode>> members = new HashMap<String, WebSocket.Out<JsonNode>>();

		/**
		 * Join the default room.
		 */
		public static void join(final String id, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception{

			// Send the Join message to the room
			String result = (String)Await.result(ask(defaultRoom,new Join(id, out), 1000), Duration.create(1, SECONDS));

			if("OK".equals(result)) {

				// For each event received on the socket,
				in.onMessage(new Callback<JsonNode>() {
					public void invoke(JsonNode event) {
						Logger.info("requested " + event);

						ObjectMapper mapper = new ObjectMapper();
						try {
							// Conversion from the JsonNode to the Object
							Command command = mapper.treeToValue(event, Command.class);
							// Send a Talk message to the room.
							defaultRoom.tell(command, null);
						} catch (JsonProcessingException e) {
							// TODO エラー通知
							Logger.warn("無効なコマンドを受信しました。" + event);

						}
					}
				});

				// When the socket is closed.
				in.onClose(new Callback0() {

					public void invoke() {
						// Send a Quit message to the room.
						defaultRoom.tell(new Quit(id), null);
					}
				});

			} else {

				// Cannot connect, create a Json error.
				ObjectNode error = Json.newObject();
				error.put("error", result);

				// Send the error to the socket.
				out.write(error);

			}
		}

		public void onReceive(Object message) throws Exception {

			if(message instanceof Join) {

				// Received a Join message
				Join join = (Join)message;

				// Check if this username is free.
				if(members.containsKey(join.id)) {
					getSender().tell("This username is already used", getSelf());
				} else {
					members.put(join.id, join.channel);
					notifyAll("join", join.id, "has entered the room");
					getSender().tell("OK", getSelf());
				}

			} else if(message instanceof Talk) {

				// Received a Talk message
				Talk talk = (Talk)message;
				talk.exec();
				notifyAll("talk", talk.id, talk.text);

			} else if(message instanceof Translate) {

				// Received a Talk message
				Translate translate = (Translate)message;
				notifyAll("translate", translate.id, translate.text);

			} else if(message instanceof Quit) {

				// Received a Quit message
				Quit quit = (Quit)message;

				members.remove(quit.id);
				notifyAll("quit", quit.id, "has left the room");

			} else {
				unhandled(message);
			}

		}

		// Send a Json event to all members
		public void notifyAll(String kind, String id, String text) {
			for(WebSocket.Out<JsonNode> channel: members.values()) {

				ObjectNode event = Json.newObject();
				event.put("kind", kind);
				event.put("id", id);
				event.put("message", text);

				ArrayNode m = event.putArray("members");
				for(String u: members.keySet()) {
					m.add(u);
				}

				channel.write(event);
			}
		}
}
