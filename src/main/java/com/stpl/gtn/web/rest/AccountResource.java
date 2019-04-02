package com.stpl.gtn.web.rest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.domain.User;
import com.stpl.gtn.domain.DashBoard;
import com.stpl.gtn.repository.DashboardRepository;
import com.stpl.gtn.repository.UserRepository;
import com.stpl.gtn.security.SecurityUtils;
import com.stpl.gtn.service.UserService;
import com.stpl.gtn.service.dto.DashboardDTO;
import com.stpl.gtn.service.dto.UserDTO;
import com.stpl.gtn.web.rest.errors.InternalServerErrorException;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DashboardRepository dashboardRepository;

    private final UserService userService;
 

    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET  /account : get the current user.
     *
     * @param principal the current user; resolves to null if not authenticated
     * @return the current user
     * @throws InternalServerErrorException 500 (Internal Server Error) if the user couldn't be returned
     */
    @GetMapping("/dashboard")
    @SuppressWarnings("unchecked")
    public UserDTO getAccount(Principal principal) {
    	System.out.println("Pricipal is"+ principal.getName());
        if (principal != null) {
            if (principal instanceof OAuth2Authentication) {
                return userService.getUserFromAuthentication((OAuth2Authentication) principal);
            } else {
                // Allow Spring Security Test to be used to mock users in the database
                return userService.getUserWithAuthorities()
                    .map(UserDTO::new)
                    .orElseThrow(() -> new InternalServerErrorException("User could not be found"));
            }
        } else {
            throw new InternalServerErrorException("User could not be found");
        }
    }
    @GetMapping("/account")
    public List<DashBoard> getDashboardContent() {
    	log.info(SecurityUtils.getCurrentUserLogin().toString());
    	Optional<User> userDetails = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
    	if(userDetails.isPresent()) {
    		System.out.println("I m in content");
    		DashboardDTO dashboard = new DashboardDTO();
        	dashboard.setUserId(userDetails.get());
        	System.out.println(userDetails.get());
        	DashBoard dashboard_item = new DashBoard();
        	dashboard_item.setUserId(dashboard.getUserId());
        	dashboard_item.setDashboardItems(dashboard.getDashboardItems());
        	System.out.println(dashboard.toString());
        	System.out.println(dashboard_item.toString());
        	dashboardRepository.save(dashboard_item);
        	return dashboardRepository.findAll();
    	}
    	else {
            throw new InternalServerErrorException("User could not be found");
        }
    }
}
