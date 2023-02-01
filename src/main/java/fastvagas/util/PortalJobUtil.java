package fastvagas.util;

import fastvagas.entity.Job;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortalJobUtil {

  public static Map<String, Job> listToMapByUrl(List<Job> list) {
    Map<String, Job> portalJobMap = new HashMap<>();
    list.forEach((portalJob) -> portalJobMap.put(portalJob.getJobUrl(), portalJob));
    return portalJobMap;
  }
}
