# multi-tenant
This will work with latest mysql version 8.

setup database on your local as given in schema files.
here tenant urls are being stored in tenant_configs tables.
please change username password according to your local setup. for this username is root and no password


create two tenant databases and customer table, whose configs are done in the code for async request handling.


For now if you will run post api : 

curl --location --request POST 'http://localhost:8080/customers' \
--header 'X-TenantId: test2' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Parul",
"lastName": "Bansal"
}'


it will store and return data in tables according to tenant header. 




