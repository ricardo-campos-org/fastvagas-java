package fastvagas.crawler;

public class CrawlerFactory {
    
    public static Crawler createInstance(String className) {
        // Curitiba
        if (className.equals("CuritibaIndeed")) {
            return new CuritibaIndeed();
        }

        // Joinville
        if (className.equals("JoinvilleBNE")) {
            return new JoinvilleBNE();
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

        // São Paulo
        if (className.equals("SaoPauloIndeed")) {
            return new SaoPauloIndeed();
        }

        return null;
    }
}
