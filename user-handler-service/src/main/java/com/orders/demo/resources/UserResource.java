package com.orders.demo.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orders.demo.datamanagers.UserDataManager;
import com.orders.demo.dto.*;
import com.orders.demo.filters.JwtRequestFilter;
import com.orders.demo.models.CatalogItem;
import com.orders.demo.models.Movie;
import com.orders.demo.models.SysUser;
import com.orders.demo.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserResource {
    @Autowired
    private SysUserDetailService myUserDetailsService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDataManager userDataManager;

//    @RequestMapping("/{userId}")
//    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
//
//        UserRating userRating = restTemplate.getForObject("http://client-handler-module/ratingsdata/user/" + userId, UserRating.class);
//
//
//        return userRating.getRatings().stream()
//                .map(rating -> {
//                    List<OrderDTO> orders = restTemplate.getForObject("http://item-manager-module/movies/" + rating.getMovieId(), Movie.class);
//                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
//                })
//                .collect(Collectors.toList());
//
//    }

    @CrossOrigin
    @RequestMapping(value ="/register-user", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> registerUser(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody UserDTO userDTO) throws IOException {
        int result=  userDataManager.addUserData(userDTO);
        Gson gsonBuilder = new GsonBuilder().create();
        String responseJson;
        if(result == 0){
            ErrorResponseDTO errorDTO = new ErrorResponseDTO();
            responseJson = gsonBuilder.toJson(errorDTO);
        } else {
            CommonResponseDTO wrapperDTO = new CommonResponseDTO();
            wrapperDTO.setSuccess(true);
            responseJson = gsonBuilder.toJson(wrapperDTO);
        }
        return ResponseEntity.ok(responseJson.toString());
    }


    @CrossOrigin
    @RequestMapping(value ="/add-order", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> addOrder(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody OrderDTO orderDTO) throws IOException {
        String result=  userDataManager.addOrder(orderDTO);
        Gson gsonBuilder = new GsonBuilder().create();
        String responseJson;
        if(!result.equals("Success")){
            ErrorResponseDTO errorDTO = new ErrorResponseDTO();
            responseJson = gsonBuilder.toJson(errorDTO);
        } else {
            CommonResponseDTO wrapperDTO = new CommonResponseDTO();
            wrapperDTO.setSuccess(true);
            responseJson = gsonBuilder.toJson(wrapperDTO);
        }
        return ResponseEntity.ok(responseJson.toString());
    }

    @CrossOrigin
    @RequestMapping(value ="/add-item", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> addItem(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody ItemDTO itemDTO) throws IOException {
        String result=  userDataManager.addItem(itemDTO);
        Gson gsonBuilder = new GsonBuilder().create();
        String responseJson;
        if(!result.equals("Success")){
            ErrorResponseDTO errorDTO = new ErrorResponseDTO();
            responseJson = gsonBuilder.toJson(errorDTO);
        } else {
            CommonResponseDTO wrapperDTO = new CommonResponseDTO();
            wrapperDTO.setSuccess(true);
            responseJson = gsonBuilder.toJson(wrapperDTO);
        }
        return ResponseEntity.ok(responseJson.toString());
    }


    @CrossOrigin
    @RequestMapping(value ="/get-item", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getContentData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody ItemDTO itemDTO) throws IOException {
        ItemDTO contentData=  userDataManager.getItemById(itemDTO);
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping(value ="/get-order", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getOrderData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody OrderDTO orderDTO) throws IOException {
        OrderDTO contentData=  userDataManager.getOrderById(orderDTO);
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping(value ="/get-user", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getUserData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody UserDTO userDTO) throws IOException {
        SysUser contentData=  userDataManager.getUserData(userDTO.getUserEmail());
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }


    @CrossOrigin
    @RequestMapping(value ="/all-items", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getAllItemData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody ItemDTO itemDTO) throws IOException {
        List<ItemDTO> contentData=  userDataManager.getAllItems();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping(value ="/all-orders", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getAllOrderData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody OrderDTO orderDTO) throws IOException {
        List<OrderDTO> contentData=  userDataManager.getAllOrders();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }

    @CrossOrigin
    @RequestMapping(value ="/all-users", headers="Content-Type=application/json", method = {RequestMethod.POST} )
    public ResponseEntity<?> getAllUserData(@ModelAttribute("model") ModelMap model, HttpServletRequest request, HttpServletResponse response , @RequestBody UserDTO userDTO) throws IOException {
        List<SysUser> contentData=  userDataManager.getAllUsers();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonObj = gsonBuilder.toJson(contentData);
        return ResponseEntity.ok(jsonObj.toString());
    }




}

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserDetailService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/get-item","/save-settings","/register-student", "/get-personality-data", "/module-file","save-file").hasRole("ADMIN").
                antMatchers("/module-data").hasAnyRole("ADMIN","CLIENT")
                .antMatchers("/authenticate","/get-user","/add-user","/model-call").hasRole("ADMIN")
                .antMatchers("/authenticate","/get-user","/get-student","/model-call").permitAll().
                anyRequest().authenticated().and().
                // logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().
                        exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors();
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register-user","/add-order","/add-item");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
