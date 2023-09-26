import polyglot as poly
import sys


def methodHello():
    print('Hello world')


def methodVersionPython():
    print(sys.version)


def methodControllingValueToHostAndPython():
    print(metaDado.getDetail())
    metaDado.setValue("Ops !!!")


def methodSharedObject():
    variableSharedBooleanObject = poly.import_value('sharedBooleanObject')
    print(f'#Python.print old value variableSharedBooleanObject: {variableSharedBooleanObject}')
    print('#Python.print update value for True')
    variableSharedBooleanObject = True
    print(f'#Python.print new value variableSharedBooleanObject: {variableSharedBooleanObject}')


def methodSharedValueObject():
    valueIncorporated = poly.import_value('valueIncorporated')
    print(f'#Python.print old value valueIncorporated: {valueIncorporated.getValue()}')
    print(f'#Python.print update value for \'Update !!!\'')
    valueIncorporated.setValue("Update !!!")
    print(f'#Python.print new value valueIncorporated: {valueIncorporated.getValue()}')


poly.export_value("methodHello", methodHello)
poly.export_value("methodVersionPython", methodVersionPython)
poly.export_value("methodControllingValueToHostAndPython", methodControllingValueToHostAndPython)
poly.export_value("methodSharedObject", methodSharedObject)
poly.export_value("methodSharedValueObject", methodSharedValueObject)
