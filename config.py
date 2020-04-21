import os

# You need to replace this values with the appropriate values for your configuration
user = ""
password = ""
dbname = ""

JWT_SECRET_KEY = "]WDN{\n\x0bp>R2w)$hZ[DOdD^O^=QM^H'0~"

basedir = os.path.abspath(os.path.dirname(__file__))
SQLALCHEMY_ECHO = False
SQLALCHEMY_TRACK_MODIFICATIONS = True
SQLALCHEMY_DATABASE_URI = "mysql+pymysql://{0}:{1}@localhost:3306/{2}".format(user, password, dbname)