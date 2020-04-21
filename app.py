from flask import Blueprint
from flask_restful import Api
from resources.tes import tes
from resources.Comment import CommentResource

api_bp = Blueprint('api', __name__)
api = Api(api_bp)

# Route
api.add_resource(tes, '/')
api.add_resource(CommentResource, '/Comment')