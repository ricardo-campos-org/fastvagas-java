package fastvagas.util;

import fastvagas.entity.Job;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class holds methods to convert and handle {@link Job} lists. */
public class PortalJobUtil {

  private PortalJobUtil() {}

  /**
   * Convert a {@link List} of {@link Job} into a {@link Map} having the job uri as key
   * and the job instance as value.
   *
   * @param list The list to be converted
   * @return A map containing all jobs
   */
  public static Map<String, Job> listToMapByUrl(List<Job> list) {
    Map<String, Job> portalJobMap = new HashMap<>();
    list.forEach((portalJob) -> portalJobMap.put(portalJob.getJobUrl(), portalJob));
    return portalJobMap;
  }
}
