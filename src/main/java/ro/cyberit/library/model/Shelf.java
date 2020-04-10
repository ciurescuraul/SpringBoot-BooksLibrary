package ro.cyberit.library.model;

public class Shelf {
    private Long id;
    private String genre;

    @Override
    public String toString() {
        return "Shelf{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
