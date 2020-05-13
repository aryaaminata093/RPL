import os

# You need to replace this values with the appropriate values for your configuration
user = "api_rpl"
password = "mabok_rpl_1337"
host = "localhost:3306"
dbname = "api"
JWT_SECRET_KEY = "]WDN{\n\x0bp>R2w)$hZ[DOdD^O^=QM^H'0~"

# user = "dhipzbackup02"
# password = "mabok_rpl_1337"
# host ="dhipzbackup02.mysql.pythonanywhere-services.com"
# dbname = "dhipzbackup02$api_test"

basedir = os.path.abspath(os.path.dirname(__file__))
SQLALCHEMY_ECHO = False
SQLALCHEMY_TRACK_MODIFICATIONS = True
SQLALCHEMY_DATABASE_URI = "mysql+pymysql://{0}:{1}@{2}/{3}".format(user, password, host, dbname)