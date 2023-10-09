import site  # ou .option("python.ForceImportSite", "true") em contexto
import polyglot as poly
import numpy as np
import pandas as pd
import time
import logging
import logging.config

log_path, file_name = './', 'app'
logging.basicConfig(
    # format='%(asctime)s | %(levelname)5s | [%(threadName)10s] %(filename)38s:%(lineno)-5d - %(message)s',
    format='[%(threadName)12s] %(message)s',
    level=logging.INFO,
    datefmt='%Y-%d-%m %I:%M:%S',
    handlers=[logging.FileHandler("{0}/{1}.log".format(log_path, file_name)), logging.StreamHandler()])

global_data_rollout = []


def load_data_source_roll_out(pathDataSource):
    global global_data_rollout
    filtered = list(filter(lambda _obj: _obj['path'] == pathDataSource, global_data_rollout))
    if len(filtered) == 0:
        # logging.info('loadding ...')
        user_roll_out_data = pd.read_csv(pathDataSource, delimiter=";", header=0).values
        data_source = {
            'path': pathDataSource,
            'value': user_roll_out_data
        }
        global_data_rollout.append(data_source)
        return user_roll_out_data
    else:
        # logging.info('cache ...')
        return filtered[0]['value']


def method_user_roll_out_analysis(path_data_source, segmentation):
    user_roll_out_data = pd.read_csv(path_data_source, delimiter=";", header=0).values
    user_roll_out_seed_data = user_roll_out_data[np.where((user_roll_out_data[:, 1] == segmentation))]
    return {
        'total': np.count_nonzero(user_roll_out_seed_data[:, 0]),
        'userIds': list(user_roll_out_seed_data[:, 0])
    }


def method_user_roll_out_analysis_optimized(path_data_source, segmentation):
    user_roll_out_data = load_data_source_roll_out(path_data_source)
    user_roll_out_seed_data = user_roll_out_data[np.where((user_roll_out_data[:, 1] == segmentation))]
    return {
        'total': np.count_nonzero(user_roll_out_seed_data[:, 0]),
        'userIds': list(user_roll_out_seed_data[:, 0])
    }


def method_user_roll_out_analysis_optimized_logging(path_data_source, segmentation):
    logging.info(f'#Python path {path_data_source}')
    start_time_load = int(round(time.time() * 1000))
    user_roll_out_data = load_data_source_roll_out(path_data_source)
    logging.info(f'#Python Time(load):{int(round(time.time() * 1000)) - start_time_load}')
    start_time_find = int(round(time.time() * 1000))
    user_roll_out_seed_data = user_roll_out_data[np.where((user_roll_out_data[:, 1] == segmentation))]
    logging.info(f'#Python Time(find):{int(round(time.time() * 1000)) - start_time_find}')
    return {
        'total': np.count_nonzero(user_roll_out_seed_data[:, 0]),
        'userIds': list(user_roll_out_seed_data[:, 0])
    }


# For polyglot programming (Other languages besides host)
poly.export_value("method_user_roll_out_analysis", method_user_roll_out_analysis)
poly.export_value("method_user_roll_out_analysis_optimized", method_user_roll_out_analysis_optimized)
poly.export_value("method_user_roll_out_analysis_optimized_logging", method_user_roll_out_analysis_optimized_logging)
poly.export_value("global_data_rollout", global_data_rollout)

# if __name__ == '__main__':
#     # listloggers()
#     for i in range(100):
#         startTime = int(round(time.time()* 1000))
#         # method_user_roll_out_analysis(
#         # method_user_roll_out_analysis_optimized(
#         method_user_roll_out_analysis_optimized_logging(
#             "/Users/fabio.henrique/Workspace-fabex/java-polyglot-with-python/src/main/resources/dataset/user-rollout.csv",
#             'MEDIUM')
#         logging.info(f'{i}x Execution | Time(sec):{int(round(time.time()* 1000)) - startTime}')
