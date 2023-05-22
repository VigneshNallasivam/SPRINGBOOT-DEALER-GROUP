package com.java.basics.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.java.basics.entity.Dealer;
import com.java.basics.entity.Group;
import com.java.basics.exception.DealerException;
import com.java.basics.repository.DealerRepository;
import com.java.basics.repository.GroupRepository;
import com.java.basics.request.GroupDealerRequest;
import com.java.basics.response.Response;
@Service
public class DealerService
{
	@Autowired
	DealerRepository dealerRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	public Dealer create(Dealer dealer)
    {
		
        return dealerRepository.save(dealer);
    }

    public Dealer update(long dealerId,Dealer dealer)
    {
       Dealer dealer1= dealerRepository.findById(dealer.getDealerId()).get();
        if(dealerRepository.findById(dealer.getDealerId()).isPresent())
        {
       
            dealer1.setDealerName(dealer.getDealerName());
        	dealer1.setName(dealer.getName());
        	dealer1.setCountry(dealer.getCountry());
        	dealer1.setState(dealer.getState());
        	dealer1.setEmail(dealer.getEmail());
        	dealer1.setPhone(dealer.getPhone());
        	dealer1.setType(dealer.getType());
        	dealer1.setWebsite(dealer.getWebsite());
        	dealer1.setActive(dealer.getActive());
        	dealer1.setCreatedBy(dealer.getCreatedBy());
        	dealer1.setCreatedDate(dealer.getCreatedDate());
        	dealer1.setUpdatedBy(dealer.getUpdatedBy());
        	dealer1.setCreatedDate(dealer.getCreatedDate());
            dealerRepository.save(dealer1);
            return dealer1;
        }
        else
        {
            throw new DealerException("ID_NOT_FOUND");
        }
    }

    public List<Dealer> read()
    {
    	List<Dealer> dealerList = dealerRepository.findAll();
        if(dealerRepository.findAll().isEmpty())
        {
            throw new DealerException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return dealerList;
        }
    }

    public void delete(long dealerId)
    {
        Dealer dealer = dealerRepository.findById(dealerId).get();
        if(dealerRepository.findById(dealer.getDealerId()).isPresent())
        {
        	dealerRepository.deleteById(dealer.getDealerId());
        }
        else
        {
            throw new DealerException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }
//    for (DealerManagementModel currentDealer : dealerrequest.getDealer()) {
//		Optional<DealerManagementModel> dealergroupexits = dealermanagementrepository
//				.findById(currentDealer.getId());
//		if (dealergroupexits.isPresent()) {
//			DealerManagementModel dealergroup = dealergroupexits.get();
//			List<GroupingModel> delaergroupsdata = dealergroup.getDealergroup();
//			if (!delaergroupsdata.contains(groupdata)) {
//				delaergroupsdata.add(groupdata);
//				dealergroup.setDealergroup(delaergroupsdata);
//				dealermanagementrepository.save(dealergroup);
//			}
//
//		} else
//			return ResponseHandler.generateResponse(
//					"Dealer ID " + currentDealer.getId() + " Doesn't exist ", false, HttpStatus.OK,
//					null);
//
//	}


	public ResponseEntity<Object> und(GroupDealerRequest groupDealerRequest) 
	{
		Optional<Group> groupdataexist = groupRepository.findById(groupDealerRequest.getGroupId());
		Group groupdata = groupdataexist.get();
		if(groupRepository.findById(groupDealerRequest.getGroupId())!=null)
		{

		for(Long dealerId : groupDealerRequest.getRemoveDealerId()) {
			
			Optional<Dealer> dealers = dealerRepository.findById(dealerId);
			if(dealers.isPresent())
			{
				List<Group> alldealer = dealers.get().getGroups();
				if(alldealer.contains(groupdata))
				{
					alldealer.remove(groupdata);
					dealers.get().setGroups(alldealer);
					dealerRepository.save(dealers.get());
				}
			}
			
		}
		for(Dealer currentDealer :groupDealerRequest.getNewDealerId())
		{
			Optional<Dealer> dealergroupexits = dealerRepository.findById(currentDealer.getDealerId());
			if (dealergroupexits.isPresent())
			{
				Dealer dealergroup = dealergroupexits.get();
				List<Group> delaergroupsdata = dealergroup.getGroups();
				if (!delaergroupsdata.contains(groupdata)) {
					delaergroupsdata.add(groupdata);
					dealergroup.setGroups(delaergroupsdata);
					dealerRepository.save(dealergroup);
				}
			}
			
			
		    }
		}
		else
		{
			throw new DealerException("ID NOT-FOUNd..!!");
		}
		 return  Response.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,null);
		
	}
}
	


