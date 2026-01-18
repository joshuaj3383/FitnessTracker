package bhcc.edu.FitnessTracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tools.jackson.databind.introspect.DefaultAccessorNamingStrategy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class FitnessTrackerController {
    private RunRepository runRepository;

    public FitnessTrackerController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    public RunRepository getRunRepository() {
        return  runRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Run> runs = getAllRuns();

        model.addAttribute("totalMiles", getTotalMiles(runs));
        model.addAttribute("numRuns", runs.size());
        model.addAttribute("runs", runs);

        System.out.println("totalMiles: " + getTotalMiles(runs));
        System.out.println("numRuns: " + runs.size());
        System.out.println("runs: " + Arrays.toString(runs.toArray()));

        return "index";
    }

    @GetMapping("/add")
    public String add(String route, double miles, String date, Model model) {
        if (route == null || route.isEmpty()) {
            route = "Empty";
        }
        if (date == null || date.isEmpty()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.format(new Date());
        }

        Run newRun = new Run(route, miles, date);
        runRepository.save(newRun);

        List<Run> runs = getAllRuns();

        model.addAttribute("totalMiles", getTotalMiles(runs));
        model.addAttribute("numRuns", runs.size());
        model.addAttribute("runs", runs);
        model.addAttribute("message", "New run added: " + newRun.getRoute());

        System.out.println("newRun: " + newRun.getRoute());

        return "index";
    }

    @GetMapping("/delete")
    public String delete(long id, Model model) {
        Run tempRun = runRepository.findById(id);
        runRepository.deleteById(id);
        List<Run> runs = getAllRuns();

        model.addAttribute("totalMiles", getTotalMiles(runs));
        model.addAttribute("numRuns", runs.size());
        model.addAttribute("runs", runs);
        model.addAttribute("message", "Deleted run: " + tempRun.getRoute());

        System.out.println("deleted: " + tempRun.getRoute());

        return "index";
    }

    private double getTotalMiles(List<Run> runs) {
        double totalMiles = 0;
        for (Run run : runs) {
            totalMiles += run.getDistance();
        }

        return totalMiles;
    }


    private List<Run> getAllRuns() {
        if (runRepository == null) {
            return new ArrayList<>();
        }

        Iterable<Run> runIter = runRepository.findAll();
        List<Run> runs = new ArrayList<>();
        for (Run run : runIter) {
            runs.add(run);
        }
        return runs;
    }

}
