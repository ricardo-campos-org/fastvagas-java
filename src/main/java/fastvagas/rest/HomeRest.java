package fastvagas.rest;

import fastvagas.dal.entity.User;
import fastvagas.json.HomeJson;
import fastvagas.json.JobPagination;
import fastvagas.service.AuthService;
import fastvagas.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/home")
public class HomeRest {

    @Autowired
    AuthService authService;

    @Autowired
    HomeService homeService;

    @GetMapping(value = "/current-user")
    public User getLoggedUser() {
        User user = authService.getCurrentUser();
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    @GetMapping(value = "/all-jobs", produces = "application/json")
    public HomeJson getAllJobs() {
        return homeService.getAllJobs(authService.getCurrentUser());
    }

    @GetMapping(value = "/last-jobs", produces = "application/json")
    public JobPagination getAllJobs(@RequestParam("city_id") Long city_id,
                                    @RequestParam("page") Integer page) {
        return homeService.getLastJobs(city_id, page);
    }
}
