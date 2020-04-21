from flask import jsonify, request
from flask_restful import Resource
from Model import db, Comment, CommentSchema #, Category

comments_schema = CommentSchema(many=True)
comment_schema = CommentSchema()

class CommentResource(Resource):
    def get(self):
        comments = Comment.query.all()
        comments = comments_schema.dump(comments).data
        return {"status":"success", "data":comments}, 200

    def post(self):
        json_data = request.get_json(force=True)
        if not json_data:
               return {'message': 'No input data provided'}, 400
        # Validate and deserialize input
        data, errors = comment_schema.load(json_data)
        if errors:
            return {"status": "error", "data": errors}, 422
                
        komen = Comment(comment=json_data['comment'])
        db.session.add(komen)
        db.session.commit()

        comen = Comment.query.filter_by(comment=json_data['comment']).first()
        res = comment_schema.dump(comen).data
        # print (json_data['comment'])
        return {"status": "success", "data": res}, 200
        # category_id = Category.query.filter_by(id=data['category_id']).first()
        # if not category_id:
            # return {'status': 'error', 'message': 'comment category not found'}, 4

    def delete(self):
        json_data = request.get_json(force=True)
        if not json_data:
               return {'message': 'No input data provided'}, 400
        # Validate and deserialize input
        data, errors = comments_schema.load(json_data)
        if errors:
            return {"status": "error", "data": errors}, 422
                
        komen = Comment.query.filter_by(id=data['id']).delete()
        db.session.commit()

        
        res = comment_schema.dump(komen).data
        # print (json_data['comment'])
        return {"status": "success", "data": res}, 200