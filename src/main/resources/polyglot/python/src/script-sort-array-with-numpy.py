import site
import polyglot as poly
import numpy as np


def methodSortArray(arr):
    newArr = np.sort(list(arr))
    return newArr.tolist()


poly.export_value("methodSortArray", methodSortArray)
