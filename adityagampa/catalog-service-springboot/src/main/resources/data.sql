insert into CatalogBrand values (1, 'dell');
insert into CatalogType values (1, 'laptop');

insert into CatalogItem (ID,AVAILABLE_STOCK,DESCRIPTION,IS_DELETED,MAXSTOCK_THRESHOLD,NAME,PICTURE_FILE_NAME,PICTURE_URI,PRICE,RESTOCK_THRESHOLD,CATALOG_BRAND_ID,CATALOG_TYPE_ID) 
values (1, 1, 'sample desc 1', 0, 10, 'lappy', 'Dell LAPTOP', 'Some URI' ,10.0, 1, 1, 1);

insert into CatalogItem (ID,AVAILABLE_STOCK,DESCRIPTION,IS_DELETED,MAXSTOCK_THRESHOLD,NAME,PICTURE_FILE_NAME,PICTURE_URI,PRICE,RESTOCK_THRESHOLD,CATALOG_BRAND_ID,CATALOG_TYPE_ID)
values (2, 1, 'sample desc 2', 1, 10, 'book', 'meluha', 'Some URI 2' ,10.0, 1, 1, 1);
