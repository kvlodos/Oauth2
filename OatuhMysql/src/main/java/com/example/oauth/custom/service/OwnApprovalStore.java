package com.example.oauth.custom.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;

import com.example.oauth.custom.reposistory.ApprovalsReposistory;

public class OwnApprovalStore implements ApprovalStore{

	@Autowired
	ApprovalsReposistory approvalsReposistory; 
	
	@Override
	public boolean addApprovals(Collection<Approval> approvals) {
//		try {
//			List<com.example.oauth.custom.model.Approval> list = new ArrayList<com.example.oauth.custom.model.Approval>();
//			approvalsReposistory.saveAll(list);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		
		return false;
		
	}

	@Override
	public boolean revokeApprovals(Collection<Approval> approvals) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Approval> getApprovals(String userId, String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
