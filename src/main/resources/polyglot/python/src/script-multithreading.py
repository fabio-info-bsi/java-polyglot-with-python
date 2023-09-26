import polyglot as poly
import concurrent.futures

def worker(a, b, c):
    print(f"parameters: {[a, b, c]} | Worker thread running.")

def run():
    # create a thread pool with 10 threads
    pool = concurrent.futures.ThreadPoolExecutor(max_workers=10)
    for i in range(10):
        # submit tasks to the pool
        pool.submit(worker, 1,2,3)

    # wait for all tasks to complete
    pool.shutdown(wait=True)

    print("Main thread continuing to run")

poly.export_value("run", run)
