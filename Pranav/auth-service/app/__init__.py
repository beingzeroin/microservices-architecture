from flask import Flask, request
from flask_sqlalchemy import SQLAlchemy
import json
import jwt
import time

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://root:password@db:5432/flaskJWT'
# app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlituth.sqliteadsadsa3'
app.config['JWT_SECRET'] = 'secret'
app.config['JWT_EXP_SECONDS'] = 20
db = SQLAlchemy(app)

class User(db.Model):
    id = db.Column('user_id', db.Integer, primary_key = True)
    username = db.Column(db.String(100))
    password = db.Column(db.String(50))

    def __init__(self, username, password):
       self.username = username
       self.password = password

    @classmethod
    def get_by_id(cls, username):
        return cls.query.filter_by(username=username).first()

@app.route("/")
def home():
    return "Flask app up and running!"

@app.route("/api/v1/register", methods = ['POST','GET'])
def register():
    try:
        body = request.form
        username = body.get('userName')
        password = body.get('password')
        if username is None or password is None:
            return "Username and Password are mandatory"
        newUser = User(username, password)
        db.session.add(newUser)
        db.session.commit()
        return "Added Successfully"
    except Exception:
        return "Something went wrong"

@app.route("/api/v1/users", methods = ['GET'])
def listUsers():
    users = User.query.all()
    response = []
    for user in users:
        response.append({
            'userName': user.username,
            'password': user.password
        })
    return json.dumps(response)

@app.route("/api/v1/login", methods = ['POST'])
def login():
    username = request.form.get('userName')
    password = request.form.get('password')
    user = User.get_by_id(username)
    if user.password != password:
        return "Invalid Credentials"
    init_time = int(time.time())
    seconds = app.config.get('JWT_EXP_SECONDS')
    jwt_payload = {
        'exp': init_time + seconds,
        'iat': init_time,
        'sub': user.id
    }
    token = jwt.encode(jwt_payload, app.config.get('JWT_SECRET'), algorithm='HS256')
    return json.dumps({
        'token': token.decode('utf-8')
    })

@app.route("/api/v1/greeting/public", methods=['GET'])
def greetingPublic():
    return json.dumps({
        'greeting': "Welcome to API"
    })
@app.route("/api/v1/greeting/protected", methods=['GET'])
def greetingProtected():
    auth_token = request.headers.get('Authorization').split(' ')[1]
    try:
        payload = jwt.decode(auth_token, app.config.get('JWT_SECRET'))
        return json.dumps({
            'message' : 'Welcome to Authenticated API'
        })
    except jwt.ExpiredSignatureError:
        return json.dumps({
            'message' : 'Signature expired. Please log in again.'
        })
    except jwt.InvalidTokenError:
        return json.dumps({
            'message' : 'You need to be Authenticated first'
        })

if __name__ == '__main__':
    db.create_all()
    db.session.commit()
    app.run(host='0.0.0.0', port=3000)