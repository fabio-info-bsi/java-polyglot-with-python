def method_loading_file(path_data_source):
    print(f'#Python path: {path_data_source}')
    try:
        print('loading')
        with open(path_data_source, 'r') as file:
            text = file.read()
            print(f'ok -> {text}')
    except Exception as e:
        print(f'Erro: {e}')


if __name__ == '__main__':
    method_loading_file(
        "/Users/fabio.henrique/Workspace-fabex/java-polyglot-with-python/src/main/resources/dataset/user-rollout.csv")
