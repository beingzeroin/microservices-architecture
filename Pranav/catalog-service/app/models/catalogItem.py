import sys
sys.path.append("..")
from db import db

class CatalogItem(db.Model):
    __tablename__ = 'catalogitem'

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80))
    description = db.Column(db.String(200))
    price = db.Column(db.Integer)
    pictureFileName = db.Column(db.String(200))
    pictureUri = db.Column(db.String(200))
    catalogTypeId = db.Column(db.Integer, db.ForeignKey('catalogtype.id'), nullable=False)
    catalogBrandId = db.Column(db.Integer, db.ForeignKey('catalogbrand.id'), nullable=False)
    availableStock = db.Column(db.Integer)
    restockThreshold = db.Column(db.Integer)
    maxStockThreshold = db.Column(db.Integer)
    isDeleted = db.Column(db.Boolean, default=False)

    def __init__(self, name, description, price, pictureFileName, pictureUri, catalogTypeId, catalogBrandId,
                 availableStock, restockThreshold, maxStockThreshold, isDeleted=False):
        self.name = name
        self.description = description
        self.price = price
        self.pictureFileName = pictureFileName
        self.pictureUri = pictureUri
        self.catalogTypeId = catalogTypeId
        self.catalogBrandId = catalogBrandId
        self.availableStock = availableStock
        self.restockThreshold = restockThreshold
        self.maxStockThreshold = maxStockThreshold
        self.isDeleted = isDeleted

    @classmethod
    def find_by_id(cls, id):
        return cls.query.filter_by(id=id).first()

    @classmethod
    def find_by_name(cls, name):
        return cls.query.filter_by(name=name).first()

    @classmethod
    def find_all(cls):
        return cls.query.filter_by(isDeleted=False).all()

    @classmethod
    def find_all_pagination(cls, page, per_page):
        return cls.query.order_by(cls.id.asc()).paginate(page, per_page)

    def save_to_db(self):
        db.session.add(self)
        db.session.commit()

    def delete_from_db(self):
        db.session.delete(self)
        db.session.commit()
