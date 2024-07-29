from polyglot import export_value
import concurrent.futures


def worker(a, b, c):
    print(f"parameters: {[a, b, c]} | Worker thread running.")


def run():
    # create a thread pool with 10 threads
    pool = concurrent.futures.ThreadPoolExecutor(max_workers=10)
    for i in range(100):
        # submit tasks to the pool
        pool.submit(worker, 1, 2, 3)

    # wait for all tasks to complete
    pool.shutdown(wait=True)

    print("Main thread continuing to run")


export_value("run", run)
