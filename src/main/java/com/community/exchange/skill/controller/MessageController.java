package com.community.exchange.skill.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.exchange.skill.DAO.Message;
import com.community.exchange.skill.DAO.Profile;
import com.community.exchange.skill.DAO.Skill;
import com.community.exchange.skill.service.RequestService;
import com.community.exchange.skill.service.UserService;
@Controller
@RequestMapping("/skillapp")
public class MessageController {
	
	
	@Autowired RequestService requestService;
	@Autowired UserService userService;

	@PostMapping("/sendRequest")

	public String sendingRequest(@ModelAttribute ("message") Message message, Model model,HttpSession session) {
		System.out.println("inside send message controller"+message.getReceiverUserName());
		message.setStatus("PEN");
		userService.sendMessage(message,(String)session.getAttribute("userName"));
		model.addAttribute("requestSuccess", "Request Sent to the provider");
		return "home";
	}

	@GetMapping("inbox")
	public String showInbox(Model model, HttpSession session) {
	    String receiverUserName = (String) session.getAttribute("userName");
	    System.out.println("receiverUserName"+receiverUserName);
	    List<Message> requests = userService.getMessagesForUser(receiverUserName,"receive");
	    System.out.println("request count"+requests.size());
	    model.addAttribute("talentRequests", requests);
	    return "Inbox";
	}
	  @GetMapping("talent-requests/{id}")
	public String viewTalentRequest(@PathVariable Long id, Model model,@ModelAttribute("response")Message response) {
	    Message talentRequest = requestService.getTalentRequestById(id);
	    model.addAttribute("talentRequest", talentRequest);
	    return "view";
	}

	  @GetMapping ("MyRequests")
		public String LoadMyRequests(HttpSession session,Model model) {
			 String senderUserName = (String) session.getAttribute("userName");
			    System.out.println("sender"+senderUserName);
			    List<Message> requests = userService.getMessagesForUser(senderUserName,"sent");
			    model.addAttribute("talentRequests", requests);
				return "Inbox";
		}
	  // Endpoint to handle providing a response to a talent request
	  
	  @PostMapping("talent-requests/{id}/respond") public String
	  respondToTalentRequest(@PathVariable Long id, @ModelAttribute("response") Message response,HttpSession session)
	  { 
		  System.out.println("nside controller respond");
		  Message talentRequest = requestService.getTalentRequestById(id);
		  Message reply= new Message();
		  reply.setContent(response.getContent());
		  reply.setReceiverUserName(talentRequest.getSenderUserName());
		  reply.setSenderUserName((String)session.getAttribute("userName"));
		  reply.setSkillName(talentRequest.getSkillName());
		  System.out.println("sender is "+reply.getSenderUserName() +"receiver is "+reply.getReceiverUserName());
		  userService.sendMessage( reply,(String)session.getAttribute("userName"));
	  requestService.markRequestAsResponded(id,response.getStatus()); 
	  return "redirect:/skillapp/inbox";
	  }
	 
	  // Endpoint to delete a talent request
	  
	  @PostMapping("talent-requests/{id}/delete")
	  public String deleteTalentRequest(@PathVariable
	  Long id,HttpSession session) {
		  String userName=(String)session.getAttribute("userName");
		String senderName=  requestService.getRequestDetails(id);
		  requestService.deleteTalentRequest(id);
		  if(senderName.equals(userName)) {
			  return "redirect:/skillapp/MyRequests";
		  }
	  return "redirect:/skillapp/inbox";
	  }
	  
	
	 
}
