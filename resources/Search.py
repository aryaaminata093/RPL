import datetime
from flask import request, Response
from flask_restful import Resource
from flask_jwt_extended import jwt_required, get_jwt_identity
from .Model import db, Dokter, Jadwal, SearchSchema

class Search(Resource):
	def get(self, data):

		hasil = db.session.query(Jadwal.id.label("jadwalId"), Jadwal.hari, Jadwal.jamMulai, Jadwal.jamSelesai, Dokter.id.label("dokterId"), Dokter.nama, Dokter.spesialis, Dokter.noTelepon).join(Jadwal, Jadwal.dokterId == Dokter.id).filter((Dokter.nama == data)|(Dokter.spesialis == data)|(Jadwal.hari==data)).all()
		# hasil = db.session.query(Jadwal.hari, Dokter.id.label("dokterId")).join(Jadwal, Jadwal.dokterId == Dokter.id).all()
		# antrian = antrian.noAntrian
		hasil = SearchSchema(many=True).dump(hasil).data

		return {'status' : 'success', 'data': hasil}, 200
		