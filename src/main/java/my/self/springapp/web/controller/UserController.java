package my.self.springapp.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import my.self.springapp.domain.user.UserService;
import my.self.springapp.web.form.user.UserForm;
import my.self.springapp.web.form.user.UserFormValidator;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserFormValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("/user/registration")
    public String userRegistration(Model model) {
        model.addAttribute(new UserForm());
        return "user/registration";
    }

    @PostMapping("/user/registration")
    public String userRegistrationSubmit(@ModelAttribute @Valid UserForm userForm, BindingResult result) {

        if (result.hasErrors()) {
            return "/user/registration";
        } else {
            System.out.println("Save");
//            userService.update(userForm);
        }

        return "redirect:/";
    }

}
