from flask import Flask, render_template, redirect, url_for, make_response, request
from werkzeug.utils import secure_filename
import tensorflow as tf
from tensorflow.keras.preprocessing import image
import numpy as np
import os
from objek_resource import db, Pengguna, app
import io
from base64 import encodebytes
from PIL import Image
from flask import jsonify

app.config['UPLOAD_FOLDER'] = 'static/uploads/'
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024
model_serviks = tf.keras.models.load_model('model_serviks.h5')
model_kulit = tf.keras.models.load_model('model_kulit.h5')

@app.route('/register', methods=('GET', 'POST'))
def register():
    if request.method == 'POST':
        Nama = request.form['Nama']
        Username = request.form['Username']
        Password = request.form['Password']
        pengguna = Pengguna(nama=Nama, username=Username, password=Password)
        db.session.add(pengguna)
        db.session.commit()

@app.route('/login', methods=('GET', 'POST'))
def login():
    global State
    State = 'Idle'
    if request.method == 'POST':
        Username = request.form['Username']
        Password = request.form['Password']
        if Pengguna.query.filter_by(username=Username, password=Password).first() is not None:
            State = Username
            return State
        else:
            State = 'False'
            return State

@app.route('/Upload', methods=('GET', 'POST'))
def Upload():
    if request.method == 'POST':
        gambar_upload = request.files['gambar_post'].read()
        pengguna = Pengguna.query.filter_by(username=State).first()
        pengguna.gambar = gambar_upload
        db.session.commit()
    return 'mengupload'

@app.route('/Prediksi', methods=('GET', 'POST'))
def prediksi():
    global State
    gambar_download = Pengguna.query.filter_by(username=State).first().gambar
    gambar_inferensi = Image.open(io.BytesIO(gambar_download)).resize((224, 224))
    img_array = image.img_to_array(gambar_inferensi)
    img_batch = np.expand_dims(img_array, axis=0)
    hasil_prediksi = model_kulit.predict(img_batch)
    if hasil_prediksi[0][0]==1:
        tampilan = 'Tidak ada Penyakit Kulit'
        #Pengguna.query.filter_by(username=State).first().hasil = tampilan
    elif hasil_prediksi[0][1]==1:
        tampilan = 'Sel Basal'
        #Pengguna.query.filter_by(username=State).first().hasil = tampilan
    elif hasil_prediksi[0][2]==1:
        tampilan = 'Benign Keratores'
    elif hasil_prediksi[0][3]==1:
        tampilan = 'Eczema'
    elif hasil_prediksi[0][4]==1:
        tampilan = 'Infeksi Jamur'
    elif hasil_prediksi[0][5]==1:
        tampilan = 'Melanocytic'
    elif hasil_prediksi[0][6]==1:
        tampilan = 'Melanoma'
    elif hasil_prediksi[0][7]==1:
        tampilan = 'Psoriasis'
    elif hasil_prediksi[0][8]==1:
        tampilan = 'Seboroik'
    elif hasil_prediksi[0][9]==1:
        tampilan = 'Moluscum'
    return tampilan

@app.route('/Data', methods=('GET', 'POST'))
def Data():
    global state
    pengguna = Pengguna.query.filter_by(username=State).first()
    nama = pengguna.nama
    hasil = pengguna.hasil
    respon = {'nama' : nama, 'hasil' : hasil}
    return jsonify(respon)

@app.route('/tampilkan_gambar', methods=('GET', 'POST'))
def tampilkan_gambar():
    global state
    gambar_download = Pengguna.query.filter_by(username=State).first().gambar
    encoded_img = encodebytes(byte_arr.getvalue()).decode('ascii')  # encode as base64
    return encoded_img

if __name__ == "__main__":
    app.run(host='0.0.0.0')


