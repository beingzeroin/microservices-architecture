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
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://root:password@db:5432/flaskJWT'
# app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///data.db'

@app.route("/")
def home():
    return "Flask app up and running!"

def _createItemsResponse(items):
    response = []
    for item in items:
        response.append({
            "id": item.id,
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
    return response


@app.route("/api/v1/catalog/items", methods=["POST", "GET"])
def catalogItems():
    if request.method == 'POST':
        return createCatalogItem()
    else:
        pageSize, pageIndex = request.args.get("pageSize"), request.args.get("pageIndex")
        if pageSize is not None and pageIndex is not None:
            return getCatalogItemsPaginated(int(pageSize), int(pageIndex))
        return getCatalogItems()


def getCatalogItemsPaginated(pageSize, pageIndex):
    records = CatalogItem.find_all_pagination(pageIndex, pageSize)
    items = records.items
    response = {
        "items": _createItemsResponse(items),
        "totalItems": records.total,
        "pageSize": items and len(items),
        "pageIndex": pageIndex,
    }
    return json.dumps(response)


def getCatalogItems():
    try:
        items = CatalogItem.query.all()
        return _createItemsResponse(items)
    except Exception:
        return InternalServerError("Something went wrong!")


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
        ct = catalogTypeId and  CatalogType.find_by_id(catalogTypeId)
        if ct == None:
            typeName = catalogType.get("type")
            db.session.add(CatalogType(type=typeName))
        elif ct.type != catalogType.get("type"):
            return BadRequest("Type name and Id do not match")
        cb = catalogBrandId and CatalogBrand.find_by_id(catalogBrandId)
        if cb == None:
            brandName = catalogBrand.get("brand")
            db.session.add(CatalogBrand(brand=brandName))
        elif cb.brand != catalogBrand.get("brand"):
            return BadRequest("Brand name and Id do not match")

        item = CatalogItem(name=name, description=description, price=price, pictureFileName=pictureFileName,
                           pictureUri=pictureUri, catalogTypeId=catalogTypeId, catalogBrandId=catalogBrandId,
                           availableStock=availableStock, restockThreshold=restockThreshold,
                           maxStockThreshold=maxStockThreshold)
        db.session.add(item)
        db.session.commit()
        return Response("Added successfuly", status=201)
    except Exception:
        return InternalServerError("Something went wrong")

@app.route("/api/v1/catalog/item/<itemId>", methods=["GET", "PUT", "DELETE"])
def catalogItem(itemId):
    if request.method == "GET":
        return getCatalogItem(itemId)
    elif request.method == "PUT":
        return updateCatalogItem(itemId)
    elif request.method == "DELETE":
        return deleteCatalogItem(itemId)

def getCatalogItem(itemId):
    try:
        item = CatalogItem.find_by_id(int(itemId))
        if item == None or item.isDeleted:
            return "No item exists for the given id"
        response = {
                    "name": item.name,
                    "description": item.description,
                    "price": item.price,
                    "pictureFileName": item.pictureFileName,
                    "pictureUri": item.pictureUri,
                    "availableStock": item.availableStock,
                    "restockThreshold": item.restockThreshold,
                    "catalogTypeId": item.catalogTypeId,
                    "catalogBrandId": item.catalogBrandId
        }
        return json.dumps(response)
    except Exception:
        return InternalServerError("Something went wrong!")

def updateCatalogItem(itemId):
    try:
        item = CatalogItem.find_by_id(itemId)
        body = request.json
        item.name = body.get("name")
        item.description = body.get("description")
        item.price = body.get("price")
        item.pictureFileName = body.get("pictureFileName")
        item.pictureUri = body.get("pictureUri")
        item.availableStock = body.get("availableStock")
        item.restockThreshold = body.get("restockThreshold")
        item.maxStockThreshold = body.get("maxStockThreshold")
        item.catalogTypeId = body.get("catalogTypeId")
        catalogType = body.get("catalogType")
        item.catalogBrandId = body.get("catalogBrandId")
        catalogBrand = body.get("catalogBrand")

        ct = item.catalogTypeId and  CatalogType.find_by_id(item.catalogTypeId)
        if ct == None:
            typeName = catalogType.get("type")
            db.session.add(CatalogType(type=typeName))
        elif ct.type != catalogType.get("type"):
            ct.type = catalogType.get("type")
            db.session.add(ct)

        cb = item.catalogBrandId and CatalogBrand.find_by_id(item.catalogBrandId)
        if cb == None:
            brandName = catalogBrand.get("brand")
            db.session.add(CatalogBrand(brand=brandName))
        elif cb.brand != catalogBrand.get("brand"):
            cb.type = catalogType.get("type")
            db.session.add(cb)
        db.session.commit()
        return "Not yet implemented"
    except Exception:
        return InternalServerError("Something went wrong")

def deleteCatalogItem(itemId):
    try:
        item = CatalogItem.find_by_id(int(itemId))
        item.isDeleted = True
        db.session.add(item)
        db.session.commit()
        return "Item deleted successfully."
    except Exception:
        return InternalServerError("Something went wrong!")

@app.route("/api/v1/catalog/types", methods=["GET"])
def getCatalogTypesPaginated():
    pageSize, pageIndex = request.args.get("pageSize"), request.args.get("pageIndex")
    if pageSize is None or pageIndex is None:
        return BadRequest("pageSize and pageIndex can't ne empty")
    records = CatalogType.find_all_pagination(int(pageIndex), int(pageSize))
    items = records.items
    rows = [{
        "id": item.id,
        "type": item.type
    } for item in items]

    response = {
        "items": rows,
        "totalItems": records.total,
        "pageSize": rows and len(rows),
        "pageIndex": pageIndex,
    }
    return json.dumps(response)


@app.route("/api/v1/catalog/brands", methods=["GET"])
def getCatalogBrandsPaginated():
    pageSize, pageIndex = request.args.get("pageSize"), request.args.get("pageIndex")
    if pageSize is None or pageIndex is None:
        return BadRequest("pageSize and pageIndex can't ne empty")
    records = CatalogBrand.find_all_pagination(int(pageIndex), int(pageSize))
    brands = records.items
    rows = [{
        "id": brand.id,
        "brand": brand.brand
    } for brand in brands]

    response = {
        "items": rows,
        "totalItems": records.total,
        "pageSize": rows and len(rows),
        "pageIndex": pageIndex,
    }
    return json.dumps(response)


@app.before_first_request
def create_tables():
    db.create_all()

if __name__ == '__main__':
    db.init_app(app)
    app.run(host='0.0.0.0', port=5000)