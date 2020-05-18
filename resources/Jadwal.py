import datetime
from flask import request, Response
from flask_restful import Resource
from flask_jwt_extended import jwt_required, get_jwt_identity
from .Model import db, Jadwal, JadwalSchema

# source = https://gist.github.com/hest/8798884
def get_count(q):
    return q.with_entities(func.count()).scalar()

class JadwalFetch(Resource):
	# @jwt_required
	def get(self):
		jadwal = Jadwal.query.all()
		jadwal = JadwalSchema(many=True).dump(jadwal).data

		return {'status' : 'success', 'data': jadwal}, 200

class InputJadwal(Resource):
	def post(self):
		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		# Validate and deserialize input
		data, errors = JadwalSchema().load(json_data)
		if errors:
			return {"status": "error", "data": errors}, 422

		jumlah_jadwal = Jadwal.query.filter((Jadwal.hari == json_data['hari']), (json_data['jamMulai'] >= Jadwal.jamMulai), (json_data['jamSelesai'] <= Jadwal.jamSelesai), (json_data['dokterId'] == Jadwal.dokterId)).first()
		# jumlah_jadwal = get_count(jumlah_jadwal)
		if jumlah_jadwal is not None:
			jumlah_jadwal = JadwalSchema().dump(jumlah_jadwal).data
			return {"message" : "Jadwal bentrok", "jadwal" : jumlah_jadwal}, 409
		jadwal = Jadwal(
			hari = json_data['hari']
			,jamMulai = json_data['jamMulai']
			,jamSelesai = json_data['jamSelesai']
			,dokterId = json_data['dokterId']
			)
		db.session.add(jadwal)
		db.session.commit()
		jadwal = JadwalSchema().dump(jadwal).data
		return {"status": "success", "data": jadwal}, 200