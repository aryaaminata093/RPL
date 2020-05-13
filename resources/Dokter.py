import datetime
from flask import request, Response
from flask_restful import Resource
from flask_jwt_extended import jwt_required, get_jwt_identity
from .Model import db, Dokter, DokterSchema


class DokterFetch(Resource):
	# @jwt_required
	def get(self):
		# user_id = get_jwt_identity()
		# doktere = Dokter.query.filter_by(id=user_id).first()
		dokter = Dokter.query.all()
		# doktere = DokterSchema().dump(doktere).data
		dokter = DokterSchema(many=True).dump(dokter).data

		return {'NOTE': 'Cuman buat sementara, ntar diapus', 'status' : 'success', 'data': dokter}, 200

# controller sementara
class DokterFetchPersonal(Resource):
	# @jwt_required
	def get(self):
		# user_id = get_jwt_identity()
		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		data = json_data['data']
		dokter = Dokter.query.filter((Dokter.nama == data)|(Dokter.spesialis == data)).all()
		dokter = DokterSchema(many=True).dump(dokter).data
		# doktere = DokterSchema(many=True).dump(doktere).data

		return {'status' : 'success', 'data': dokter}, 200

class InputDokter(Resource):
	def post(self):
		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		# Validate and deserialize input
		data, errors = DokterSchema().load(json_data)
		if errors:
			return {"status": "error", "data": errors}, 422
		dokter = Dokter(
			nama = json_data['nama']
			,spesialis = json_data['spesialis']
			,noTelepon = json_data['noTelepon']
			)
		db.session.add(dokter)
		db.session.commit()
		dokter = Dokter.query.filter_by(nama=json_data['nama']).first()
		dokter = DokterSchema().dump(dokter).data
		return {"status": "success", "data": dokter}, 200