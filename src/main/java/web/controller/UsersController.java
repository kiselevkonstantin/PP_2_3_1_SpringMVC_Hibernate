package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //   -----------------    список Users    -----------------
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/index";
    }

    //   -----------------    возвращает User по его id    --------------------
    @GetMapping("/details")
    public String getUserByID(@RequestParam("id") int id, Model model) { // (value = "id", required = false, defaultValue = "0")
        model.addAttribute("user", userService.getUserByID(id));
        return "users/show";
    }

    //   -----------------    добавить User    -----------------
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {  // вызываем форму для добавления нового User
        return "users/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User newUser) {
        userService.addUser(newUser);
        return "redirect:/users";
    }

    //   -----------------    удалить User    -----------------
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    //   -----------------    изменить User    -----------------
    @GetMapping("/update")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserByID(id));
        return "users/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

}
