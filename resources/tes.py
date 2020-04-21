from flask_restful import Resource
from flask import Response, render_template


class tes(Resource):
	def get(self):
		return Response(render_template("index.html"),mimetype='text/html')
