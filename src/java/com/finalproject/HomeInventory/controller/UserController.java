package com.finalproject.HomeInventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.HomeInventory.DAO.UserDAOImpl;
import com.finalproject.HomeInventory.Utility.Constants;
import com.finalproject.HomeInventory.config.AuthenticationManager;
//import com.finalproject.HomeInventory.model.Items;
import com.finalproject.HomeInventory.model.User;
import com.finalproject.HomeInventory.validator.UserValidator;

@Controller
public class UserController {

    @Autowired
    UserDAOImpl userService;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    UserValidator userValidator;
  
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }
    
    @RequestMapping(value ="/access-denied", method = RequestMethod.GET)
    public String denied(Model model){
        return "accessdenied";
    }
    @RequestMapping(value ="/errors", method = RequestMethod.GET)
    public String error(Model model){
        return "error";
    }
    @RequestMapping(value ="/logout", method = RequestMethod.POST)
    public String logout(Model model){
        AuthenticationManager.getInstance().setSc(null);
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm,
            BindingResult bindingResult, Model model) {
        String errorMessage = null;
        if(userValidator.existUser(userForm.getUsername())){
            errorMessage = "This user is existed!";
            model.addAttribute("error", errorMessage);
            return "registration";
        }
        if(!userValidator.confirmPasswordValidate(userForm.getPassword(), userForm.getPasswordConfirm())){
            errorMessage = "Confirm password is not match!";
            model.addAttribute("error", errorMessage);
            return "registration";
        }
        userService.save(userForm);
        String successMessage = "Register successfully!";
        model.addAttribute("success", successMessage);
        return "registration";
    }
    @RequestMapping(value="/user/updateuser", params = "update", method = RequestMethod.POST)
    public String updateUser(User userForm, Model model){
        userService.updateUser(userForm);
        model.addAttribute("user", userForm);
        return "/user/user";
    }
    @RequestMapping(value="/user/updateuser", params = "deactive", method = RequestMethod.POST)
    public String deactiveUser(User userForm, Model model){
        userService.deactiveUser(userForm);
        model.addAttribute("user", userForm);
        return "redirect:/login";
    }
    
    @RequestMapping(value="/user/changepassword", method = RequestMethod.GET)
    public String changepassword(Model model){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        if(Constants.ROLE_USER != user.getIsadmin()){
            return "redirect:/access-denied";
        }
        model.addAttribute("user", user);
        return "/user/changepassword";
    }
    @RequestMapping(value="/admin/changepassword", method = RequestMethod.GET)
    public String changepasswordAdmin(Model model){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User admin = userService.findByUsername(username);
        if(Constants.ROLE_ADMIN!= admin.getIsadmin()){
            return "redirect:/access-denied";
        }
        model.addAttribute("admin", admin);
        return "/admin/changepassword";
    }
    
    @RequestMapping(value="/user/changepassword", method = RequestMethod.POST)
    public String changepasswordPOST(Model model,
            @RequestParam(value = "oldpassword", required = false) String oldpassword,
            @RequestParam(value = "newpassword", required = false) String newpassword,
            @RequestParam(value = "renewpassword", required = false) String renewpassword){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        String existPassword = user.getPassword();
        boolean matches = bCryptPasswordEncoder.matches(oldpassword, existPassword);
        String errorMessage = null;
        if(matches){
           if(newpassword.trim().equalsIgnoreCase(renewpassword.trim())){
               user.setPassword(newpassword);
               userService.changePassword(user);
           }else {
               errorMessage = "Password is not matched!";
               model.addAttribute("error", errorMessage);
               return "/user/changepassword";
           }
        }else {
            errorMessage = "Incorrect old password!";
            model.addAttribute("error", errorMessage);
            return "/user/changepassword";
        }
        model.addAttribute("user", user);
        return "/user/changepassword";
    }
    
    @RequestMapping(value="/admin/changepassword", method = RequestMethod.POST)
    public String changepasswordAdminPOST(Model model,
            @RequestParam(value = "oldpassword", required = false) String oldpassword,
            @RequestParam(value = "newpassword", required = false) String newpassword,
            @RequestParam(value = "renewpassword", required = false) String renewpassword){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User admin = userService.findByUsername(username);
        String existPassword = admin.getPassword();
        boolean matches = bCryptPasswordEncoder.matches(oldpassword, existPassword);
        String errorMessage = null;
        if(matches){
           if(newpassword.trim().equalsIgnoreCase(renewpassword.trim())){
               admin.setPassword(newpassword);
               userService.changePassword(admin);
           }else {
               errorMessage = "Password is not matched!";
               model.addAttribute("error", errorMessage);
               return "/admin/changepassword";
           }
        }else {
            errorMessage = "Incorrect old password!";
            model.addAttribute("error", errorMessage);
            return "/admin/changepassword";
        }
        model.addAttribute("admin", admin);
        return "/admin/changepassword";
    }

    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String authen(Model model,String error, String logout, String username, String password,HttpServletRequest request){
        User user = userService.findByUsername(username);
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            model.addAttribute("error", "Your username and password is invalid.");
            return "login";
        }
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if(Constants.ACTIVE == user.getActive()){
            if(Constants.ROLE_ADMIN == user.getIsadmin()){
                Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                SecurityContext sc = SecurityContextHolder.getContext();
                sc.setAuthentication(auth);
                AuthenticationManager authManager = AuthenticationManager.getInstance();
                authManager.setSc(sc);
                return "redirect:/admin/admin";
            }else {
                Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                SecurityContext sc = SecurityContextHolder.getContext();
                sc.setAuthentication(auth);
                AuthenticationManager authManager = AuthenticationManager.getInstance();
                authManager.setSc(sc);
                return "redirect:/user/user";
            }
        }else {
            model.addAttribute("error", "Your account has been deactived!");
            return "login";
        }
        
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcome(Model model) {
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        List<GrantedAuthority> lsAutho =  (List<GrantedAuthority>) auth.getAuthorities();
        String role = null;
        for (GrantedAuthority grantedAuthority : lsAutho) {
            role = grantedAuthority.getAuthority();
        }
        if(Constants.ADMIN.equals(role)){
            return "redirect:admin/admin";
        }else {
            return "redirect:/user/user";
        }
    }
    @RequestMapping(value ="/admin/admin", method = RequestMethod.GET)
    public String adminInfo(Model model){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User admin = userService.findByUsername(username);
        if(Constants.ROLE_ADMIN != admin.getIsadmin()){
            return "redirect:/access-denied";
        }
        List<User> listUsers = userService.getAllUser();
        model.addAttribute("admin", admin);
        if(!listUsers.isEmpty()){
            model.addAttribute("listUsers", listUsers);
        }
        return "/admin/admin";
    }
    
    @RequestMapping(value ="/user/user", method = RequestMethod.GET)
    public String userinfo(Model model){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        if(Constants.ROLE_USER != user.getIsadmin()){
            return "redirect:/access-denied";
        }
        model.addAttribute("user", user);
        return "/user/user";
    }
    
    @RequestMapping(value ="/admin/updateuser",params = "active", method =RequestMethod.POST)
    public String activeUser(Model model,
            @RequestParam(value = "username", required = false) String username){
        userService.activeUser(username);
        return "redirect:/admin/admin";
    }
    
    @RequestMapping(value ="/admin/updateuser",params = "deactive", method =RequestMethod.POST)
    public String admindeactiveUser(Model model,
            @RequestParam(value = "username", required = false) String username){
        User user = userService.findByUsername(username);
        userService.deactiveUser(user);
        return "redirect:/admin/admin";
    }
    
    @RequestMapping(value ="/admin/updateuser",params = "promote", method =RequestMethod.POST)
    public String promoteUser(Model model,
            @RequestParam(value = "username", required = false) String username){
        userService.promoteUser(username);
        return "redirect:/admin/admin";
    }
    
    @RequestMapping(value ="/admin/updateuser",params = "demote", method =RequestMethod.POST)
    public String demoteUser(Model model,
            @RequestParam(value = "username", required = false) String username){
        userService.demoteUser(username);
        return "redirect:/admin/admin";
    }
    @RequestMapping(value ="/admin/updateuser",params = "delete", method =RequestMethod.POST)
    public String deleteUserAdmin(Model model,
            @RequestParam(value = "username", required = false) String username){
        User deleteUser = userService.findByUsername(username);
        userService.deleteUserByAdmin(deleteUser);
//        List<Items> listItems = itemService.getItemByUser(username);
//        for (Items items : listItems) {
//            itemService.deleteItem(items);
//        }
        return "redirect:/admin/admin";
    }
    @RequestMapping(value ="/admin/updateuserpopup", method =RequestMethod.POST)
    public String editUser(Model model,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "Email", required = false) String email,
            @RequestParam(value = "First name", required = false) String firstname,
            @RequestParam(value = "Last name", required = false) String lastname){
        User user = userService.findByUsername(username);
        if(email != null){
            user.setEmail(email);
        }
        if(firstname != null){
            user.setFirstname(firstname);
        }
        if(lastname != null){
            user.setLastname(lastname);
        }
        userService.updateUser(user);
        return "redirect:/admin/admin";
    }
    
    @RequestMapping(value ="/admin/adduser", method =RequestMethod.GET)
    public String adminaddUser(Model model){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User admin = userService.findByUsername(username);
        if(Constants.ROLE_ADMIN!= admin.getIsadmin()){
            return "redirect:/access-denied";
        }
        model.addAttribute("admin", admin);
        return "/admin/adduser";
    }
    
    @RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
    public String adminaddUserPost(@ModelAttribute("userForm") User userForm,
            BindingResult bindingResult, Model model,
            @RequestParam(value = "isadmin", required = false) boolean isadmin) {
        if(isadmin){
            userForm.setIsadmin(Constants.ROLE_ADMIN);
        }else {
            userForm.setIsadmin(Constants.ROLE_USER);
        }
        String errorMessage = null;
        if(userValidator.existUser(userForm.getUsername())){
            errorMessage = "This user is existed!";
            model.addAttribute("error", errorMessage);
            return "/admin/adduser";
        }
        if(!userValidator.confirmPasswordValidate(userForm.getPassword(), userForm.getPasswordConfirm())){
            errorMessage = "Confirm password is not match!";
            model.addAttribute("error", errorMessage);
            return "/admin/adduser";
        }
        
        userService.savebyadmin(userForm);
        return "redirect:/admin/admin";
    }
    
    
}
