package fastvagas.json;

import fastvagas.data.entity.PortalJob;
import fastvagas.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalJobResponse implements Serializable {

    private static final long serialVersionUID = 1920774283340602960L;
    private Long portalJobId;
    private String name;
    private String company;
    private String description;
    private String url;
    private LocalDateTime createdAt;
    private String share;

    public static PortalJobResponse fromPortalJob(PortalJob portalJob) {
        PortalJobResponse pjr = PortalJobResponse.builder()
                .portalJobId(portalJob.getPortal_job_id())
                .name(portalJob.getName())
                .company(portalJob.getCompany_name())
                .description(portalJob.getDescription())
                .url(portalJob.getUrl())
                .createdAt(portalJob.getCreated_at())
                .build();

        String share = portalJob.getName() + " - ";
        if (ObjectUtil.hasValue(portalJob.getCompany_name())) {
            share += portalJob.getCompany_name() + " - ";
        }
        int start = 0;
        String description = portalJob.getDescription();
        if (description.startsWith("null")) {
            start = 4;
        }
        share += description.substring(start) + " - ";
        share += portalJob.getUrl();
        pjr.setShare(share);

        return pjr;
    }
}
