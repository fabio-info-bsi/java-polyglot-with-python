import site  # ou .option("python.ForceImportSite", "true") em contexto
import polyglot as poly
import numpy as np
import pandas as pd
import time
import logging
import logging.config
import sys

logPath, fileName = './', 'app'
logging.basicConfig(
    # format='%(asctime)s | %(levelname)5s | [%(threadName)10s] %(filename)38s:%(lineno)-5d - %(message)s',
    format='[%(threadName)12s] %(message)s',
    level=logging.INFO,
    datefmt='%Y-%d-%m %I:%M:%S',
    handlers=[logging.FileHandler("{0}/{1}.log".format(logPath, fileName)), logging.StreamHandler()])

globalDataRollout = []


def loadDataSourceRollOut(pathDataSource):
    global globalDataRollout
    filtered = list(filter(lambda _obj: _obj['path'] == pathDataSource, globalDataRollout))
    if len(filtered) == 0:
        # logging.info('loadding ...')
        userRollOutData = pd.read_csv(pathDataSource, delimiter=";", header=0).values
        dataSource = {
            'path': pathDataSource,
            'value': userRollOutData
        }
        globalDataRollout.append(dataSource)
        return userRollOutData
    else:
        # logging.info('cache ...')
        return filtered[0]['value']


def methodUserRollOutAnalysis(pathDataSource, segmentation):
    userRollOutData = pd.read_csv(pathDataSource, delimiter=";", header=0).values
    userRollOutSeedData = userRollOutData[np.where((userRollOutData[:, 1] == segmentation))]
    return {
        'total': np.count_nonzero(userRollOutSeedData[:, 0]),
        'userIds': list(userRollOutSeedData[:, 0])
    }


def methodUserRollOutAnalysisOptimized(pathDataSource, segmentation):
    userRollOutData = loadDataSourceRollOut(pathDataSource)
    userRollOutSeedData = userRollOutData[np.where((userRollOutData[:, 1] == segmentation))]
    return {
        'total': np.count_nonzero(userRollOutSeedData[:, 0]),
        'userIds': list(userRollOutSeedData[:, 0])
    }


def methodUserRollOutAnalysisOptimizedLogging(pathDataSource, segmentation):
    logging.info(f'#Python path {pathDataSource}')
    startTimeLoad = int(round(time.time() * 1000))
    userRollOutData = loadDataSourceRollOut(pathDataSource)
    logging.info(f'#Python Time(load):{int(round(time.time() * 1000)) - startTimeLoad}')
    startTimeFind = int(round(time.time() * 1000))
    userRollOutSeedData = userRollOutData[np.where((userRollOutData[:, 1] == segmentation))]
    logging.info(f'#Python Time(find):{int(round(time.time() * 1000)) - startTimeFind}')
    return {
        'total': np.count_nonzero(userRollOutSeedData[:, 0]),
        'userIds': list(userRollOutSeedData[:, 0])
    }


poly.export_value("methodUserRollOutAnalysis", methodUserRollOutAnalysis)
poly.export_value("methodUserRollOutAnalysisOptimized", methodUserRollOutAnalysisOptimized)
poly.export_value("methodUserRollOutAnalysisOptimizedLogging", methodUserRollOutAnalysisOptimizedLogging)
poly.export_value("globalDataRollout", globalDataRollout)

# if __name__ == '__main__':
#     # logger = logger()
#     logging.info('#main hello')
#     # logger.info('#methodUserRollOutAnalysis hello')
#     # listloggers()
#     # for i in range(1):
#     #     startTime = int(round(time.time()* 1000))
#     #     # methodUserRollOutAnalysis(
#     #     # methodUserRollOutOptimizeAnalysis(
#     #     methodUserRollOutAnalysisOptimizedLogging(
#     #         "/Users/fabio.henrique/Workspace-fabex/java-polyglot-with-python/src/main/resources/dataset/user-rollout.csv",
#     #         'MEDIUM')
#     #     logging.info(f'{i}x Execution | Time(sec):{int(round(time.time()* 1000)) - startTime}')
