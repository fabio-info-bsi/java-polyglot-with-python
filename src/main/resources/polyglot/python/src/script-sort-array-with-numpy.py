import site
import polyglot as poly
import numpy as np


def method_sort_array(arr):
    newArr = np.sort(list(arr))
    return newArr.tolist()


poly.export_value("method_sort_array", method_sort_array)
