package fastvagas.json;

import fastvagas.entity.PortalJob;
import fastvagas.util.ObjectUtil;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** This class represents a response from a Portal Job query. */
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

  /**
   * Create a PortalJobResponse from a PortalJob.
   *
   * @param portalJob a PortalJob instance to be used
   * @return a PortalJobResponse instance.
   */
  public static PortalJobResponse fromPortalJob(PortalJob portalJob) {
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
    share += portalJob.getJobUrl();

    PortalJobResponse pjr =
        PortalJobResponse.builder()
            .portalJobId(portalJob.getId())
            .name(portalJob.getJobTitle())
            .company(portalJob.getCompanyName())
            .description(portalJob.getJobDescription())
            .url(portalJob.getJobUrl())
            .createdAt(portalJob.getCreatedAt())
            .build();
    pjr.setShare(share);

    return pjr;
  }
}
