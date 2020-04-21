from flask import Flask
from flask_bcrypt import Bcrypt
from flask_jwt_extended import JWTManager

def create_app(config_filename):
    app = Flask(__name__)
    app.config.from_object(config_filename)
    
    bcrypt = Bcrypt(app)
    jwt = JWTManager(app)

    from app import api_bp
    app.register_blueprint(api_bp, url_prefix='/api')

    from resources.Model import db
    db.init_app(app)

    return app


if __name__ == "__main__":
    app = create_app("config")
    app.run(debug=True)