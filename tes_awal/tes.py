import sqlite3
from flask import Flask, jsonify
import json

DATABASE = 'tes.db'

con = sqlite3.connect(DATABASE)

app = Flask(__name__)

cur = con.cursor()
cur.execute("SELECT * FROM antri")
rows = cur.fetchall()
print rows
print json.dumps([dict(i) for i in rows])
@app.route("/")
def get_data():
	DATABASE = 'tes.db'
	con = sqlite3.connect(DATABASE)
	cur = con.cursor()
	cur.execute("SELECT * FROM antri where no=1")
	rows = cur.fetchall()
	print json.dumps(rows)
	return jsonify(rows)

app.run(debug=True)