import site
import pandas as pd
import time

# reading the csv file
df = pd.read_csv("../../../dataset/user-rollout.csv", delimiter=";")

# updating the column value/data
df['user_id'] = df.index * round(time.time() * 1000)

# writing into the file
df.to_csv("../../../dataset/user-rollout-encrypt.csv", index=False, sep=";")

