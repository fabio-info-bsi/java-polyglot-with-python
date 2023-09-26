import polyglot as poly


def add(n1, n2):
    return n1 + n2


def subtract(n1, n2):
    return n1 - n2


def multiply(n1, n2):
    return n1 * n2


def divide(n1, n2):
    return n1 / n2


poly.export_value("add", add)
poly.export_value("subtract", subtract)
poly.export_value("multiply", multiply)
poly.export_value("divide", divide)
