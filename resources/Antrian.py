import datetime
from flask import request, Response
from flask_restful import Resource
from flask_jwt_extended import jwt_required, get_jwt_identity
from .Model import db, Antrian, AntrianSchema, AntrianQuerySchema, AntrianAddSchema, User, Jadwal, Profile, Dokter, AntrianSchemaAll



class AntrianFetchAll(Resource):
	# @jwt_required
	def get(self):
		antrian = Antrian.query.all()
		antrian = db.session.query(Antrian.noAntrian, Antrian.tanggal, Antrian.profileId, Antrian.jadwalId, Dokter.nama.label("namaDokter"), Profile.nama.label("namaPasien")).select_from(Antrian).join(Jadwal, Antrian.jadwalId == Jadwal.id).join(Profile, Antrian.profileId == Profile.id).join(Dokter, Jadwal.dokterId == Dokter.id).all()
		antrian = AntrianSchemaAll(many=True).dump(antrian).data
		return {'status' : 'success', 'data': antrian}, 200

class AntrianFetchDate(Resource):
	def get(self,tanggal):

		antrian = Antrian.query.filter_by(tanggal=tanggal)
		antrian = AntrianSchema(many=True).dump(antrian).data

		return {'status' : 'success', 'data': antrian}, 200

class AntrianFetchJadwal(Resource):
	def get(self,jadwalId):
		antrian = Antrian.query.filter_by(jadwalId=jadwalId)
		antrian = AntrianSchema(many=True).dump(antrian).data

		return {'status' : 'success', 'data': antrian}, 200

class AntrianFetchLast(Resource):
	@jwt_required
	def get(self,jadwalId, tanggal):
		# jadwal = request.args['jadwal']
		# tanggal = request.args['tanggal']


		data, errors = AntrianQuerySchema().load({"jadwalId":jadwalId, "tanggal":tanggal})
		if errors:
			return {"status": "error", "data": errors}, 422

		antrian = Antrian.query.filter((Antrian.jadwalId == data["jadwalId"]), (Antrian.status == False) ,(Antrian.tanggal == data["tanggal"])).order_by(db.asc(Antrian.noAntrian)).first()
		antrian = antrian.noAntrian
		# antrian = AntrianSchema().dump(antrian).data

		return {'status' : 'success', 'data': antrian}, 200

class AntrianFetchProfile(Resource):
	@jwt_required
	def get(self, jadwalId, tanggal):
		# jadwal = request.args['jadwal']
		# tanggal = request.args['tanggal']
		user_id = get_jwt_identity()
		profile = User.query.filter_by(id=user_id).first()
		profileId = profile.profileId

		data, errors = AntrianAddSchema().load({"jadwalId":jadwalId, "tanggal":tanggal, "profileId":profileId})
		if errors:
			return {"status": "error", "data": errors}, 422

		antrian = Antrian.query.filter((Antrian.profileId == data["profileId"]),(Antrian.jadwalId == data["jadwalId"]), (Antrian.tanggal == data["tanggal"])).first()
		antrian = antrian.noAntrian
		# antrian = AntrianSchema().dump(antrian).data

		return {'status' : 'success', 'data': antrian}, 200

class AntrianFetchProfileAll(Resource):
	@jwt_required
	def get(self):
		user_id = get_jwt_identity()
		profile = User.query.filter_by(id=user_id).first()
		profileId = profile.profileId

		# data, errors = AntrianAddSchema().load({"jadwalId":jadwalId, "tanggal":tanggal, "profileId":profileId})
		# if errors:
			# return {"status": "error", "data": errors}, 422

		antrian = Antrian.query.filter((Antrian.profileId == profileId)).all()
		# antrian = antrian.noAntrian
		antrian = AntrianSchema(many=True).dump(antrian).data

		return {'status' : 'success', 'data': antrian}, 200

###############################################################################
# Input data

class AddAntrian(Resource):
	@jwt_required
	def post(self):
		user_id = get_jwt_identity()
		profile = User.query.filter_by(id=user_id).first()
		profileId = profile.profileId

		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		# Validate and deserialize input
		data, errors = AntrianAddSchema().load(json_data.update({"profileId":profileId}))
		if errors:
			return {"status": "error", "data": errors}, 422

		antrian_terakhir = Antrian.query.filter((Antrian.jadwalId == data['jadwalId']) ,(Antrian.tanggal == data['tanggal'])).order_by(db.desc(Antrian.noAntrian)).first()
		
		if antrian_terakhir is None:
			antrian_terakhir = 0
		else:
			antrian_terakhir = antrian_terakhir.noAntrian

		if antrian_terakhir >= 25:
			return {"message" : "Antrian penuh"}, 409

		antrian = Antrian(
			noAntrian = antrian_terakhir + 1
			,tanggal = json_data['tanggal']
			,jadwalId = json_data['jadwalId']
			,profileId = data['profileId']
			)
		db.session.add(antrian)
		db.session.commit()
		antrian = AntrianSchema().dump(antrian).data
		return {"status": "success", "data": antrian}, 200


class UpdateAntrian(Resource):
	def post(self):
		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		# Validate and deserialize input
		data, errors = AntrianAddSchema().load(json_data)
		if errors:
			return {"status": "error", "data": errors}, 422

		antrian_user = Antrian.query.filter((Antrian.profileId == data["profileId"]), (Antrian.jadwalId == data["jadwalId"]) ,(Antrian.tanggal == data["tanggal"])).order_by(db.desc(Antrian.noAntrian)).first()
		# antrian_terakhir = antrian_terakhir.noAntrian
		if antrian_user is not None:
			setattr(antrian_user, 'status', True )
		else:
			return {'message': 'Antrian tidak ditemukan'}, 400
		db.session.commit()
		antrian = AntrianSchema().dump(antrian_user).data
		return {"status": "success", "data": antrian}, 200