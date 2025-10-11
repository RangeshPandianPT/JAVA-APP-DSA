import java.util.*;

class Process {
    String id;
    int arrival, burst, priority;
    int waiting, turnaround, start, finish;

    Process(String id, int arrival, int burst, int priority) {
        this.id = id;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
    }
}

public class PriorityScheduler {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        System.out.println("Enter process details (ID Arrival Burst Priority):");
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            int arrival = sc.nextInt();
            int burst = sc.nextInt();
            int priority = sc.nextInt();
            processes.add(new Process(id, arrival, burst, priority));
        }

        System.out.print("Enter aging time (T): ");
        int agingTime = sc.nextInt();

        schedule(processes, agingTime);
    }

    static void schedule(List<Process> processes, int agingTime) {
        int time = 0, completed = 0;
        List<String> order = new ArrayList<>();

        processes.sort(Comparator.comparingInt(p -> p.arrival));

        List<Process> ready = new ArrayList<>();

        while (completed < processes.size()) {
            for (Process p : processes) {
                if (p.arrival <= time && !ready.contains(p) && p.finish == 0) {
                    ready.add(p);
                }
            }
            if (agingTime > 0) {
                for (Process p : ready) {
                    if (p.finish == 0 && (time - p.arrival) > 0 && (time - p.arrival) % agingTime == 0) {
                        p.priority++;
                    }
                }
            }
            if (ready.isEmpty()) {
                time++;
                continue;
            }
            ready.sort((a, b) -> {
                if (b.priority != a.priority)
                    return b.priority - a.priority;
                return a.arrival - b.arrival;
            });

            Process current = ready.remove(0);
            current.start = Math.max(time, current.arrival);
            current.finish = current.start + current.burst;
            current.waiting = current.start - current.arrival;
            current.turnaround = current.finish - current.arrival;

            time = current.finish;
            completed++;
            order.add(current.id);
        }
        System.out.println("\nExecution Order: " + String.join(" → ", order));
        System.out.println("Process\tArr\tBurst\tPrio\tStart\tFinish\tWait\tTAT");
        double totalWait = 0, totalTAT = 0;

        for (Process p : processes) {
            System.out.printf("%s\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n",
                    p.id, p.arrival, p.burst, p.priority, p.start, p.finish, p.waiting, p.turnaround);
            totalWait += p.waiting;
            totalTAT += p.turnaround;
        }
        System.out.printf("Average Waiting Time: %.2f\n", totalWait / processes.size());
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / processes.size());
    }
}
