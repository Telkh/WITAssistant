package application;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import java.util.HashMap;

public class Command {
	//static HashMap<String, Runnable> hmap;
	
	/*public Command() {
		Command.hmap = new HashMap<String, Runnable>() {
			{
				put("~calender", () -> calender());
			    put("~faculty", () -> faculty(AssistantController.message));
			    put("~search", () -> search(AssistantController.message));
			    put("~help", () -> help(AssistantController.message));//utilize message for specific command help
			    put("~about", () -> about());
			}
		};
	}*/
	
	public static String commandTerm(String term) {
		String newterm[];
		newterm = term.split(" ", 1);
		if (term.contains("~calender")) {
			return calender();
		}
		if (term.contains("~faculty")) {
			if (newterm.length == 1) {
				return faculty(newterm[0]);
			}
			return faculty(newterm[1]);
		}
		if (term.contains("~search")) {
			if (newterm.length == 1) {
				return search(newterm[0]);
			}
			return search(newterm[1]);
		}
		if (term.contains("~help")) {
			return help();
		}
		if (term.contains("~about")) {
			return about();
		}
		
		return null;
	}
	
	public static String calender() {
		return "https://wit.edu/calendar";
	}
	public static String faculty(String message) {
		if (message.equals("~faculty")) {
			return "Please enter a term after the command";
		}
		
		message = message.substring(9);
		if (message.contains(" ")) {
			try {
				Document doc = Jsoup.connect("https://wit.edu/wentworth-directory?name=" + message + "&field_colleges_target_id=All&field_department_target_id=All").get();
				Elements links = doc.select("a[href]");
				for (Element link : links) {
					if (link.toString().toLowerCase().contains(message.replace("+", "-"))) {
						return "https://wit.edu" + link.attr("href");
					}
				}
			}
			catch (IOException e) {
				return null;
			}
			message = message.toLowerCase().replace(" ", "+");
			return "https://wit.edu/wentworth-directory?name="+ message + "&field_colleges_target_id=All&field_department_target_id=All";
		}
		
		try {
			Document doc = Jsoup.connect("https://wit.edu/wentworth-directory?name=" + message + "&field_colleges_target_id=All&field_department_target_id=All").get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (link.toString().toLowerCase().contains(message)) {
					return "https://wit.edu" + link.attr("href");
				}
			}
		}
		catch (IOException e) {
			return null;
		}
		return "https://wit.edu/wentworth-directory?name="+ message.toLowerCase() + "&field_colleges_target_id=All&field_department_target_id=All";
	}
	public static String search(String message) {
		if (message.equals("~search")) {
			return "Please enter a term after the command";
		}
		
		message = message.substring(8);
		if (message.contains(" ")) {
			try {
				Document doc = Jsoup.connect("https://wit.edu/search/" + message).get();
				Elements links = doc.select("a[href]");
				for (Element link : links) {
					if (link.toString().toLowerCase().contains(message.replace(" ", "-").toLowerCase())) {
						return "https://wit.edu" + link.attr("href");
					}
				}
			}
			catch (IOException e) {
				return null;
			}
			message = message.toLowerCase().replace(" ", "%20");
			return "https://wit.edu/search/" + message;
		}
		try {
			Document doc = Jsoup.connect("https://wit.edu/search/" + message.toLowerCase()).get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (link.toString().toLowerCase().contains(message.toLowerCase())) {
					return "https://wit.edu" + link.attr("href");
				}
			}
		}
		catch (IOException e) {
			return null;
		}
	return "https://wit.edu/search/" + message;
	}
	public static String help() {
		return "Commands: help, about, search(term), faculty(name), calender";
	}
	public static String about() {
		return "This assistant is to help access various parts of the Wentworth Website with ease";
	}
}
