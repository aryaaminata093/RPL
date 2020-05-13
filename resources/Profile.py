from flask import request, Response
from flask_restful import Resource
from flask_jwt_extended import jwt_required, get_jwt_identity
from .Model import db, Profile, ProfileSchema, User

# class Signup(Resource):
# 	def post(self):
# 		json_data = request.get_json()
# 		if not json_data:
# 			return {'message': 'No input data provided'}, 400
# 		# Validate and deserialize input
# 		data, errors = UserSchema().load(json_data)
# 		if errors:
# 			return {"status": "error", "data": errors}, 422
# 		user = User(username=json_data['username'], email=json_data['email'], password= json_data['password'])
# 		user.hash_password()
# 		db.session.add(user)
# 		db.session.commit()
# 		id = user.id
# 		return {"status": "success", "data": str(id)}, 200

class ProfileFetch(Resource):
	# @jwt_required
	def get(self):
		# user_id = get_jwt_identity()
		# profile = Profile.query.filter_by(id=user_id).first()
		profile = Profile.query.all()
		# profile = ProfileSchema().dump(profile).data
		profile = ProfileSchema(many=True).dump(profile).data

		return {'NOTE': 'Cuman buat sementara, ntar diapus', 'status' : 'success', 'data': profile}, 200

class ProfileFetchUser(Resource):
	@jwt_required
	def get(self):
		user_id = get_jwt_identity()
		profile = User.query.filter_by(id=user_id).first()
		profile_id = profile.profileId
		profile = Profile.query.filter_by(id=profile_id).first()
		profile = ProfileSchema().dump(profile).data
		# profile = ProfileSchema(many=True).dump(profile).data

		return {'status' : 'success', 'data': profile}, 200


class InputProfile(Resource):
	def post(self):
		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		# Validate and deserialize input
		data, errors = ProfileSchema().load(json_data)
		if errors:
			return {"status": "error", "data": errors}, 422
		profil = Profile(
			nama = json_data['nama']
			,tempat_lahir = json_data['tempat_lahir']
			,tanggal_lahir = json_data['tanggal_lahir']
			,jenis_kelamin = json_data['jenis_kelamin']
			,gol_darah = json_data['gol_darah']
			,alamat = json_data['alamat'])
		db.session.add(profil)
		db.session.commit()
		profill = Profile.query.filter_by(nama=json_data['nama']).first()
		profile = ProfileSchema().dump(profill).data
		return {"status": "success", "data": profile}, 200