import sys
sys.path.append("..")

from db import db


class CatalogBrand(db.Model):
    __tablename__ = 'catalogbrand'

    id = db.Column(db.Integer, primary_key=True)
    brand = db.Column(db.String(80))

    def __init__(self, brand):
        self.brand = brand

    @classmethod
    def find_by_id(cls, id):
        return cls.query.filter_by(id=id).first()

    @classmethod
    def find_by_name(cls, name):
        return cls.query.filter_by(name=name).first()

    @classmethod
    def find_all(cls):
        return cls.query.all()

    @classmethod
    def find_all_pagination(cls, page, per_page):
        return cls.query.order_by(cls.id.asc()).paginate(page, per_page)

    def save_to_db(self):
        db.session.add(self)
        db.session.commit()

    def delete_from_db(self):
        db.session.delete(self)
        db.session.commit()