package fastvagas.json;

import fastvagas.entity.PortalJob;
import fastvagas.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalJobResponse {

    private Long portalJobId;
    private String name;
    private String company;
    private String description;
    private String url;
    private LocalDateTime createdAt;
    private String share;

    public static PortalJobResponse fromPortalJob(PortalJob portalJob) {
        PortalJobResponse pjr = PortalJobResponse.builder()
                .portalJobId(portalJob.getId())
                .name(portalJob.getJobTitle())
                .company(portalJob.getCompanyName())
                .description(portalJob.getJobDescription())
                .url(portalJob.getJobUri())
                .createdAt(portalJob.getCreatedAt())
                .build();

        String share = portalJob.getJobTitle() + " - ";
        if (ObjectUtil.hasValue(portalJob.getCompanyName())) {
            share += portalJob.getCompanyName() + " - ";
        }
        int start = 0;
        String description = portalJob.getJobDescription();
        if (description.startsWith("null")) {
            start = 4;
        }
        share += description.substring(start) + " - ";
        share += portalJob.getJobUri();
        pjr.setShare(share);

        return pjr;
    }
}
