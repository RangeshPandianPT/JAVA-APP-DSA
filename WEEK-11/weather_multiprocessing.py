from multiprocessing import Process, Queue
import random, os, time
from statistics import mean

def simulate_region(readings_per_region: int, out: Queue):
    rng = random.Random()
    rng.seed(os.getpid() ^ int(time.time() * 1_000_000))
    temps = [rng.uniform(15.0, 40.0) for _ in range(readings_per_region)]  
    out.put((os.getpid(), temps))  

if __name__ == "__main__":
    NUM_REGIONS = 4
    READINGS_PER_REGION = 5

    q = Queue()
    procs = [Process(target=simulate_region, args=(READINGS_PER_REGION, q)) for _ in range(NUM_REGIONS)]

    for p in procs:
        p.start()

    results = {}
    for _ in range(NUM_REGIONS):
        pid, temps = q.get()  
        results[pid] = temps

    for p in procs:
        p.join()

    all_readings = [t for temps in results.values() for t in temps]
    overall_avg = mean(all_readings)

    print("Per-process readings:")
    for pid, temps in results.items():
        print(f"  PID {pid}: {', '.join(f'{t:.2f}' for t in temps)}")

    print(f"\nTotal readings: {len(all_readings)}")
    print(f"Overall average temperature: {overall_avg:.2f} °C")
