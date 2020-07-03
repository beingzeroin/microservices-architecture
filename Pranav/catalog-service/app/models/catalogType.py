import sys
sys.path.append("..")
from db import db


class CatalogType(db.Model):
    __tablename__ = 'catalogtype'

    id = db.Column(db.Integer, primary_key=True)
    type = db.Column(db.String(80))

    def __init__(self, type):
        self.type = type

    @classmethod
    def find_by_id(cls, type):
        return cls.query.filter_by(type=type).first()

    @classmethod
    def find_by_name(cls, name):
        return cls.query.filter_by(type=name).first()

    @classmethod
    def find_all(cls):
        return cls.query.all()

    def save_to_db(self):
        db.session.add(self)
        db.session.commit()

    def delete_from_db(self):
        db.session.delete(self)
        db.session.commit()