from flask import Blueprint, redirect, url_for
from flask_restful import Api
from resources.Auth import Signup, Login
from resources.Landing import Landing
from resources.Profile import ProfileFetch, InputProfile, ProfileFetchUser
from resources.Dokter import DokterFetch, InputDokter, DokterFetchPersonal


api_bp = Blueprint('api', __name__)
api = Api(api_bp)


# Route
api.add_resource(Landing, '/')
api.add_resource(Signup, '/signup')
api.add_resource(Login, '/login')
api.add_resource(ProfileFetch, '/profile') #show profile for debug
api.add_resource(InputProfile, '/profile/add') #add profile for debug
api.add_resource(ProfileFetchUser, '/profile/user')

api.add_resource(DokterFetch, '/dokter') #show profile for debug
api.add_resource(InputDokter, '/dokter/add') #add profile for debug
api.add_resource(DokterFetchPersonal, '/dokter/fetch')



api_index = Blueprint('index', __name__)
@api_index.route('/', methods=['GET'])
def index():
	return redirect(url_for('api.landing'))

