package com.stpl.gtn.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "dashboard")
public class DashBoard {
	 private static final long serialVersionUID = 1L;
	 
	    private User userId;
	    
	    @Field("dashboard_items")
	    private String dashboardItems[];

		public User getUserId() {
			return userId;
		}

		public void setUserId(User userId) {
			this.userId = userId;
		}

		public String[] getDashboardItems() {
			return dashboardItems;
		}

		public void setDashboardItems(String[] dashboardItems) {
			this.dashboardItems = dashboardItems;
		}
}
