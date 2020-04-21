from flask import Flask
from marshmallow import Schema, fields, pre_load, validate
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import generate_password_hash, check_password_hash

ma = Marshmallow()
db = SQLAlchemy()



class User(db.Model):
	"""docstring for User"""
	__tablename__ = 'Users'
	id = db.Column(db.Integer, primary_key=True)
	username = db.Column(db.String(80), unique=True, nullable=False)
	email = db.Column(db.String(120), unique=True, nullable = False)
	password = db.Column(db.String(80), unique=True, nullable=False)
	creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)
	
	def __init__(self, username, email, password):
		self.username = username
		self.email = email
		self.password = password

	def hash_password(self):
		self.password = generate_password_hash(self.password).decode('utf8')
	
	def check_password(self, password):
		return check_password_hash(self.password, password)

class UserSchema(ma.Schema):
	id = fields.Integer()
	username = fields.String(required=True)
	email = fields.Email(required = True)
	password = fields.String(required=True)
	creation_date = fields.DateTime()



# class Comment(db.Model):
#     __tablename__ = 'comments'
#     id = db.Column(db.Integer, primary_key=True)
#     comment = db.Column(db.String(250), nullable=False)
#     creation_date = db.Column(db.TIMESTAMP, server_default=db.func.current_timestamp(), nullable=False)
#     category_id = db.Column(db.Integer, db.ForeignKey('categories.id', ondelete='CASCADE'), nullable=False)
#     category = db.relationship('Category', backref=db.backref('comments', lazy='dynamic' ))

#     def __init__(self, comment, category_id):
#         self.comment = comment
#         self.category_id = category_id


# class Category(db.Model):
#     __tablename__ = 'categories'
#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(150), unique=True, nullable=False)

#     def __init__(self, name):
#         self.name = name


# class CategorySchema(ma.Schema):
#     id = fields.Integer()
#     name = fields.String(required=True)


# class CommentSchema(ma.Schema):
#     id = fields.Integer(dump_only=True)
#     category_id = fields.Integer(required=True)
#     comment = fields.String(required=True, validate=validate.Length(1))
#     creation_date = fields.DateTime()