package com.java.basics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.basics.entity.Group;
import com.java.basics.exception.DealerException;
import com.java.basics.repository.GroupRepository;

@Service
public class GroupService 
{
	@Autowired
	GroupRepository groupRepository;
	
	public Group create(Group group)
    {
        return groupRepository.save(group);
    }

    public Group update(long dealerId,Group group)
    {
    	Group group1= groupRepository.findById(group.getGroupId()).get();
        if(groupRepository.findById(group.getGroupId()).isPresent())
        {
        	group1.setGroupName(group.getGroupName());
        	group1.setActive(group.getActive());
        	group1.setCreatedBy(group.getCreatedBy());
        	group1.setCreatedDate(group.getCreatedDate());
        	group1.setUpdatedBy(group.getUpdatedBy());
        	group1.setUpdatedDate(group.getUpdatedDate());
        	group1.setLabel(group.getLabel());
            return group1;
        }
        else
        {
            throw new DealerException("ID_NOT_FOUND");
        }
    }

    public List<Group> read()
    {
    	List<Group> groupList = groupRepository.findAll();
        if(groupRepository.findAll().isEmpty())
        {
            throw new DealerException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return groupList;
        }
    }

    public void delete(long groupId)
    {
    	Group group = groupRepository.findById(groupId).get();
        if(groupRepository.findById(group.getGroupId()).isPresent())
        {
        	groupRepository.deleteById(groupId);
        }
        else
        {
            throw new DealerException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }
	


}
