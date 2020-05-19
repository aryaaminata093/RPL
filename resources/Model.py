from flask import Flask
from marshmallow import Schema, fields, pre_load, validate
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import generate_password_hash, check_password_hash

ma = Marshmallow()
db = SQLAlchemy()

##############################################
# USER
class User(db.Model):
	"""docstring for User"""
	__tablename__ = 'Users'
	id = db.Column(db.Integer, primary_key=True)
	
	email = db.Column(db.String(120), unique=True, nullable = False)
	password = db.Column(db.String(80), unique=True, nullable=False)
	profileId = db.Column(db.Integer, db.ForeignKey('Profiles.id'), nullable=False, unique = True)
	profile = db.relationship('Profile', backref=db.backref('Users', uselist=False))

	creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)
	
	def __init__(self, email, password, profileId):
		self.email = email
		self.password = password
		self.profileId = profileId

	def hash_password(self):
		self.password = generate_password_hash(self.password)
	
	def check_password(self, password):
		return check_password_hash(self.password, password)

class UserSchema(ma.Schema):
	id = fields.Integer()
	email = fields.Email(required = True)
	password = fields.String(required=True, validate=validate.Length(8))
	profileId = fields.Integer(required=True)
	creation_date = fields.DateTime()

##############################################
# PROFILE
class Profile(db.Model):
	__tablename__ = 'Profiles'

	id = db.Column(db.Integer, primary_key=True)
	nama = db.Column(db.String(25), nullable=False)
	tempat_lahir = db.Column(db.String(20), nullable=False)
	tanggal_lahir = db.Column(db.Date, nullable=False)
	jenis_kelamin = db.Column(db.String(1), nullable=False)
	gol_darah = db.Column(db.String(1), nullable=False)
	alamat = db.Column(db.String(100), nullable=False)
	creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)

	def __init__(self, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, gol_darah, alamat):
		self.nama = nama
		self.tempat_lahir = tempat_lahir
		self.tanggal_lahir = tanggal_lahir
		self.jenis_kelamin = jenis_kelamin
		self.gol_darah = gol_darah
		self.alamat = alamat


class ProfileSchema(ma.Schema):
	id = fields.Integer()
	nama = fields.String(required=True)
	tempat_lahir = fields.String(required=True)
	tanggal_lahir = fields.Date(required=True)
	jenis_kelamin = fields.String(required=True, validate=validate.Length(min = 1, max = 1))
	gol_darah = fields.String(required=True, validate=validate.Length(min = 1, max = 1))
	alamat = fields.String(required = True)
	
##############################################
# DOKTER
class Dokter(db.Model):
	__tablename__ = 'Dokter'

	id = db.Column(db.Integer, primary_key=True)
	
	nama = db.Column(db.String(25), nullable=False)
	spesialis = db.Column(db.String(20), nullable=False)
	noTelepon = db.Column(db.String(13), nullable=False)
	
	creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)

	def __init__(self, nama, spesialis, noTelepon ):
		self.nama = nama
		self.spesialis = spesialis
		self.noTelepon = noTelepon
		
	
class DokterSchema(ma.Schema):
	id = fields.Integer()
	nama = fields.String(required=True)
	spesialis = fields.String(required=True)
	noTelepon = fields.String(required=True)

##############################################
# JADWAL


class Jadwal(db.Model):
	__tablename__ = 'Jadwal'

	id = db.Column(db.Integer, primary_key=True)
	
	hari = db.Column(db.String(25), nullable=False)
	jamMulai = db.Column(db.Time, nullable=False)
	jamSelesai = db.Column(db.Time, nullable=False)
	
	dokterId = db.Column(db.Integer, db.ForeignKey('Dokter.id'), nullable=False)
	dokter = db.relationship('Dokter', backref=db.backref('Jadwal'))

	creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)

	def __init__(self, hari, jamMulai, jamSelesai, dokterId):
		self.hari = hari
		self.jamMulai = jamMulai
		self.jamSelesai = jamSelesai
		self.dokterId = dokterId
	
class JadwalSchema(ma.Schema):
	id = fields.Integer()
	hari = fields.String(required=True)
	jamMulai = fields.Time(required=True)
	jamSelesai = fields.Time(required=True)
	dokterId = fields.Integer(required=True)

##############################################
# Antrian

class Antrian(db.Model):
	__tablename__ = 'Antrian'

	# id = db.Column(db.Integer, primary_key=True)
	
	noAntrian = db.Column(db.Integer, nullable=False)
	tanggal = db.Column(db.Date, nullable=False)
	status = db.Column(db.Boolean, default = False)
	jadwalId = db.Column(db.Integer, db.ForeignKey('Jadwal.id'), nullable=False, primary_key = True)
	profileId = db.Column(db.Integer, db.ForeignKey('Profiles.id'), nullable=False, primary_key = True)
	
	dokter = db.relationship('Jadwal', backref=db.backref('Antrian'))
	profile = db.relationship('Profile', backref=db.backref('Antrian'))
	
	creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)

	def __init__(self, noAntrian, tanggal, profileId, jadwalId):
		self.noAntrian = noAntrian
		self.tanggal = tanggal
		self.profileId = profileId
		self.jadwalId = jadwalId
	
class AntrianSchema(ma.Schema):
	# id = fields.Integer()
	noAntrian = fields.Integer(required=True)
	tanggal = fields.Date(required=True)
	status = fields.Boolean(required=True)
	jadwalId = fields.Integer(required=True)
	profileId = fields.Integer(required=True)

class AntrianQuerySchema(ma.Schema):
	# id = fields.Integer()
	# noAntrian = fields.Integer(required=True)
	tanggal = fields.Date(required=True)
	jadwalId = fields.Integer(required=True)

class AntrianAddSchema(ma.Schema):
	# id = fields.Integer()
	# noAntrian = fields.Integer(required=True)
	tanggal = fields.Date(required=True)
	jadwalId = fields.Integer(required=True)
	profileId = fields.Integer(required=True)

class AntrianAddSchema(ma.Schema):
	# id = fields.Integer()
	# noAntrian = fields.Integer(required=True)
	tanggal = fields.Date(required=True)
	jadwalId = fields.Integer(required=True)
	profileId = fields.Integer(required=True)

##############################################
# Search

class SearchSchema(ma.Schema):
	# id = fields.Integer()
	jadwalId = fields.Integer(required=True)
	dokterId = fields.Integer(required=True)

	hari = fields.String(required=True)
	jamMulai = fields.Time(required=True)
	jamSelesai = fields.Time(required=True)
	# dokterId = fields.Integer(required=True)
	nama = fields.String(required=True)
	spesialis = fields.String(required=True)
	noTelepon = fields.String(required=True)
