package com.mush.EatersOnline.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService adminDetailsService() {
		return new AdminServiceImpl();
	}

	@Bean
	public UserDetailsService customerDetailsService() {
		return new CustomerUserServiceImpl();
	}
	
	@Bean
	public UserDetailsService shopDetailsService() {
		return new ShopUserServiceImpl();
	}
	
	
	//password 
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider adminauthenticationProvider() {
		DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(adminDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public DaoAuthenticationProvider customerauthenticationProvider() {
		DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customerDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public DaoAuthenticationProvider shopauthenticationProvider() {
		DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(shopDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder authAdmin,AuthenticationManagerBuilder authCustomer,AuthenticationManagerBuilder authShop) throws Exception {
		

		authAdmin
        	.authenticationProvider(adminauthenticationProvider())
        	.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)       
            .usersByUsernameQuery("select username, password, status from admin where username=?")
            .authoritiesByUsernameQuery("select username, role from admin where username=?")
            ;
		
		authCustomer
    	.authenticationProvider(customerauthenticationProvider())
    	.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .dataSource(dataSource)       
        .usersByUsernameQuery("select email, password, status from customer where email=?")
        .authoritiesByUsernameQuery("select email, role from customer where email=?")
        ;
        
		authShop
    	.authenticationProvider(shopauthenticationProvider())
    	.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .dataSource(dataSource)
        .usersByUsernameQuery("select shopusername, password, status from shop where shopusername=?")
        .authoritiesByUsernameQuery("select shopusername, role from shop where shopusername=?")
        ;
        
    }
	
	
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
       .csrf().disable()

        .authorizeRequests()

        .antMatchers("/","/resources/**","/Customer/signupCustomer","/Customer/save","/Shop/createShop","/Shop/save","/Food/view/**").permitAll()
        
        .antMatchers("/Customer/","/Shop/","/Customer/edit/*","/Customer/saveDeposit/*","/Shop/delete/*").hasAuthority("ADMIN")
        
        .antMatchers("/account/**","/Bill/accept/","Bill/buy/","Bill/save/","Bill/deleteSession").hasAuthority("CUSTOMER")
        
        .antMatchers("/Shop/myFood/","/Food/newMenu","/Bill/myOrders","/Food/save","Food/edit/","Food/delete/").hasAuthority("SHOP")
        
        .mvcMatchers("/Bill/**","/Food/**","/Shop/**","/Customer/**").authenticated()
        .and()
        
            .formLogin().permitAll()
            .and()
           .logout().permitAll()
           .and()
           .rememberMe()
           .and()
          .exceptionHandling().accessDeniedPage("/403");
       
    }
    //.usernameParameter("username").passwordParameter("password")
    //.loginPage("login")
    //.permitAll()
	
	
}
