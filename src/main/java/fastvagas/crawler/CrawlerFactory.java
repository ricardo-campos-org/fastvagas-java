package fastvagas.crawler;

/** This class is a factory to create the class intances for each city portal. */
public class CrawlerFactory {

  /**
   * Creates an instance of a given class name.
   *
   * @param className The class name to be created
   * @return a {@link Crawler} instance or null if not found
   */
  public static Crawler createInstance(String className) {
    // Joinville
    if (className.equals("JoinvilleBNE")) {
      return new JoinvilleBne();
    }
    if (className.equals("JoinvilleIndeed")) {
      return new JoinvilleIndeed();
    }
    if (className.equals("JoinvilleInfoJobs")) {
      return new JoinvilleInfoJobs();
    }
    if (className.equals("JoinvilleJoinvilleVagas")) {
      return new JoinvilleJoinvilleVagas();
    }
    if (className.equals("JoinvilleTrabalhaBrasil")) {
      return new JoinvilleTrabalhaBrasil();
    }

    // Testing
    if (className.equals("JoinvilleTesting")) {
      return new JoinvilleTesting();
    }

    return null;
  }
}
