package hu.elte.EducationSystem.controllers;

import hu.elte.EducationSystem.entities.Course;
import hu.elte.EducationSystem.entities.User;
import hu.elte.EducationSystem.repositories.CourseRepository;
import hu.elte.EducationSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import hu.elte.EducationSystem.security.AuthenticatedUser;
import hu.elte.EducationSystem.repositories.UserRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    private User getUser(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username).get();
    }

    @GetMapping("")
    public String list(Model model, Principal principal) {
        User user = getUser(principal);
        Iterable<Course> courses = user.getRole() == User.Role.ROLE_USER
                ? user.getCourses()
                : courseRepo.findAll();
        model.addAttribute("courses", courses);
        return "list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course-form";
    }

    @PostMapping("/new")
    public String addcourse(@Valid Course course,
                           BindingResult bindingResult,
                            Principal principal) {
        if (bindingResult.hasErrors()) {
            return "course-form";
        }
        User user = this.getUser(principal);
        course.setUser(user);
        courseRepo.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String showcourse(@PathVariable int id,
                            Model model) {
        Optional<Course> ocourse = courseRepo.findById(id);
        if (ocourse.isPresent()) {
            Course course = ocourse.get();
            model.addAttribute("course", course);
            return "course";
        }
        //throw new Exception("No such course id");
        return "";
    }
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id,
                               Model model) throws Exception {
        Optional<Course> oCourse = courseRepo.findById(id);
        if (oCourse.isPresent()) {
            model.addAttribute("course", oCourse.get());
            return "course-form";
        }
        throw new Exception("No such course id");
    }

    @PostMapping("/{id}/edit")
    public String editcourse(
            @Valid Course course,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            return "course-form";
        }
        User user = this.getUser(principal);
        course.setUser(user);
        courseRepo.save(course);
        return "redirect:/courses";
    }
    @GetMapping("/{id}/editStatusGet")
    public String editStatusGet (@PathVariable int id,
                                 Model model) throws Exception {
        Optional<Course> ocourse = courseRepo.findById(id);
        if (ocourse.isPresent()) {
            Course course = ocourse.get();
            model.addAttribute("course", course);
            if (course.getPublished()) {
                course.setPublished(false);
            } else {
                course.setPublished(true);
            }
            courseRepo.save(course);
            return "redirect:/courses";
        }
        throw new Exception("No such course id");
    }
}
