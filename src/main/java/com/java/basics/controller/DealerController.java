package com.java.basics.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.java.basics.entity.Dealer;
import com.java.basics.request.GroupDealerRequest;
import com.java.basics.response.Message;
import com.java.basics.response.Response;
import com.java.basics.service.DealerService;

@RestController
@RequestMapping("/dealer")
public class DealerController 
{
	
	Logger logger = LoggerFactory.getLogger(DealerController.class);
//	logger.trace("Log level: TRACE");
//    logger.info("Log level: INFO");
//    logger.debug("Log level: DEBUG");
//    logger.error("Log level: ERROR");
//    logger.warn("Log level: WARN");
	
	@Autowired
	DealerService dealerService;
	
	@PostMapping("/dealerPost")
	public ResponseEntity<Object> create(@RequestBody Dealer dealer)
	{
		Dealer dealer1 = dealerService.create(dealer);
        Message message = new Message("Data is Created..!!",dealer1);
        return Response.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,message);
	}
	
	@PutMapping("/dealerPut")
	public ResponseEntity<Object> update(long dealerId,@RequestBody Dealer dealer)
	{
		try
		{
		Dealer dealer1 = dealerService.update(dealerId,dealer);
		Message message = new Message("Data is Updated..!!",dealer1);
		}catch(Exception ex)
		{
			
        return Response.generateResponse("Data Updation = Success",true, HttpStatus.OK,message);
		}
		
	}
	@GetMapping("/dealerGet")
	public ResponseEntity<Object> read(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "2") Integer pageSize,
                                       @RequestParam(defaultValue = "emp") String asc)
	{
		List<Dealer> dealer = dealerService.read();
        return new ResponseEntity<>(dealer,HttpStatus.FOUND);
		
	}
	@DeleteMapping("/dealerDelete/{dealerId}")
	public ResponseEntity<Object> delete(@PathVariable long dealerId)
	{
		dealerService.delete(dealerId);
        Message message = new Message("Data is Deleted..!!","Success..!!");
        return Response.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,message);
		
	}
	@PostMapping("/und/{groupId}")
	public ResponseEntity<Object> uAndD(@PathVariable Long groupId,@RequestBody GroupDealerRequest groupDealerRequest)
	{
		ResponseEntity<Object> deals = dealerService.und(groupDealerRequest);
		 return Response.generateResponse("Dealer Deleted From Group = Success",true, HttpStatus.GONE,deals);
	}

}
