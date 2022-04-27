package fastvagas.util;

import fastvagas.data.entity.PortalJob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortalJobUtil {

    public static Map<String, PortalJob> listToMapByUrl(List<PortalJob> list) {
        Map<String, PortalJob> portalJobMap = new HashMap<>();
        list.forEach((portalJob) -> portalJobMap.put(portalJob.getUrl(), portalJob));
        return portalJobMap;
    }
}
