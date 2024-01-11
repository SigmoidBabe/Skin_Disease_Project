package com.example.wraphumic;

//import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class ImageClassifier {
    /*private Interpreter tflite;

    public ImageClassifier(AssetManager assetManager, String modelPath) throws IOException {
        Interpreter.Options options = new Interpreter.Options();
        tflite = new Interpreter(loadModelFile(assetManager, modelPath), options);
    }

    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public String classifyImage(Bitmap image) {
        int NUM_CLASSES = 2;
        int IMAGE_WIDTH = 224;
        int IMAGE_HEIGHT = 224;
        int IMAGE_CHANNELS = 3;
        String predictedLabel;

        // Inisialisasi array input dan output
        float[][] outputScores = new float[1][NUM_CLASSES];
        float[][][][] inputBuffer = new float[1][IMAGE_WIDTH][IMAGE_HEIGHT][IMAGE_CHANNELS];

        for (int x = 0; x < IMAGE_HEIGHT; ++x) {
            for (int y = 0; y < IMAGE_WIDTH; ++y) {
                int pixel = image.getPixel(x, y);
                inputBuffer[0][x][y][0] = Color.red(pixel) / 255.0f;   // Komponen R
                inputBuffer[0][x][y][1] = Color.green(pixel) / 255.0f; // Komponen G
                inputBuffer[0][x][y][2] = Color.blue(pixel) / 255.0f;  // Komponen B
            }
        }

        // Mengisi inputBuffer dengan data gambar
        // ...
        // Jalankan prediksi menggunakan model
        tflite.run(inputBuffer, outputScores);

        // Proses outputScores untuk mendapatkan label prediksi
        // ...
        if(outputScores[0][0]<1)
        {
            predictedLabel = "Negatif";
        }
        else{predictedLabel = "Positif";}

        // Kembalikan hasil prediksi
        return predictedLabel;
    }*/
}


