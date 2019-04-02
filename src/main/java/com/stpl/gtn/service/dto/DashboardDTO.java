package com.stpl.gtn.service.dto;

//import java.util.Optional;
//
import com.stpl.gtn.domain.User;
//import com.stpl.gtn.repository.UserRepository;
//import com.stpl.gtn.security.SecurityUtils;

public class DashboardDTO {
	private String dashboardItems[];
	
	private User userId;

	public DashboardDTO() {
		dashboardItems = new String[]{"todoList","weather","profile"};
	}

	public String[] getDashboardItems() {
		return dashboardItems;
	}

	public void setDashboardItems(String[] dashboardItems) {
		this.dashboardItems = dashboardItems;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
}
