package fastvagas.rest;

import fastvagas.dal.entity.User;
import fastvagas.json.AppUserJob;
import fastvagas.service.AppJobService;
import fastvagas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/jobs")
public class JobsRest {

    @Autowired
    private AppJobService appJobService;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/user", produces = "application/json")
    public AppUserJob getAppUserJobs() {
        User user = authService.getUser();
        return appJobService.getAppUserJobs(user);
    }
}
