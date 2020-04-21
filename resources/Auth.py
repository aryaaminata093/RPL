import datetime
from flask import request, Response
from flask_restful import Resource
from flask_jwt_extended import create_access_token
from .Model import db, User, UserSchema

class Signup(Resource):
	def post(self):
		json_data = request.get_json()
		if not json_data:
			return {'message': 'No input data provided'}, 400
		# Validate and deserialize input
		data, errors = UserSchema().load(json_data)
		if errors:
			return {"status": "error", "data": errors}, 422
		user = User(username=json_data['username'], email=json_data['email'], password= json_data['password'])
		user.hash_password()
		db.session.add(user)
		db.session.commit()
		id = user.id
		return {"status": "success", "data": str(id)}, 200

class Login(Resource):
	def post(self):
		json_data = request.get_json()
		user = User.query.filter_by(email=json_data['email']).first()
		authorized = user.check_password(json_data.get('password'))
		if not authorized:
			return {'error': 'Email or password invalid'}, 401

		expires = datetime.timedelta(days=1)
		access_token = create_access_token(identity=str(user.id), expires_delta=expires)
		
		return {'token': access_token}, 200