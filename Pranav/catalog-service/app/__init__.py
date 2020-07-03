import sys
sys.path.append(".")
from flask import Flask, request, Response
from models.catalogBrand import CatalogBrand
from models.catalogItem import CatalogItem
from models.catalogType import CatalogType
from werkzeug.exceptions import BadRequest, InternalServerError
from db import db

import json

app = Flask(__name__)
# app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://root:password@db:5432/flaskJWT'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///data.db'

@app.route("/")
def home():
    return "Flask app up and running!"

@app.route("/api/v1/catalog/item", methods=["POST", "GET"])
def catalogItem():
    import pdb;pdb.set_trace()
    if request.method == 'POST':
        return createCatalogItem()
    else:
        return getCatalogItem()

def getCatalogItem():
    try:
        import pdb;pdb.set_trace()
        items = CatalogItem.query.all()
        response = []
        for item in items:
            response.append({
                "name": item.name,
                "description": item.description,
                "price": item.price,
                "pictureFileName": item.pictureFileName,
                "pictureUri": item.pictureUri,
                "availableStock": item.availableStock,
                "restockThreshold": item.restockThreshold,
                "catalogTypeId": item.catalogTypeId,
                "catalogBrandId": item.catalogBrandId
            })
        return json.dumps(response)
    except Exception:
        raise InternalServerError("Something went wrong!")

def createCatalogItem():
    try:
        body = request.json
        name = body.get("name")
        description = body.get("description")
        price = body.get("price")
        pictureFileName = body.get("pictureFileName")
        pictureUri = body.get("pictureUri")
        availableStock = body.get("availableStock")
        restockThreshold = body.get("restockThreshold")
        maxStockThreshold = body.get("maxStockThreshold")
        catalogTypeId = body.get("catalogTypeId")
        catalogType = body.get("catalogType")
        catalogBrandId = body.get("catalogBrandId")
        catalogBrand = body.get("catalogBrand")
        import pdb;pdb.set_trace()
        ct = catalogTypeId and  CatalogType.find_by_id(catalogTypeId)
        if ct == None:
            typeName = catalogType.get("type")
            db.session.add(CatalogType(type=typeName))
        elif ct.type != catalogType.get("type"):
            raise BadRequest("Type name and Id do not match")

        cb = catalogBrandId and CatalogBrand.find_by_id(catalogBrandId)
        if cb == None:
            brandName = catalogBrand.get("brand")
            db.session.add(CatalogBrand(brand=brandName))
        elif cb.brand != catalogBrand.get("brand"):
            raise BadRequest("Brand name and Id do not match")

        item = CatalogItem(name=name, description=description, price=price, pictureFileName=pictureFileName,
                           pictureUri=pictureUri, catalogTypeId=catalogTypeId, catalogBrandId=catalogBrandId,
                           availableStock=availableStock, restockThreshold=restockThreshold,
                           maxStockThreshold=maxStockThreshold)
        db.session.add(item)
        db.session.commit()
        return Response("Added successfuly", status=201)
    except Exception:
        raise InternalServerError("Something went wrong")

@app.before_first_request
def create_tables():
    db.create_all()

if __name__ == '__main__':
    db.init_app(app)
    # db.create_all()
    # db.session.commit()
    app.run(host='0.0.0.0', port=5000)