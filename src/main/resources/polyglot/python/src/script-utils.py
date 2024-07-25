import polyglot as poly
import sys


def method_hello():
    print('#Python.print#method_hello Hello world')


def method_version_python():
    print(sys.version)


def method_controlling_value_to_host_and_python():
    print(f'#Python.print#method_controlling_value_to_host_and_python {metaDado.getDetail()}')
    metaDado.setValue("Ops !!!")


def method_shared_object():
    variableSharedBooleanObject = poly.import_value('sharedBooleanObject')
    print(f'#Python.print#methodSharedObject old value variableSharedBooleanObject: {variableSharedBooleanObject}')
    print('#Python.print#methodSharedObject update value for True')
    variableSharedBooleanObject = True
    print(f'#Python.print#methodSharedObject new value variableSharedBooleanObject: {variableSharedBooleanObject}')


def method_shared_value_object():
    valueIncorporated = poly.import_value('valueIncorporated')
    print(f'#Python.print#methodSharedValueObject old value valueIncorporated: {valueIncorporated.getValue()}')
    print(f'#Python.print#methodSharedValueObject update value for \'Update !!!\'')
    valueIncorporated.setValue("Update !!!")
    print(f'#Python.print#methodSharedValueObject new value valueIncorporated: {valueIncorporated.getValue()}')


poly.export_value("method_hello", method_hello)
poly.export_value("method_version_python", method_version_python)
poly.export_value("method_controlling_value_to_host_and_python", method_controlling_value_to_host_and_python)
poly.export_value("method_shared_object", method_shared_object)
poly.export_value("method_shared_value_object", method_shared_value_object)
