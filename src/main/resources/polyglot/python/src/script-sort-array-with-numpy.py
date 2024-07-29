from polyglot import export_value
import numpy as np


def method_sort_array(arr):
    return np.sort(list(arr)).tolist()


export_value("method_sort_array", method_sort_array)
