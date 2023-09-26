import os.path

def methodLoadingFile(pathDataSource):
    print(f'#Python path: {pathDataSource}')
    try:
        print('loading')
        with open(pathDataSource, 'r') as file:
            text = file.read()
            print(f'ok -> {text}')
    except Exception as e:
        print(f'Erro: {e}')

# if __name__ == '__main__':
#     methodLoadingFile("/Users/fabio.henrique/Workspace-fabex/java-polyglot-with-python/src/main/resources/datasource/test-file-build.csv")