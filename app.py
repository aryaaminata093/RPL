from flask import Blueprint
from flask_restful import Api
from resources.Landing import Landing
from resources.Auth import Signup, Login


api_bp = Blueprint('api', __name__)
api = Api(api_bp)

# Route
api.add_resource(Landing, '/')
api.add_resource(Signup, '/signup')
api.add_resource(Login, '/login')
