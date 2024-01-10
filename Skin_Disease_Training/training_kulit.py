import os
import random
import shutil
import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from shutil import copyfile
from tensorflow.keras.optimizers import RMSprop


def train_val_generators(TRAINING_DIR, VALIDATION_DIR):
    train_datagen = ImageDataGenerator(rescale=1 / 255)

    train_generator = train_datagen.flow_from_directory(directory=TRAINING_DIR,
                                                        batch_size=20,
                                                        class_mode='categorical',
                                                        target_size=(224, 224))

    validation_datagen = ImageDataGenerator(rescale=1 / 255)

    # Pass in the appropriate arguments to the flow_from_directory method
    validation_generator = validation_datagen.flow_from_directory(directory=VALIDATION_DIR,
                                                                  batch_size=20,
                                                                  class_mode='categorical',
                                                                  target_size=(224, 224))
    ### END CODE HERE
    return train_generator, validation_generator


training_dir = 'Dataset/Dataset Kulit/training/'
testing_dir = 'Dataset/Dataset Kulit/testing'

train_generator, validation_generator = train_val_generators(training_dir, testing_dir)

def create_model():
    ### START CODE HERE

    model = tf.keras.models.Sequential([
        tf.keras.layers.Conv2D(16, (3, 3), activation=tf.nn.relu, input_shape=(224, 224, 3)),
        tf.keras.layers.MaxPooling2D((2, 2)),
        tf.keras.layers.Conv2D(32, (3, 3), activation=tf.nn.relu),
        tf.keras.layers.MaxPooling2D((2, 2)),
        tf.keras.layers.Conv2D(64, (3, 3), activation=tf.nn.relu),
        tf.keras.layers.MaxPooling2D((2, 2)),
        tf.keras.layers.Flatten(),
        tf.keras.layers.Dense(128, activation=tf.nn.relu),
        tf.keras.layers.Dense(10, activation=tf.nn.softmax),
    ])

    model.compile(optimizer=RMSprop(lr=0.001),
                  loss='categorical_crossentropy',
                  metrics=['accuracy'])

    ### END CODE HERE

    return model

model_10epoch = create_model()
history = model_10epoch.fit(train_generator,
                    epochs=10,
                    verbose=1,
                    validation_data=validation_generator)


model_10epoch.save('model_kulit.h5')