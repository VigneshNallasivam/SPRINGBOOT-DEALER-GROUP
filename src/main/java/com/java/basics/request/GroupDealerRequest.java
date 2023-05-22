package com.java.basics.request;

import java.util.List;

import com.java.basics.entity.Dealer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDealerRequest 
{
	private long groupId;
	private String groupName;
	private List<Long> removeDealerId;
	private List<Dealer> newDealerId;
	private String label;
}
