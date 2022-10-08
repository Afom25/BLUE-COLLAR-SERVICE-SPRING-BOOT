package com.example.BLUE.COLLAR.SERVICE.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.BLUE.COLLAR.SERVICE.repository.BlueRepository;

import com.example.BLUE.COLLAR.SERVICE.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.BLUE.COLLAR.SERVICE.model.BlueCollarItem;

@RestController

public class BlueCollarController {
	
	private List<BlueCollarItem> bluelistItems = new ArrayList<BlueCollarItem>();
	private static int index = 1;


	@GetMapping("/bluelistItemForm")
	public ModelAndView showWatchlistItemForm() {

		String viewName = "bluelistItemForm";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("watchlistItem", new BlueCollarItem());
		return new ModelAndView(viewName,model);
	}
	@GetMapping("/signup")
	public ModelAndView getBlueSignUp() {
		String viewName = "signup";
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(viewName,model);
		
	}

	@RequestMapping("/service")
	public ModelAndView getBlueService() {
		
		String viewName = "service";
		
		Map<String,Object> model = new HashMap<String,Object>();
		
		return new ModelAndView(viewName,model);
		
	}
	@GetMapping("/about")
	public ModelAndView getBlueAbout() {
		
		String viewName = "about";
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(viewName,model);
		
	}
	@GetMapping("/contact")
	public ModelAndView getBlueContact() {
		
		String viewName = "contact";	
		Map<String,Object> model = new HashMap<String,Object>();	
		return new ModelAndView(viewName,model);
		
	}
	@GetMapping("/login")
	public ModelAndView getBlueLogin() {
		
		String viewName = "login";
		
		Map<String,Object> model = new HashMap<String,Object>();

		return new ModelAndView(viewName,model);
		
	}
	@GetMapping("/blueLive")
	public ModelAndView getBlueLive() {

		String viewName = "blueLive";
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(viewName,model);

	}
	@PostMapping("/bluelistItemForm")
	public ModelAndView submitWatchlistItemForm(BlueCollarItem bluelistItem) {

		bluelistItem.setId(index++);
		bluelistItems.add(bluelistItem);
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/bluecollarlist");

		return new ModelAndView(redirect);
	}
	@GetMapping("/bluecollarlist")
	public ModelAndView getBluebluelist() {
		
		String viewName = "bluecollarlist";
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("bluelistItems", bluelistItems);
		return new ModelAndView(viewName,model);
	}

	@GetMapping("/location")
	public ModelAndView getBluebluelocation() {

		String viewName = "location";
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(viewName,model);

	}

	@GetMapping("/locationId")
	public ModelAndView getBluebluelocationId() {
		String viewName = "locationId";
		Map<String,Object> model = new HashMap<String,Object>();
		return new ModelAndView(viewName,model);

	}
	@PostMapping("/subscribed")
	public ModelAndView scbscribe(BlueCollarItem bluelistItem) {


		bluelistItems.add(bluelistItem);
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/login");

		return new ModelAndView(redirect);
	}

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,
							   SimpMessageHeaderAccessor headerAccessor) {

		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

}
