import polyglot as poly
import sys


def method_hello():
    print('#Python.print#method_hello Hello world')


def method_version_python():
    print(sys.version)


def method_controlling_value_to_host_and_python():
    """
    meta_data is embedded by host (Java):
    ctx.getBindings("python").putMember("meta_data", metaData);
    """
    print(f'#Python.print#method_controlling_value_to_host_and_python {meta_data.getDetail()}')
    meta_data.setId(2)
    meta_data.setModule("Guest - Python")
    meta_data.setValue("Ops !!!")


def method_shared_object():
    """
    sharedBooleanObject is embedded for share in polyglot contexts by host (Java):
    ctx.getPolyglotBindings().putMember("sharedBooleanObject", sharedBooleanObject);
    """
    variable_shared_boolean_object = poly.import_value('sharedBooleanObject')
    print(
        f'#Python.print#method_shared_object old value variable_shared_boolean_object: {variable_shared_boolean_object}')
    print('#Python.print#method_shared_object update value for True')
    variable_shared_boolean_object = True
    print(
        f'#Python.print#methodSharedObject new value variable_shared_boolean_object: {variable_shared_boolean_object}')


def method_shared_value_object():
    """
    valueIncorporated is embedded for share in polyglot contexts by host (Java):
    ctx.getPolyglotBindings().putMember("valueIncorporated", valueIncorporated);
    """
    value_incorporated = poly.import_value('valueIncorporated')
    print(f'#Python.print#method_shared_value_object old value value_incorporated: {value_incorporated.getValue()}')
    print(f'#Python.print#method_shared_value_object update value for \'Update !!!\'')
    value_incorporated.setValue("Update !!!")
    print(f'#Python.print#method_shared_value_object new value value_incorporated: {value_incorporated.getValue()}')


poly.export_value("method_controlling_value_to_host_and_python", method_controlling_value_to_host_and_python)
poly.export_value("method_shared_object", method_shared_object)
poly.export_value("method_shared_value_object", method_shared_value_object)
