package bhcc.edu.FitnessTracker;

import jakarta.persistence.*;

@Entity
@Table(name = "run")
public class Run {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String route;
    private double distance;
    private String date;

    protected Run() {}

    public Run(String route, double distant, String date) {
        this.route = route;
        this.distance = distant;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distant) {
        this.distance = distant;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return String.format("Run[id=%d,route=%s, distance=%f, date=%s]", id, route, distance, date);
    }
}
