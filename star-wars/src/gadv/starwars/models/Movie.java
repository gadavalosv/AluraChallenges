package gadv.starwars.models;

public class Movie {
    private final String title;
    private final String release_date;
    private final String director;
    private final String producer;
    private final int episode_id;
    private final String opening_crawl;
    public Movie(int episode_id, String title, String release_date, String director, String producer, String opening_crawl) {
        this.title = title;
        this.release_date = release_date;
        this.director = director;
        this.producer = producer;
        this.episode_id = episode_id;
        this.opening_crawl = opening_crawl;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", episode_id=" + episode_id +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", episode_id=" + episode_id +
                ", opening_crawl='" + opening_crawl + '\'' +
                '}';
    }
}
