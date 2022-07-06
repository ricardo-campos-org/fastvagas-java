package fastvagas.rest;

import fastvagas.json.HomeJson;
import fastvagas.json.JobPagination;
import fastvagas.service.AuthService;
import fastvagas.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/all-jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public HomeJson getAllJobs() {
        return homeService.getAllJobs(authService.getCurrentUser());
    }

    @GetMapping(value = "/last-jobs", produces = "application/json")
    public JobPagination getAllJobs(@RequestParam("city_id") Long city_id,
                                    @RequestParam("page") Integer page) {
        return homeService.getLastJobs(city_id, page);
    }
}
