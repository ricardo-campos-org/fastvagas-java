package fastvagas.rest;

import fastvagas.dal.entity.User;
import fastvagas.json.IndexJson;
import fastvagas.json.JobPagination;
import fastvagas.service.AuthService;
import fastvagas.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/index")
public class IndexRest {

    @Autowired
    AuthService authService;

    @Autowired
    IndexService indexService;

    @GetMapping(value = "/current-user")
    public User getLoggedUser() {
        User user = authService.getCurrentUser();
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    @GetMapping(value = "/all-jobs", produces = "application/json")
    public IndexJson getAllJobs() {
        return indexService.getAllJobs(authService.getCurrentUser());
    }

    @GetMapping(value = "/last-jobs", produces = "application/json")
    public JobPagination getAllJobs(@RequestParam("city_id") Long city_id,
                                    @RequestParam("page") Integer page) {
        return indexService.getLastJobs(city_id, page);
    }
}
