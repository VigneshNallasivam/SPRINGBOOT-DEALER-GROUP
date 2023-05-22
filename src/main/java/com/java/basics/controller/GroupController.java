package com.java.basics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.basics.entity.Group;
import com.java.basics.response.Message;
import com.java.basics.response.Response;
import com.java.basics.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController 
{
	@Autowired
	private GroupService groupService;
	
	@PostMapping("/groupPost")
	public ResponseEntity<Object> create(@RequestBody Group group)
	{
		Group group1 = groupService.create(group);
        Message message = new Message("Data is Created..!!",group1);
        return Response.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,message);
	}
	
	@PutMapping("/groupPut")
	public ResponseEntity<Object> update(long groupId,@RequestBody Group group)
	{
		Group group1 = groupService.update(groupId,group);
		Message message = new Message("Data is Updated..!!",group1);
        return Response.generateResponse("Data Updation = Success",true, HttpStatus.OK,message);
		
	}
	@GetMapping("/groupGet")
	public ResponseEntity<Object> read()
	{
		List<Group> group = groupService.read();
        return new ResponseEntity<>(group,HttpStatus.FOUND);
		
	}
	@DeleteMapping("/groupDelete/{groupId}")
	public ResponseEntity<Object> delete(@PathVariable long dealerId)
	{
		groupService.delete(dealerId);
        Message message = new Message("Data is Deleted..!!","Success..!!");
        return Response.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,message);
		
	}

}
