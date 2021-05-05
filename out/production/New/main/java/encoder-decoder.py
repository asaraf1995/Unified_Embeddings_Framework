import os
os.system("pip install mysql-connector")
os.system("pip install numpy")
os.system("pip install tensorflow")
os.system("pip install keras")
os.system("pip install matplotlib")
os.system("pip install sklearn")
# import sys
# import subprocess
# # subprocess.run('source activate conda_env && "run encoder-decoder.py" && source deactivate', shell=True)
# # implement pip as a subprocess:
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'mysql-connector'])
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'tensorflow'])
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'keras'])
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'numpy'])
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'sklearn'])
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'matplotlib'])
# subprocess.check_call([sys.executable, '-m', 'pip', 'install',
#                        'pandas'])

import mysql.connector
from mysql.connector import errorcode
from sklearn import preprocessing
from sklearn.metrics import confusion_matrix, accuracy_score, roc_curve, auc
from keras import optimizers, regularizers
from keras.layers import Activation, Dense, Dropout, Input
from keras.models import Sequential, Model
from sklearn.model_selection import train_test_split
import keras as keras
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# Data Import
conn = mysql.connector.connect(user='root', password='Kunwar_IIT13',
                               host='127.0.0.1',
                               database='database_schema')
cursor = conn.cursor()
query = 'select * from ml_temp'
results = pd.read_sql_query(query, conn)
results.to_csv("ml_temp1.csv", index=False)
data = pd.read_csv('ml_temp1.csv')
user_id = data["userid"]

# remove unnessacry variables
df = data.drop(['userid'], axis=1)
list_of_column_names = list(df.columns)

# Encode categorical data to ONE-HOT
categorical_data = ['gender']
df = pd.get_dummies(df, columns=categorical_data)

# Scale the data to [0,1] range
columns_to_scale = list_of_column_names
df2 = pd.DataFrame(np.random.randint(
    12, 1000, size=(len(user_id), 199)), columns=list_of_column_names)
df2[columns_to_scale] = df2[columns_to_scale].apply(
    lambda x: (x-x.min())/(x.max()-x.min()))

# Split in 75% train and 25% test set
X = df2.iloc[:, :]
X_train, X_test, y_train, y_test = train_test_split(
    X, X, test_size=0.2, random_state=2)
print(X_train.shape, X_test.shape, y_train.shape, y_test.shape)

# reduce to 32 features
encoding_dim = 32

input_df = Input(shape=(X_train.shape[1],))
encoded = Dense(encoding_dim, activation='relu')(input_df)
decoded = Dense(X_train.shape[1], activation='sigmoid')(encoded)

# encoder
autoencoder = Model(input_df, decoded)

autoencoder.compile(optimizer='adam', loss='mean_squared_logarithmic_error')
# autoencoder.compile(optimizer='adam', loss='binary_crossentropy')

autoencoder_model = autoencoder.fit(X_train, X_train,
                                    epochs=200,
                                    batch_size=100,
                                    shuffle=True,
                                    validation_data=(X_test, X_test))

# Summarize history for loss
# plt.figure()
# plt.plot(autoencoder_model.history['loss'])
# plt.plot(autoencoder_model.history['val_loss'])
# plt.title('Autoencoder model loss')
# plt.ylabel('loss')
# plt.xlabel('epoch')
# plt.legend(['train', 'test'], loc='upper right')
# plt.show()

encoder = Model(input_df, encoded)
encoded_x = encoder.predict(X)
new_table = []
for i in range(len(encoded_x)):
    new_table.append([(user_id[i]), str(encoded_x[i])])

# try:
#     cnx = mysql.connector.connect(user='root', password='Kunwar_IIT13',
#                                   host='127.0.0.1',
#                                   database='database_schema')
# except mysql.connector.Error as err:
#     if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
#         print("Something is wrong with your user name or password")
#     elif err.errno == errorcode.ER_BAD_DB_ERROR:
#         print("Database does not exist")
#     else:
#         print(err)
# else:
cursor = conn.cursor()
cursor.execute("DROP TABLE IF EXISTS embeddings_table")
cursor.execute("CREATE TABLE `embeddings_table` ("
               "  `User_id` int NOT NULL, `Embeddings` VARCHAR(1000))")
for i in range(len(new_table)):
    #         print(new_table[i][0], new_table[i][1])
    # data = (new_table[i][0], str(new_table[i][1]))
    insert_query = """INSERT INTO embeddings_table (User_id, Embeddings) VALUES (%s, %s)"""
    cursor.execute(
        insert_query, (int(new_table[i][0]), str(new_table[i][1])))
conn.commit()
